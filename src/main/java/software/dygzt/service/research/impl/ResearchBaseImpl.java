package software.dygzt.service.research.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

import software.dygzt.data.bbsc.dao.BbscTemplateDao;
import software.dygzt.data.bbsc.dataobject.BbscTemplateDO;
import software.dygzt.data.research.dao.ResearchCellDao;
import software.dygzt.data.research.dao.ResearchSavedInfoDao;
import software.dygzt.data.research.dao.ResearchTableDao;
import software.dygzt.data.research.dao.ResearchTableDefDao;
import software.dygzt.data.research.dao.ResearchXXBDao;
import software.dygzt.data.research.dao.ResearchXXZBDao;
import software.dygzt.data.research.dao.SqlResultDao;
import software.dygzt.data.research.dataobject.ResearchCellDO;
import software.dygzt.data.research.dataobject.ResearchSavedInfoDO;
import software.dygzt.data.research.dataobject.ResearchTableDO;
import software.dygzt.data.research.dataobject.ResearchXXBDO;
import software.dygzt.data.research.dataobject.ResearchTableDefDO;
import software.dygzt.data.research.dataobject.ResearchXXZBDO;
import software.dygzt.dynamicds.CustomerContextHolder;
import software.dygzt.dynamicds.DataSourceRouter;
import software.dygzt.dynamicds.FYDataSourceEnum;
import software.dygzt.service.research.ResearchService;
import software.dygzt.service.research.model.BbscConditionVO;
import software.dygzt.service.research.model.ResearchAj;
import software.dygzt.service.research.model.ResearchAjVO;
import software.dygzt.service.research.model.ResearchCellCondition;
import software.dygzt.service.research.model.ResearchConditionVO;
import software.dygzt.service.research.model.ResearchQueryCondition;
import software.dygzt.service.research.model.ResearchQueryConditionJzrh;
import software.dygzt.service.research.model.ResearchQueryConditionSepa;
import software.dygzt.service.research.model.ResearchQueryResult;
import software.dygzt.service.research.model.ResearchQueryResultJzrh;
import software.dygzt.service.research.model.ResearchQueryResultSepa;
import software.dygzt.service.research.model.ResearchTable;
import software.dygzt.service.research.model.ResearchTableCell;
import software.dygzt.service.research.model.ResearchTableColumn;
import software.dygzt.service.research.model.ResearchTableDefModel;
import software.dygzt.service.research.model.ResearchTableRow;
import software.dygzt.service.research.model.ResearchTableVO;
import software.dygzt.service.research.model.ResearchXXBModel;
import software.dygzt.service.research.model.ResearchXXZBModel;
import software.dygzt.service.research.model.SearchConditionVO;
import software.dygzt.service.share.Convertor;
import software.dygzt.service.share.model.ContextHolder;
import software.dygzt.service.user.model.UserContextModel;
import software.dygzt.util.DateUtil;
import software.dygzt.util.ReflectionUtil;
import software.dygzt.util.StringUtil;

@Service("researchBaseImpl")
public class ResearchBaseImpl implements ResearchService, InitializingBean {
    private static final Logger log = Logger.getLogger(ResearchBaseImpl.class);
    @Autowired
    private SqlResultDao sqlResultDao;
    @Autowired
    private ResearchXXZBDao researchXXZBDao;
    @Autowired
    private ResearchXXBDao researchXXBDao;
    @Autowired
    private ResearchTableDefDao researchTableDefDao;
    @Autowired
    private ResearchTableDao researchTableDao;
    @Autowired
    private ResearchCellDao researchTableCellDao;
    @Autowired
    private ResearchSavedInfoDao researchSavedInfoDao;
    @Autowired
    private BbscTemplateDao bbscTemplateDao;

    private Map<String, ResearchXXZBModel> xxzMap = new HashMap<String, ResearchXXZBModel>();
    private Map<String, ResearchXXBModel> xxMap = new HashMap<String, ResearchXXBModel>();
    private Map<String, List<ResearchXXBModel>> xxbbMap = new HashMap<String, List<ResearchXXBModel>>(); //信息报表
    private Map<String, ResearchTableDefModel> tableMap = new HashMap<String, ResearchTableDefModel>();

    /**
     * 检查数据库连接状态(JZ)
     */
    public boolean databaseIsConnected() {
        return sqlResultDao.isConnected();
    }

    /**
     * 选择案件数目，生成案件列表(ok)
     */
    @Cacheable(cacheName = "AUTO_AJ_CACHE")
    public List<ResearchAjVO> getAjList(String fydm, String baseCondition,
                                        String condition) throws Exception {
        String curDb = CustomerContextHolder.getCustomerType();
        List<FYDataSourceEnum> dataSources = FYDataSourceEnum.getFyDataSourceList(fydm);
        //生成baseCondition
        ResearchQueryCondition base = new ResearchQueryConditionSepa();
        setQueryConditionByFieldStr(base, baseCondition);
        //生成cellcondition
        ResearchCellCondition cellCondition = new ResearchCellCondition();
        setCellConditionByFieldStr(cellCondition, condition, base);
        //通过basecondition筛选
        List<ResearchAj> baseCase = new ArrayList<ResearchAj>();
        //建立result
        ResearchQueryResult result = new ResearchQueryResultSepa();
        result.setAjxh(true);
        result.setLarq(true);
        result.setJarq(true);
        result.setAjxz(true);
        result.setSpcx(true);
        result.setSpcxdz(true);
        result.setAh(true);
        result.setAjmc(true);
        result.setLaay(true);
        result.setSpt(true);
        for (FYDataSourceEnum dataSource : dataSources) {
            DataSourceRouter.routerTo(dataSource.getFydm());
            log.info("连接" + dataSource.getFymc());
            try {
                List<ResearchAj> cases = sqlResultDao.getSqlResult(base.getSql(result), result, dataSource.getFydm());
                baseCase.addAll(cases);
            } catch (Exception e) {
                log.error(e);
            }
        }
        //通过cellcondition进一步筛选
        cellCondition.setCases(baseCase);
        cellCondition.filter();

        DataSourceRouter.routerTo(curDb);
        //转换为vo
        List<ResearchAjVO> vos = ajListToVo(cellCondition.getCases());
        return vos;
    }

    @Cacheable(cacheName = "AUTO_AJ_CACHE")
    public List<ResearchAjVO> getJzrhAjList(String fydm, String baseCondition,
                                            String condition) throws Exception {
        String curDb = CustomerContextHolder.getCustomerType();
        List<FYDataSourceEnum> dataSources = FYDataSourceEnum.getFyDataSourceList(fydm);
        //生成baseCondition
        ResearchQueryConditionJzrh base = new ResearchQueryConditionJzrh();
        setQueryConditionByFieldStr(base, baseCondition);
        for (FYDataSourceEnum dataSource : dataSources) {
            base.addFybh(dataSource.getFybh()); //增加一个法院选择，而不是set
        }
        //生成cellcondition
        ResearchCellCondition cellCondition = new ResearchCellCondition();
        setCellConditionByFieldStr(cellCondition, condition, base);
        //建立result
        ResearchQueryResultJzrh result = new ResearchQueryResultJzrh();
        result.setAjxh(true);
        result.setLarq(true);
        result.setJarq(true);
        result.setAjxz(true);
        result.setSpcx(true);
        result.setSpcxdz(true);
        result.setAh(true);
        result.setAjmc(true);
        result.setLaay(true);
        result.setSpt(true);
        result.setFybh(true);
        //通过basecondition筛选
        List<ResearchAj> baseCase = new ArrayList<ResearchAj>();
        DataSourceRouter.routerToJzrh(); //切换到集中融合库
        try {
            baseCase = sqlResultDao.getSqlResult(base.getSql(result), result, "-1");
        } catch (Exception e) {
            log.error(e);
        }
        //通过cellcondition进一步筛选
        cellCondition.setCases(baseCase);
        cellCondition.filter();

        DataSourceRouter.routerTo(curDb);

        //转换为vo
        List<ResearchAjVO> vos = ajListToVo(cellCondition.getCases());
        return vos;
    }

    private List<ResearchAjVO> ajListToVo(List<ResearchAj> ajlist) {
        // 去重
        Set<ResearchAj> set = new HashSet<ResearchAj>(ajlist);
        // 转换为vo
        List<ResearchAjVO> vos = new ArrayList<ResearchAjVO>();
        int i = 1;
        for (ResearchAj aj : set) {
            ResearchAjVO vo = new ResearchAjVO();
            vo.setXh(i);
            vo.setAh(aj.getAh());
            vo.setAhstr("<a target='_blank' href='ajxq.do?ajxh=" + aj.getAjxh()
                    + "&fydm=" + aj.getFydm() + "'>" + aj.getAh() + "</a>");
            vo.setAjmc(aj.getAjmc());
            vo.setLarq(aj.getLarqStr() == null ? "" : aj.getLarqStr());
            vo.setJarq(aj.getJarqStr() == null ? "" : aj.getJarqStr());
            vos.add(vo);
            i++;
        }
        return vos;
    }

    /**
     * 保存table（ok）
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public int saveTable(int tableid, ResearchTable table, int type) {
        ResearchTableDO po = findTable(table.getDytj());
        if (po == null) {
            ResearchTableDO tabledo = new ResearchTableDO();
            long maxid;
            if (tableid > 0) {
                maxid = tableid;
            } else {
                maxid = researchTableDao.getMaxId();
                maxid++;
            }
            tabledo.setId(Integer.parseInt(maxid + ""));
            tabledo.setBbmc(table.getBbmc());
            tabledo.setDytj(table.getDytj());
            tabledo.setKsrq(DateUtil.parse(table.getKsrq(), DateUtil.webFormat));
            tabledo.setJsrq(DateUtil.parse(table.getJsrq(), DateUtil.webFormat));
            tabledo.setAjzt(table.getBaseCondition().getAjztstr());
            tabledo.setRowlevel(table.getRowlevel());
            tabledo.setCollevel(table.getCollevel());
            tabledo.setSbfy(table.getSbfy());
            tabledo.setType(type);
            researchTableDao.save(tabledo);

            //建值
            List<List<ResearchCellDO>> valueList = new ArrayList<List<ResearchCellDO>>();

            int rowNum = table.getCollevel() + table.getRowList().size();    //总行数=表头信息层数+数据行数
            for (int i = 0; i < rowNum; i++) {    //初始化行列表
                List<ResearchCellDO> row = new ArrayList<ResearchCellDO>();
                valueList.add(row);
            }
            //下面使用的构造方法都是ResearchTableCellDO(tableid,rowid,cellid,value,fydm[案件涉及的法院数据库],sql[条件],colspan,rowspan,base[基本条件])
            //建立第一个空白格,在第一行的第一个单元格,宽是行层数，高是列层数
            ResearchCellDO cell = new ResearchCellDO(tabledo.getId(), 1, 1, "", null, null, table.getRowlevel(), table.getCollevel(), null);
            valueList.get(0).add(cell);
            for (ResearchTableColumn colmodel : table.getColList()) {    //转化表头信息
                //新建底层cell
                int height = table.getCollevel() - colmodel.getLevel() + 1; //rowspan=表头信息总层数-改单元格所占层数+1
                cell = new ResearchCellDO(tabledo.getId(), colmodel.getLevel(), valueList.get(colmodel.getLevel() - 1).size() + 1, colmodel.getColName(), null, null, 1, height, null);    //行数=在表头信息中所占层数，每个基本表头信息colspan都是1
                valueList.get(colmodel.getLevel() - 1).add(cell);    //在单元格所在层数增加这个单元格
                //新建父cell
                for (int j = 0; j < colmodel.getFatherName().size(); j++) { //遍历父节点名称
                    int level = colmodel.getLevel() - colmodel.getFatherName().size() - 1 + j; //所在层数 = 底层cell在层数-底层cell父cell总数-当前是第几个父cell
                    cell = new ResearchCellDO(tabledo.getId(), level + 1, valueList.get(level).size() + 1, colmodel.getFatherName().get(j), null, null, colmodel.getBrotherSize().get(j), 1, null);//colspan=cell兄弟数（含自己cell自己） rowspan=1
                    valueList.get(level).add(cell); //在单元格所在层数增加这个单元格
                }
            }
            for (ResearchTableRow rowmodel : table.getRowList()) {    //转化行信息
                ResearchTableColumn rowinfo = rowmodel.getRowInfo();
                int level = table.getCollevel() + rowinfo.getColNum() - 1; //层数 = 表头信息行数+cell在数据行的行数
                for (int j = 0; j < rowinfo.getFatherName().size(); j++) {//建父cell
                    cell = new ResearchCellDO(tabledo.getId(), level + 1, valueList.get(level).size() + 1, rowinfo.getFatherName().get(j), null, null, 1, rowinfo.getBrotherSize().get(j), null);//rowspan=cell兄弟数（含自己cell自己） colspan=1
                    valueList.get(level).add(cell);
                }
                //建自己
                int width = table.getRowlevel() - rowinfo.getLevel() + 1;    //colspan=左侧表头信息总层数-当前所在层数+1
                cell = new ResearchCellDO(tabledo.getId(), level + 1, valueList.get(level).size() + 1, rowinfo.getColName(), null, null, width, 1, null);    //基本表头信息的rowspan永远是1
                valueList.get(level).add(cell);
                //建值
                for (ResearchTableCell value : rowmodel.getValue()) {
                    if (value.getValue().equals("0")) {    //如果值是0，则不存入显示，且不需存入sql和fydm来提供案件列表查看
                        cell = new ResearchCellDO(tabledo.getId(), level + 1, valueList.get(level).size() + 1, "", null, null, 1, 1, null);
                    } else if (value.getCondition() == null) {    //不提供查看列表的信息，例如总计
                        cell = new ResearchCellDO(tabledo.getId(), level + 1, valueList.get(level).size() + 1, value.getValue(), null, null, 1, 1, null);
                    } else {    //值不为0且提供查询案件列表的表格
                        cell = new ResearchCellDO(tabledo.getId(), level + 1, valueList.get(level).size() + 1, value.getValue(), value.getFydm(), value.getCondition(), 1, 1, value.getBase());
                    }
                    valueList.get(level).add(cell);    //在对应层加入信息
                }
            }
            if (tableid > 0) {
                researchTableCellDao.delete(tableid);
            }
            researchTableCellDao.saveBatch(valueList);
            return tabledo.getId();
        } else {
            return po.getId();
        }
    }

    /**
     * 保存调研记录(ok)
     */
    @TriggersRemove(cacheName = {"AUTO_CACHE", "AUTO_SEARCH_CACHE"}, removeAll = true)
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveInfo(int tableid, String bgms, int type) {
        UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
        ResearchSavedInfoDO po = researchSavedInfoDao.findById(tableid, user.getYhmc(), user.getFydm());
        if (po == null) {
            ResearchSavedInfoDO info = new ResearchSavedInfoDO();
            info.setTableid(tableid);
            info.setDyr(user.getYhmc());
            info.setDyrfydm(user.getFydm());
            info.setDyrq(new Date());
            info.setBgms(bgms);
            info.setType(type);
            researchSavedInfoDao.save(info);
            return null;
        } else {
            return "已保存过此记录";
        }
    }

    /**
     * 删除调研记录（ok）
     */
    @TriggersRemove(cacheName = {"AUTO_CACHE", "AUTO_SEARCH_CACHE"}, removeAll = true)
    public String deleteSavedTable(int tableid) {
        UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
        researchSavedInfoDao.delete(tableid, user.getYhmc(), user.getFydm());
        return null;
    }

    /**
     * 查找调研记录(ok)
     */
    @Cacheable(cacheName = "AUTO_CACHE")
    public List<Object[]> savedTables(String name, String fydm, String ksrq, String jsrq, int type) {
        List<Object[]> dos = researchSavedInfoDao.findByDyr(name, fydm, ksrq, jsrq, type);
        return dos == null ? new ArrayList<Object[]>() : dos;
    }

    /**
     * 查询(ok)
     */
    @Cacheable(cacheName = "AUTO_SEARCH_CACHE")
    public List<Object[]> search(SearchConditionVO condition) {
        String sql = "select model.tableid, model.dyrq, ta.bbmc, ta.ksrq, ta.jsrq, ta.ajzt, model.dyr, model.dyrfydm from DY_AUTO_SAVEDINFO as model left join DY_AUTO_TABLE as ta on ta.id = model.tableid ";
        //type为1
        String con = "where model.type=1 and ta.type=1 and model.dyrq >= '" + condition.getKsrq() + "' and model.dyrq <= '" + condition.getJsrq() + " 23:59:59'";
        if (StringUtil.isNotBlank(condition.getDyfy())) {
            con += " and ta.dytj like '%fyfw:" + condition.getDyfy() + "%'";
        }
        if (StringUtil.isNotBlank(condition.getBblx())) {
            con += " and ta.dytj like '%bblx:" + condition.getBblx() + "%'";
        }
        if (StringUtil.isNotBlank(condition.getAjzt())) {
            con += " and ta.ajzt='" + condition.getAjzt() + "'";
        }
        if (StringUtil.isNotBlank(condition.getDyr())) {
            con += " and model.dyr like '%" + condition.getDyr() + "%'";
        }
        if (StringUtil.isNotBlank(condition.getDyrfydm())) {
            con += " and model.dyrfydm = '" + condition.getDyrfydm() + "'";
        }
        sql = sql + con + " order by model.dyrq desc";
        List<Object[]> dos = researchSavedInfoDao.findBySql(sql);
        return dos == null ? new ArrayList<Object[]>() : dos;
    }

    /**
     * 查询(ok),带 type 的历史查询
     */
    @Cacheable(cacheName = "AUTO_SEARCH_CACHE")
    public List<Object[]> search(SearchConditionVO condition, int type) {
        String sql = "select model.tableid, model.dyrq, ta.bbmc, ta.ksrq, ta.jsrq, ta.ajzt, model.dyr, model.dyrfydm from DY_AUTO_SAVEDINFO as model left join DY_AUTO_TABLE as ta on ta.id = model.tableid ";
        //type取值为1或者2
        String con = "where model.type=" + type + "and ta.type=" + type + " and model.dyrq >= '" + condition.getKsrq() + "' and model.dyrq <= '" + condition.getJsrq() + " 23:59:59'";
        if (StringUtil.isNotBlank(condition.getDyfy())) {
            con += " and ta.dytj like '%fyfw:" + condition.getDyfy() + "%'";
        }
        if (StringUtil.isNotBlank(condition.getBblx())) {
            con += " and ta.dytj like '%bblx:" + condition.getBblx() + "%'";
        }
        if (StringUtil.isNotBlank(condition.getAjzt())) {
            con += " and ta.ajzt='" + condition.getAjzt() + "'";
        }
        if (StringUtil.isNotBlank(condition.getDyr())) {
            con += " and model.dyr like '%" + condition.getDyr() + "%'";
        }
        if (StringUtil.isNotBlank(condition.getDyrfydm())) {
            con += " and model.dyrfydm = '" + condition.getDyrfydm() + "'";
        }
        sql = sql + con + " order by model.dyrq desc";
        List<Object[]> dos = researchSavedInfoDao.findBySql(sql);
        return dos == null ? new ArrayList<Object[]>() : dos;
    }

    /**
     * 查看已有的表(ok)
     */
    @Cacheable(cacheName = "AUTO_TABLE_CACHE")
    public ResearchTableVO findSavedTable(int tableid) {
        ResearchTableDO tableDO = researchTableDao.findById(tableid);
        List<ResearchCellDO> cellDO = researchTableCellDao.findByTableId(tableid);
        ResearchTableVO table = Convertor.do2vo(tableDO);
        table.setValueList(Convertor.dos2Cellvos(cellDO));
        return table;
    }

    /**
     * 寻找是否有已生成的表(ok)
     */
    public ResearchTableDO findTable(String dytj) {
        return researchTableDao.findByDytj(dytj);
    }

    /**
     * 生成列条件（ok）
     *
     * @param table
     * @param condition
     * @return
     * @throws Exception
     */
    public ResearchTable generateQueryConditionCol(ResearchConditionVO condition, ResearchQueryCondition base, String type) throws Exception {
        ResearchTable table = initTable(condition);
        //查找已经定义好的表
        ResearchTableDefModel tableDO = findTableById(table.getBbbh());
        //表condition=数据库中定义好的condition+用户通过界面新定义的condition
        if (StringUtil.isNotBlank(tableDO.getCondition())) {
            if (StringUtil.isNotBlank(table.getCondition())) {
                table.setCondition(tableDO.getCondition() + ";" + table.getCondition());
            } else {
                table.setCondition(tableDO.getCondition());
            }
        }
        //取出报表名称、行条件和列条件
        table.setBbmc(tableDO.getBbmc());
        table.setColvarlist(tableDO.getColCondition());
        String rowVarList;
        if (StringUtil.isNotBlank(type)) {
            rowVarList = (String) ReflectionUtil.invokeGetter(tableDO, "rowCondition" + type);
        } else {
            rowVarList = tableDO.getRowCondition();
        }
        table.setRowvarlist(rowVarList);

        List<ResearchCellCondition> result = new ArrayList<ResearchCellCondition>();

        //生成开始结束时间条件
        if (table.getKsrq() != null) {
            base.setKssj(table.getKsrq());
        }
        if (table.getJsrq() != null) {
            base.setJssj(table.getJsrq());
        }
        //生成整个报表的基本条件
        if (StringUtil.isNotBlank(table.getCondition())) {
            setQueryConditionByFieldStr(base, table.getCondition());
        }
        table.setBaseCondition(base);

        //将列条件中的变量组替换
        table.setColvarlist(replaceColXXZ(table.getColvarlist()));

        generateColTree(new ResearchCellCondition(), result, table, table.getColvarlist(), 0, 0);

        //将condition改为colcondition的最大集合
        table.setCondition(table.getBaseCondition().toString());

        table.setQueryList(result);
        return table;
    }

    /**
     * 生成列条件
     *
     * @param table
     * @param condition
     * @return
     * @throws Exception
     */
    public ResearchTable generateQueryConditionCol(BbscConditionVO condition, ResearchQueryCondition base, String type) throws Exception {
        ResearchTable table = new ResearchTable();
        table.setBblx(condition.getBblx());
        table.setKsrq(condition.getKsrq());
        table.setJsrq(condition.getJsrq());
        table.setFyfw(condition.getFyfw());
        table.setColvarlist(condition.getColCondition());

        //取出报表行条件
        if (StringUtil.isNotBlank(condition.getBbbh())) {
            ResearchTableDefModel tableDO = findTableById(condition.getBbbh());
            String rowVarList;
            if (StringUtil.isNotBlank(type)) {
                rowVarList = (String) ReflectionUtil.invokeGetter(tableDO, "rowCondition" + type);
            } else {
                rowVarList = tableDO.getRowCondition();
            }
            table.setRowvarlist(rowVarList);
        }

        List<ResearchCellCondition> result = new ArrayList<ResearchCellCondition>();

        //生成整个报表的基本条件
        if (table.getKsrq() != null) {
            base.setKssj(table.getKsrq());
        }
        if (table.getJsrq() != null) {
            base.setJssj(table.getJsrq());
        }
        table.setBaseCondition(base);

        //将列条件中的变量组替换
        table.setColvarlist(replaceColXXZ(table.getColvarlist()));

        //从第二层开始
        generateColTree(new ResearchCellCondition(), result, table, table.getColvarlist(), 1, 0);

        table.setQueryList(result);
        return table;
    }

    /**
     * 生成列条件树(ok)
     *
     * @param fatherCondition 父条件
     * @param result          生成列条件结果集合
     * @param table           表model
     * @param colCondition    解析到当前的colCondition String
     * @param fatherLevel     父亲的层数
     * @param firstChild      统计的第一个的index
     * @throws Exception
     * @return 兄弟个数
     */
    private int generateColTree(ResearchCellCondition fatherCondition, List<ResearchCellCondition> result, ResearchTable table, String colCondition, int fatherLevel, int firstChild) throws Exception {
        if (colCondition.startsWith("(")) {
            //递归中止条件
            if (colCondition.indexOf(">") < 0 && colCondition.indexOf("+") < 0) {
                colCondition = bracketsTrim(colCondition);
                ResearchTableColumn col;

                //层数
                int level = fatherLevel + 1;

                if (colCondition.equals("合计")) {
                    //赋值条件
                    ResearchCellCondition query = new ResearchCellCondition();
                    query.setIssum(true);
                    query.setStartIndex(firstChild);
                    result.add(query);
                    //赋值列
                    col = new ResearchTableColumn(table.getColList().size() + 1, "合计", level);
                } else {
                    ResearchXXBModel xx = findXxById(colCondition);
                    //赋值条件
                    ResearchCellCondition query = generateCondition(fatherCondition, xx, table.getBaseCondition());
                    result.add(query);
                    //赋值列
                    col = new ResearchTableColumn(table.getColList().size() + 1, xx.getXsmc(), level);
                }

                if (level > table.getCollevel()) {
                    table.setCollevel(level);
                }
                table.getColList().add(col);
                return 1;
            } else {
                int end = getMatchedRightBracket(0, colCondition);
                if (end == colCondition.length() - 1) {
                    return generateColTree(fatherCondition, result, table, colCondition.substring(1, end), fatherLevel, firstChild);
                } else if (end != -1) {
                    if (colCondition.charAt(end + 1) == '+') {
                        ResearchCellCondition fatherClone = (ResearchCellCondition) fatherCondition.clone();
                        return generateColTree(fatherCondition, result, table, colCondition.substring(0, end + 1), fatherLevel, firstChild)
                                + generateColTree(fatherClone, result, table, colCondition.substring(end + 2), fatherLevel, firstChild);
                    } else if (colCondition.charAt(end + 1) == '>') {
                        ResearchXXBModel xx = findXxById(colCondition.substring(1, end));
                        //赋值条件
                        ResearchCellCondition query = generateCondition(fatherCondition, xx, table.getBaseCondition());
                        int beforeIndex = table.getColList().size();
                        int sonnum = generateColTree(query, result, table, colCondition.substring(end + 2), fatherLevel + 1, beforeIndex);
                        ResearchTableColumn col = table.getColList().get(beforeIndex);
                        col.setFirstChild(true);
                        col.getFatherName().add(0, xx.getXsmc());
                        col.getBrotherSize().add(0, sonnum);
                        return sonnum;
                    } else {
                        throw new Exception(")多出");
                    }
                } else {
                    throw new Exception("(多出");
                }
            }
        } else {
            throw new Exception("(格式错误");
        }
    }

    /**
     * 生成cell(ok)
     *
     * @param con
     * @param cellList
     * @param fydm
     * @return
     * @throws Exception
     */
    public ResearchTableCell generateCell(ResearchCellCondition con, List<ResearchTableCell> cellList, String fydm, int type, String base) throws Exception {
        ResearchTableCell cell = new ResearchTableCell();
        /*判断该 cell 是否是合计单元，因为如果是合计 cell ，那么这个cell 只需要给数字，不需要设置链接
        * 如果不是合计 cell，则需要重新往数据库查数据，即给定查询条件和链接。
        * */
        if (con.isIssum()) {
            double sum = 0.0;
            for (int i = con.getStartIndex(); i < cellList.size(); i++) {
                sum += cellList.get(i).getValueDouble();
            }
            cell.setValue(sum);
        } else {

            /*初始化 cell 的案件 List<ResearchAj> cases;*/
            if (type == 0) {
                con.filter();
            } else if (type == 1) {
                con.fil_laay();
            } else if (type == 2) {
                con.fil_baspt();
            }
            /*该 cell 的案件数目 col 空，则通过计算 cases 的数目再设置*/
            if (con.getCol() == null) {
                cell.setValue(con.result());
            } else if (con.getCol() == "立案标的") {
                //...
            }
            cell.setCondition(con.toString()); //设置跳转的时候的查询条件
            cell.setFydm(fydm);
            cell.setBase(base);
        }
        cellList.add(cell);
        return cell;
    }

    /**
     * 得到行总计(ok)
     *
     * @param startIndex
     * @param rowlist
     * @param row
     */
    public List<ResearchTableCell> generateSumRow(int startIndex, List<ResearchTableRow> rowlist, int size) {
        double[] sumList = new double[size];
        List<ResearchTableCell> cellList = new ArrayList<ResearchTableCell>();
        //计算
        for (int i = startIndex; i < rowlist.size(); i++) {
            List<ResearchTableCell> cells = rowlist.get(i).getValue();
            for (int j = 0; j < cells.size(); j++) {
                sumList[j] += cells.get(j).getValueDouble();
            }
        }
        //生成cell
        for (double value : sumList) {
            ResearchTableCell cell = new ResearchTableCell();
            cell.setValue(value);
            cellList.add(cell);
        }
        return cellList;
    }

    /**
     * 生成进一步的条件(ok)
     *
     * @throws Exception
     */
    private ResearchCellCondition generateCondition(ResearchCellCondition condition, ResearchXXBModel xx, ResearchQueryCondition base) throws Exception {
        //赋值条件
        try {
            setCellConditionByFieldStr(condition, xx.getCondition(), base);
        } catch (Exception e) {
            //条件赋值冲突
            String message = xx.getXxmc() + e.getMessage();
            throw new Exception(message);
        }
        //赋值结果提取方式
        try {
            condition.setCol(xx.getCol());
        } catch (Exception e) {
            //条件赋值冲突
            String message = xx.getXxmc() + e.getMessage();
            throw new Exception(message);
        }
        return condition;
    }

    /**
     * 给condition的列赋值(ok)
     *
     * @param condition
     * @param fields
     */
    private void setQueryConditionByFieldStr(ResearchQueryCondition condition, String fields) {
        if (StringUtil.isNotBlank(fields)) {
            String[] conditionList = fields.split(";");
            for (String con : conditionList) {
                int equalIndex = con.indexOf("=");
                String key = con.substring(0, equalIndex);
                String value = con.substring(equalIndex + 1);
                ReflectionUtil.invokeSetter(condition, key, value);
            }
        }
    }

    /**
     * 给cellcondition的列赋值(ok)
     *
     * @param condition
     * @param fields
     * @param base
     */
    private void setCellConditionByFieldStr(ResearchCellCondition condition, String fields, ResearchQueryCondition base) {
        if (StringUtil.isNotBlank(fields)) {
            String[] conditionList = fields.split(";");
            for (String con : conditionList) {
                int equalIndex = con.indexOf("=");
                String key = con.substring(0, equalIndex);
                String value = con.substring(equalIndex + 1);
                ReflectionUtil.invokeSetter(condition, key, value);
                if (key.equals("ajxz")) {
                    base.setAjxz(value);
                }
            }
        }
    }

    /**
     * 初始化表(ok)
     */
    private ResearchTable initTable(ResearchConditionVO condition) {
        ResearchTable table = new ResearchTable();
        table.setBbbh(condition.getBbbh());
        table.setBblx(condition.getBblx());
        table.setKsrq(condition.getKsrq());
        table.setJsrq(condition.getJsrq());
        table.setFyfw(condition.getFyfw());
        table.setCondition(condition.getCondition().replaceAll(":", "="));
        return table;
    }

    /**
     * 将信息组替换为信息（ok）
     */
    private String replaceColXXZ(String col) {
        String pattern = "\\[(.*?)\\]";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(col);

        while (m.find()) {
            String xxs = findXxzById(m.group(1)).getXxs();
            col = col.replace(m.group(0), xxs);
        }
        return col;
    }

    /**
     * 删掉两边的括号(ok)
     *
     * @param s
     * @return
     */
    private String bracketsTrim(String s) {
        int left = 0;
        int right = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left = i + 1;
            } else {
                break;
            }
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                right = i;
            } else {
                break;
            }
        }
        return s.substring(left, right);
    }

    /**
     * 找到对应的右括号(ok)
     */
    private int getMatchedRightBracket(int startIndex, String s) {
        int left = 1;
        int right = 0;
        for (int i = startIndex + 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                right++;
            }
            if (left == right) {
                return i;
            }
        }
        return -1;
    }

    public Map<String, List<ResearchXXBModel>> getXxbbMap() {
        return xxbbMap;
    }

    private ResearchXXZBModel findXxzById(String id) {
        return xxzMap.get(id);
    }

    private ResearchXXBModel findXxById(String id) {
        return xxMap.get(id);
    }

    private ResearchTableDefModel findTableById(String id) {
        return tableMap.get(id);
    }

    public ResearchTable[] splitTable(String[] yjls, ResearchTable table) throws CloneNotSupportedException {
        ResearchTable[] tables = new ResearchTable[yjls.length];
        for (int i = 0; i < yjls.length; i++) {
            //克隆table
            ResearchTable tmpTable = (ResearchTable) table.clone();
            //修改baseCondition
            String[] key_value = yjls[i].split("/");
            ReflectionUtil.invokeSetter(tmpTable.getBaseCondition(), key_value[0], key_value[1]);
            tmpTable.setCondition(tmpTable.getBaseCondition().toString());
            //在第一列上加上父列(案件状态)
            List<ResearchTableColumn> colList = tmpTable.getColList();
            ResearchTableColumn col = colList.get(0);
            col.getFatherName().add(key_value[1]);
            col.getBrotherSize().add(colList.size());
            col.setFirstChild(true);
            tables[i] = tmpTable;
        }
        return tables;
    }

    public ResearchTable unionTable(ResearchTable table, ResearchTable[] tables) {
        List<ResearchTableColumn> cols = table.getColList();
        cols.clear();
        int collevel = 1;
        int rowlevel = 1;
        //列，level
        for (ResearchTable tmpTable : tables) {
            cols.addAll(tmpTable.getColList());
            if (tmpTable.getCollevel() > collevel) {
                collevel = tmpTable.getCollevel();
            }
            if (tmpTable.getRowlevel() > rowlevel) {
                rowlevel = tmpTable.getRowlevel();
            }
        }
        table.setCollevel(collevel);
        table.setRowlevel(rowlevel);

        int i = 0;
        List<ResearchTableRow> rows = tables[0].getRowList();
        for (int j = 0; j < rows.size(); j++) {
            List<ResearchTableCell> cellList = new ArrayList<ResearchTableCell>();
            for (ResearchTable tmpTable : tables) {
                cellList.addAll(tmpTable.getRowList().get(j).getValue());
            }
//            if (hasValue(cellList)) { //如果cell 中有值，则加一行，否则，不加，为了加入同比，去了这个判断条件
                ResearchTableColumn oldRow = rows.get(j).getRowInfo();
                //建row
                i++;
                ResearchTableRow row = new ResearchTableRow();
                ResearchTableColumn rowAttr = new ResearchTableColumn(i, oldRow.getColName(), oldRow.getLevel());
                row.setRowInfo(rowAttr);
                row.setValue(cellList);
                table.getRowList().add(row);
//            }
        }
        return table;
    }

    public boolean hasValue(List<ResearchTableCell> list) {
        boolean hasNum = false;
        for (ResearchTableCell cell : list) {
            if (cell.getValueDouble() != 0) {
                hasNum = true;
                break;
            }
        }
        return hasNum;
    }

    public void afterPropertiesSet() throws Exception {
        List<ResearchXXZBDO> xxzList = researchXXZBDao.findAll();
        for (ResearchXXZBDO xxz : xxzList) {
            ResearchXXZBModel model = Convertor.doToModel(xxz);
            xxzMap.put(xxz.getLbmc(), model);
        }
        List<ResearchXXBDO> xxList = researchXXBDao.findAll();
        for (ResearchXXBDO xx : xxList) {
            ResearchXXBModel model = Convertor.doToModel(xx);
            xxMap.put(xx.getXxmc(), model);
            String bbbh = xx.getBbbh();
            if (StringUtil.isNotBlank(bbbh)) {
                if (xxbbMap.containsKey(bbbh)) {
                    xxbbMap.get(bbbh).add(model);
                } else {
                    List<ResearchXXBModel> xxs = new ArrayList<ResearchXXBModel>();
                    xxs.add(model);
                    xxbbMap.put(bbbh, xxs);
                }
            }
        }
        List<ResearchTableDefDO> tableList = researchTableDefDao.findAll();
        for (ResearchTableDefDO table : tableList) {
            ResearchTableDefModel model = Convertor.doToModel(table);
            tableMap.put(table.getBbbh(), model);
        }

    }

    public BbscConditionVO getConditionByTemplate(int id) {
        BbscTemplateDO po = bbscTemplateDao.findById(id);
        BbscConditionVO condition = new BbscConditionVO();
        condition.setYjCondition(po.getYjCondition());
        condition.setColCondition(po.getColCondition());
        condition.setBblx(po.getBblx());
        condition.setFyfw(po.getFyfw());
        condition.setBbbh(po.getBbbh());
        return condition;
    }

    @TriggersRemove(cacheName = {"BBSC_TEMPLATE_CACHE"}, removeAll = true)
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveTemplate(BbscConditionVO condition, String name) {
        UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
        List<BbscTemplateDO> pos = bbscTemplateDao.findByCondition(condition.getYjCondition(), condition.getColCondition(), condition.getBblx(), condition.getFyfw(), condition.getBbbh());
        if (pos.size() == 0) {
            long maxid = bbscTemplateDao.getMaxId();
            maxid++;
            BbscTemplateDO info = new BbscTemplateDO();
            info.setId(Integer.parseInt(maxid + ""));
            info.setName(name);
            info.setYjCondition(condition.getYjCondition());
            info.setColCondition(condition.getColCondition());
            info.setBblx(condition.getBblx());
            info.setFyfw(condition.getFyfw());
            info.setBbbh(condition.getBbbh());
            info.setUsername(user.getYhmc());
            info.setUserfydm(user.getFydm());
            info.setCjsj(new Date());
            bbscTemplateDao.save(info);
            return null;
        } else {
            return "已保存过此模板";
        }
    }

    @Cacheable(cacheName = "BBSC_TEMPLATE_CACHE")
    public List<BbscTemplateDO> myTemplateList(String name, String fydm) {
        List<BbscTemplateDO> dos = bbscTemplateDao.findByDyr(name, fydm);
        return dos == null ? new ArrayList<BbscTemplateDO>() : dos;
    }

    public List<BbscTemplateDO> allTemplateList() {
        List<BbscTemplateDO> dos = bbscTemplateDao.findAll();
        return dos == null ? new ArrayList<BbscTemplateDO>() : dos;
    }

    @TriggersRemove(cacheName = {"BBSC_TEMPLATE_CACHE"}, removeAll = true)
    public String modifyTemplate(int id, String name) {
        bbscTemplateDao.updateName(id, name);
        return null;
    }

    @TriggersRemove(cacheName = {"BBSC_TEMPLATE_CACHE"}, removeAll = true)
    public String deleteTemplate(int id) {
        bbscTemplateDao.delete(id);
        return null;
    }

}
