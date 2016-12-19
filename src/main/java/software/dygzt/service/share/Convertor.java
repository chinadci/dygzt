package software.dygzt.service.share;

import java.util.ArrayList;
import java.util.List;

import software.dygzt.data.share.dataobject.SjjzBdDO;
import software.dygzt.data.user.dataobject.XtglYhbDO;
import software.dygzt.service.ajlx.model.AjlxVO;
import software.dygzt.service.bm.model.BmModel;
import software.dygzt.service.dmb.model.DmVO;
import software.dygzt.service.dmb.model.DmbModel;
import software.dygzt.service.manualResearch.model.ManualHistoryVO;
import software.dygzt.service.manualResearch.model.ManualModel;
import software.dygzt.service.manualResearch.model.ManualVO;
import software.dygzt.service.research.model.BbscTemplateVO;
import software.dygzt.service.research.model.ResearchTable;
import software.dygzt.service.research.model.ResearchTableCell;
import software.dygzt.service.research.model.ResearchTableCellVO;
import software.dygzt.service.research.model.ResearchTableColumn;
import software.dygzt.service.research.model.ResearchTableDefModel;
import software.dygzt.service.research.model.ResearchTableRow;
import software.dygzt.service.research.model.ResearchTableVO;
import software.dygzt.service.research.model.ResearchXXBModel;
import software.dygzt.service.research.model.ResearchXXZBModel;
import software.dygzt.service.share.model.ExcelCell;
import software.dygzt.service.share.model.SjjzBdModel;
import software.dygzt.service.user.model.DyXtyhVO;
import software.dygzt.service.user.model.XtglyhModel;
import software.dygzt.util.DateUtil;
import software.dygzt.util.StringUtil;
import software.dygzt.data.ajlx.dataobject.AjlxbDO;
import software.dygzt.data.bbsc.dataobject.BbscTemplateDO;
import software.dygzt.data.bm.dataobject.JzrhBmbDO;
import software.dygzt.data.dmb.dataobject.DmbDO;
import software.dygzt.data.manual.dataobject.ManualResearchDO;
import software.dygzt.data.research.dataobject.ResearchCellDO;
import software.dygzt.data.research.dataobject.ResearchTableDO;
import software.dygzt.data.research.dataobject.ResearchTableDefDO;
import software.dygzt.data.research.dataobject.ResearchXXBDO;
import software.dygzt.data.research.dataobject.ResearchXXZBDO;
import software.dygzt.data.user.dataobject.DyXtyhDO;
import software.dygzt.dynamicds.FYDataSourceEnum;

import static software.dygzt.dynamicds.FYDataSourceEnum.getDataSourceByFydm;

/**
 * po、vo互转
 */
public class Convertor {

    /**
     * 人工调研do转model
     *
     * @param model
     * @return
     */
    public static ManualResearchDO model2do(ManualModel model) {
        ManualResearchDO manualdo = null;
        if (model != null) {
            manualdo = new ManualResearchDO();
            manualdo.setKsrq(DateUtil.parse(model.getKsrq(), DateUtil.webFormat));
            manualdo.setJsrq(DateUtil.parse(model.getJsrq(), DateUtil.webFormat));
            manualdo.setSjlx(model.getSjlx());
            manualdo.setFyfw(model.getFyfw());
            manualdo.setAjlx(model.getAjlx());
            manualdo.setDymd(model.getDymd());
            manualdo.setDyxq(model.getDyxq());
            manualdo.setFjm(model.getFjm());
            manualdo.setFj(model.getFj());
            manualdo.setDyr(model.getDyr());
            manualdo.setDyrfydm(model.getDyrfydm());
            manualdo.setDyrphone(model.getDyrphone());
            manualdo.setDyrlxfs(model.getDyrlxfs());
        }
        return manualdo;
    }


    /**
     * XtglYhbDO 转 XtglyhModel
     * */
    public static XtglyhModel do2model(XtglYhbDO xtglYhbDO){
        XtglyhModel xtglyhModel = null;
        if(xtglYhbDO != null){
            xtglyhModel = new XtglyhModel();
            xtglyhModel.setYhmc(xtglYhbDO.getYhmc());
            xtglyhModel.setFybh(xtglYhbDO.getFybh()); //法院编号
            xtglyhModel.setYhbm(xtglYhbDO.getYhbm());
            xtglyhModel.setYhsf(xtglYhbDO.getYhsf());
            return xtglyhModel;
        }
        return null;
    }



    /**
     * 调研用户vo转do
     *
     * @param vo
     * @return
     */
    public static DyXtyhDO vo2do(DyXtyhVO vo) {
        DyXtyhDO xtyhdo = null;
        if (vo != null) {
            xtyhdo = new DyXtyhDO();
            xtyhdo.setYhm(vo.getYhm());
            xtyhdo.setFydm(vo.getFydm());
            xtyhdo.setName(vo.getName());
            xtyhdo.setBm(vo.getBm());
            xtyhdo.setPassword(vo.getPassword());
            xtyhdo.setQx(vo.getQx());
            xtyhdo.setPhone(vo.getPhone());
            xtyhdo.setLxfs(vo.getLxfs());
        }
        return xtyhdo;
    }

    /**
     * 调研用户do转vo
     *
     * @param vo
     * @return
     */
    public static DyXtyhVO do2vo(DyXtyhDO po) {
        DyXtyhVO xtyhvo = null;
        if (po != null) {
            xtyhvo = new DyXtyhVO();
            xtyhvo.setYhm(po.getYhm());
            xtyhvo.setFydm(po.getFydm());

//			FYDataSourceEnum fyEnum=  FYDataSourceEnum.getDataSourceByFydm(po.getFydm());
//			xtyhvo.setFymc(fyEnum == null ? "":fyEnum.getFymc());
//这里的法院名称是根据 FYDataSourceEnum 枚举中的fydm 来取得的，如果数据库中的法院代码没有在这个枚举中出现，是会出现错误的，如果改成上面，则不会出现错误，但是和业务不符合
            xtyhvo.setFymc(FYDataSourceEnum.getDataSourceByFydm(po.getFydm()).getFymc());
            xtyhvo.setName(po.getName());
            xtyhvo.setQx(po.getQx());
            xtyhvo.setPhone(po.getPhone() == null ? "" : po.getPhone());
            xtyhvo.setLxfs(po.getLxfs() == null ? "" : po.getLxfs());
            xtyhvo.setPassword(po.getPassword());
            xtyhvo.setBm(po.getBm() == null ? "" : po.getBm());
        }
        return xtyhvo;
    }

    /**
     * 调研用户dos转vos
     *
     * @param vo
     * @return
     */
    public static List<DyXtyhVO> dos2dyXtyhVos(List<DyXtyhDO> pos) {
        List<DyXtyhVO> resultList = new ArrayList<DyXtyhVO>();
        for (int i = 0; i < pos.size(); i++) {
            DyXtyhDO dyXtyhdo = pos.get(i);
            DyXtyhVO vo = do2vo(dyXtyhdo);
            vo.setXh(i + 1);
            resultList.add(vo);
        }
        return resultList;
    }

//	public static AjjbModel do2vo(AjjbDO po){
//		AjjbModel vo = null;
//		if(po != null){
//			vo = new AjjbModel();
//			
//		}
//		return vo;
//	}

    public static ResearchTableVO do2vo(ResearchTableDO po) {
        ResearchTableVO vo = null;
        if (po != null) {
            vo = new ResearchTableVO();
            vo.setBbmc(po.getBbmc());
            vo.setRowlevel(po.getRowlevel());
            vo.setCollevel(po.getCollevel());
            vo.setDytj(po.getDytj());
        }
        return vo;
    }

    public static ResearchTableCellVO do2vo(ResearchCellDO po) {
        ResearchTableCellVO vo = null;
        if (po != null) {
            vo = new ResearchTableCellVO(po.getValue(), po.getColspan(), po.getRowspan(), null, po.getSql(), po.getFydm());
            if (po.getBase() != null) {
                vo.setBase(po.getBase().replace("%", "%25"));
            }
        }
        return vo;
    }

    public static List<List<ResearchTableCellVO>> dos2Cellvos(List<ResearchCellDO> pos) {
        List<List<ResearchTableCellVO>> table = new ArrayList<List<ResearchTableCellVO>>();
        int currentRow = 1;
        List<ResearchTableCellVO> row = new ArrayList<ResearchTableCellVO>();
        for (ResearchCellDO po : pos) {
            if (po.getRowid() != currentRow) {
                currentRow = po.getRowid();
                table.add(row);
                row = new ArrayList<ResearchTableCellVO>();
            }
            ResearchTableCellVO vo = do2vo(po);
            row.add(vo);
        }
        if (pos != null && (!pos.isEmpty())) {
            table.add(row);
        }
        return table;
    }

    /**
     * 代码表do转vo
     *
     * @param vo
     * @return
     */
    public static DmVO do2vo(DmbDO po) {
        DmVO vo = null;
        if (po != null) {
            vo = new DmVO();
            vo.setBh(po.getDmbh());
            vo.setMs(po.getDmms());
        }
        return vo;
    }

    /**
     * 代码表dos转vos
     *
     * @param vo
     * @return
     */
    public static List<DmVO> dos2Dmvos(List<DmbDO> dmbList) {
        List<DmVO> resultList = new ArrayList<DmVO>();
        for (DmbDO dmbdo : dmbList) {
            DmVO vo = do2vo(dmbdo);
            resultList.add(vo);
        }
        return resultList;
    }

    /**
     * @param po
     * @return
     */
    public static ManualVO doToManualVO(ManualResearchDO po) {
        ManualVO vo = null;
        if (po != null) {
            vo = new ManualVO();
            vo.setId(po.getId());
            vo.setDyrq(DateUtil.format(po.getDyrq(), DateUtil.webFormat));
            vo.setKsrq(DateUtil.format(po.getKsrq(), DateUtil.webFormat));
            vo.setJsrq(DateUtil.format(po.getJsrq(), DateUtil.webFormat));
            vo.setSjlx(po.getSjlx());
            vo.setDyzt(po.getDyzt());
            vo.setFyfw(po.getFyfw());
            vo.setAjlx(po.getAjlx());
            vo.setDymd(po.getDymd());
            vo.setDyxq(po.getDyxq());
            vo.setFjm(po.getFjm());
            vo.setDyr(po.getDyr());
            if (po.getDyrfydm() != null) {
                vo.setDyrfy(getDataSourceByFydm(po.getDyrfydm()).getFymc());
            }
            vo.setDyrphone(po.getDyrphone() == null ? "" : po.getDyrphone());
            vo.setDyrlxfs(po.getDyrlxfs() == null ? "" : po.getDyrlxfs());
            vo.setSpr(po.getSpr());
            if (po.getSprfydm() != null) {
                vo.setSprfy(getDataSourceByFydm(po.getSprfydm()).getFymc());
            }
            if (po.getSpryj() != null) {
                vo.setSpryj(po.getSpryj().equals("Y") ? "通过" : "不通过");
            }
            vo.setSprthyy(po.getSprthyy());
            vo.setJsr(po.getJsr());
            if (po.getJsrfydm() != null) {
                vo.setJsrfy(getDataSourceByFydm(po.getJsrfydm()).getFymc());
            }
            vo.setDyjgm(po.getDyjgm());
            vo.setDydmm(po.getDydmm());
            if (po.getSprspsj() != null) {
                vo.setSprspsj(DateUtil.format(po.getSprspsj(), DateUtil.timestampFormat));
            }
            if (po.getJsrjssj() != null) {
                vo.setJsrjssj(DateUtil.format(po.getJsrjssj(), DateUtil.timestampFormat));
            }
        }
        return vo;
    }

    /**
     * @param po
     * @return
     */
    public static ManualHistoryVO do2vo(ManualResearchDO po) {
        ManualHistoryVO vo = null;
        if (po != null) {
            vo = new ManualHistoryVO();
            vo.setDyrq(DateUtil.format(po.getDyrq(), DateUtil.webFormat));
            vo.setDymd(po.getDymd());
            vo.setKsrq(DateUtil.format(po.getKsrq(), DateUtil.webFormat));
            vo.setJsrq(DateUtil.format(po.getJsrq(), DateUtil.webFormat));
            vo.setDyfw(po.getFyfw());
            vo.setDyxq(po.getDyxq());
            vo.setDyzt(po.getDyzt());
        }
        return vo;
    }

    /**
     * @param dos
     * @return
     */
    public static List<ManualHistoryVO> dosToManualHistoryVOs(List<ManualResearchDO> dos) {
        List<ManualHistoryVO> vos = new ArrayList<ManualHistoryVO>();
        for (ManualResearchDO manualdo : dos) {
            ManualHistoryVO vo = do2vo(manualdo);
            vos.add(vo);
        }
        return vos;
    }

    /**
     * 案件类型转换
     *
     * @param pos
     * @return
     */
    public static List<AjlxVO> dos2Ajlxvos(List<AjlxbDO> pos) {
        List<AjlxVO> vos = new ArrayList<AjlxVO>();
        for (AjlxbDO src : pos) {
            AjlxVO des = do2vo(src);
            vos.add(des);
        }
        return vos;
    }

    public static AjlxVO do2vo(AjlxbDO po) {
        AjlxVO vo = null;
        if (po != null) {
            vo = new AjlxVO(po.getId(), po.getSjid(), po.getMc(), po.getDz());
        }
        return vo;
    }

    /**
     * 模板do转vo
     *
     * @param vo
     * @return
     */
    public static BbscTemplateVO do2vo(BbscTemplateDO po) {
        BbscTemplateVO vo = null;
        if (po != null) {
            vo = new BbscTemplateVO();
            vo.setName(po.getName());
            String yjCondition = "";
            for (String yjl : po.getYjCondition().split("\\+")) {
                String[] key_value = yjl.split("/");
                yjCondition += "(" + key_value[1] + ")+";
            }
            if (StringUtil.isNotBlank(yjCondition)) {
                yjCondition = yjCondition.substring(0, yjCondition.length() - 1);
            }
            vo.setYjCondition(yjCondition);
            vo.setColCondition(po.getColCondition());
            String bblx = "法院";
            String bblxpo = po.getBblx();
            if (bblxpo.contains("researchfy")) {
                bblx = "法院";
            } else if (bblxpo.contains("researchay")) {
                bblx = "案由";
            } else if (bblxpo.contains("researchbm")) {
                bblx = "部门";
            }
            vo.setBblx(bblx);
            vo.setFyfw(FYDataSourceEnum.getFymcByFydm(po.getFyfw()));
            vo.setCjsj(DateUtil.format(po.getCjsj(), DateUtil.webFormat));
            vo.setUsername(po.getUsername());
            vo.setUserfymc(getDataSourceByFydm(po.getUserfydm()).getFymc());
        }
        return vo;
    }

    public static BmModel doToBmModel(DmbDO bmDmb) {
        BmModel bmModel = new BmModel();
        bmModel.setBmmc(bmDmb.getDmms());
        bmModel.setFybh(bmDmb.getFybh() == null ? -1 : bmDmb.getFybh());
        bmModel.setBmbh(bmDmb.getDmbh());
        return bmModel;
    }


    public static SjjzBdModel doToSjjzBdModel(SjjzBdDO sjjzBdDO){
        if(sjjzBdDO != null){
            SjjzBdModel sjjzBdModel = new SjjzBdModel();
            String kssj = String.valueOf(sjjzBdDO.getKsNd())+"-"+String.valueOf(sjjzBdDO.getKsYf())+"-"+String.valueOf(sjjzBdDO.getKsTs());
            String jssj = String.valueOf(sjjzBdDO.getJsNd())+"-"+String.valueOf(sjjzBdDO.getJsYf())+"-"+String.valueOf(sjjzBdDO.getJsTs());

            sjjzBdModel.setKssj(kssj);
            sjjzBdModel.setJssj(jssj);

            sjjzBdModel.setJzrhJc(sjjzBdDO.getJzrhJc());
            sjjzBdModel.setJzrhWj(sjjzBdDO.getJzrhWj());
            sjjzBdModel.setJzrhXs(sjjzBdDO.getJzrhXs());
            sjjzBdModel.setJzrhYj(sjjzBdDO.getJzrhYj());

            sjjzBdModel.setBdJc(sjjzBdDO.getBdJc());
            sjjzBdModel.setBdWj(sjjzBdDO.getBdWj());
            sjjzBdModel.setBdXs(sjjzBdDO.getBdXs());
            sjjzBdModel.setBdYj(sjjzBdDO.getBdYj());

            sjjzBdModel.setCyJc(sjjzBdDO.getCyJc());
            sjjzBdModel.setCyWj(sjjzBdDO.getCyWj());
            sjjzBdModel.setCyXs(sjjzBdDO.getCyXs());
            sjjzBdModel.setCyYj(sjjzBdDO.getCyYj());
            return sjjzBdModel;
        }else{
            return null;//如果传过来的是空，则返回空
        }


    }

    public static BmModel doToBmModel(JzrhBmbDO bmDmb) {
        BmModel bmModel = new BmModel();
        bmModel.setBmmc(bmDmb.getBmmc());
        bmModel.setFybh(bmDmb.getFybh() == null ? -1 : bmDmb.getFybh());
        bmModel.setBmbh(String.valueOf(bmDmb.getBmbh()));
        return bmModel;
    }


    public static DmbModel doToModel(DmbDO po) {
        DmbModel vo = null;
        if (po != null) {
            vo = new DmbModel();
            vo.setLbbh(StringUtil.trim(po.getLbbh()));
            vo.setDmbh(StringUtil.trim(po.getDmbh()));
            vo.setDmms(StringUtil.trim(po.getDmms() == null ? "" : po.getDmms()));
            vo.setXgdm(StringUtil.trim(po.getXgdm()));
            vo.setBz(StringUtil.trim(po.getBz()));
            vo.setModflag(StringUtil.trim(po.getModflag()));
            vo.setTransflag(StringUtil.trim(po.getTransflag()));
            vo.setXgsj(po.getXgsj());
            vo.setXssx(po.getXssx() == null ? 0 : po.getXssx());
            vo.setDqbs(StringUtil.trim(po.getDqbs()));
            vo.setFybh(po.getFybh() == null ? -1 : po.getFybh());
            vo.setFysx(StringUtil.trim(po.getFysx()));
            vo.setFylbdm(StringUtil.trim(po.getFylbdm()));
            vo.setXzqdmbh(po.getXzqdmbh());
            vo.setDmbbh(po.getDmbbh());
            vo.setXgdm2(StringUtil.trim(po.getXgdm2()));
        }
        return vo;
    }

    public static List<DmbModel> dosToDmModels(List<DmbDO> dos) {
        List<DmbModel> dmModels = new ArrayList<DmbModel>();
        for (DmbDO src : dos) {
            DmbModel des = doToModel(src);
            dmModels.add(des);
        }
        return dmModels;
    }

    public static ResearchXXZBModel doToModel(ResearchXXZBDO po) {
        ResearchXXZBModel model = null;
        if (po != null) {
            model = new ResearchXXZBModel(po.getLbmc(), po.getXxs(), po.getUsername(), po.getUserfydm());
        }
        return model;
    }

    public static ResearchXXBModel doToModel(ResearchXXBDO po) {
        ResearchXXBModel model = null;
        if (po != null) {
            model = new ResearchXXBModel(po.getXxmc(), po.getXsmc(), po.getCondition(),
                    po.getCol(), po.getUsername(), po.getUserfydm(), po.getBbbh());
        }
        return model;
    }

    public static ResearchTableDefModel doToModel(ResearchTableDefDO po) {
        ResearchTableDefModel model = null;
        if (po != null) {
            model = new ResearchTableDefModel(po.getBbbh(), po.getBbmc(), po.getBblx(),
                    po.getUsername(), po.getUserfydm(), po.getCondition(),
                    po.getColCondition(), po.getRowCondition(), po.getRowConditionAyjzrh());
        }
        return model;
    }

    /**
     * @param model
     * @return
     */
    public static ResearchTableVO model2vo(ResearchTable model) {
        ResearchTableVO vo = new ResearchTableVO();
        vo.setBbmc(model.getBbmc());
        vo.setRowlevel(model.getRowlevel());
        vo.setCollevel(model.getCollevel());
        vo.setDytj(model.getDytj());
        List<List<ResearchTableCellVO>> valueList = new ArrayList<List<ResearchTableCellVO>>();

        //得到所有行数，新建列表
        int rowNum = model.getCollevel() + model.getRowList().size();
        for (int i = 0; i < rowNum; i++) {
            List<ResearchTableCellVO> row = new ArrayList<ResearchTableCellVO>();
            valueList.add(row);
        }

        //建立第一个空白格
        ResearchTableCellVO cell = new ResearchTableCellVO("", model.getRowlevel(), model.getCollevel(), null, null, null);
        valueList.get(0).add(cell);

        //建立列的
        for (ResearchTableColumn colmodel : model.getColList()) {
            //新建cell
            int height = model.getCollevel() - colmodel.getLevel() + 1;
            cell = new ResearchTableCellVO(colmodel.getColName(), 1, height, null, null, null);
            valueList.get(colmodel.getLevel() - 1).add(cell);
            //新建父cell
            for (int j = 0; j < colmodel.getFatherName().size(); j++) {
                cell = new ResearchTableCellVO(colmodel.getFatherName().get(j), colmodel.getBrotherSize().get(j), 1, null, null, null);
                int level = colmodel.getLevel() - colmodel.getFatherName().size() - 1 + j;
                valueList.get(level).add(cell);
            }
        }

        for (ResearchTableRow rowmodel : model.getRowList()) {
            ResearchTableColumn rowinfo = rowmodel.getRowInfo();
            int level = model.getCollevel() + rowinfo.getColNum() - 1; //列行数+行数-1

            //建父cell
            for (int j = 0; j < rowinfo.getFatherName().size(); j++) {
                cell = new ResearchTableCellVO(rowinfo.getFatherName().get(j), 1, rowinfo.getBrotherSize().get(j), null, null, null);
                valueList.get(level).add(cell);
            }
            //建自己
            int width = model.getRowlevel() - rowinfo.getLevel() + 1;
            cell = new ResearchTableCellVO(rowinfo.getColName(), width, 1, null, null, null);
            valueList.get(level).add(cell);

            //建值
            for (ResearchTableCell value : rowmodel.getValue()) {
                if (value.getValue().equals("0")) { //该 cell 的当前 value 如果为 0 就设置为空，去年同期的值也要设置
                    cell = new ResearchTableCellVO(value.getValue(), value.getSamePeriodLastYearValue(),1, 1, null, null, null);
                } else if (value.getCondition() == null) { //该 cell 的当前查询条件为 null，即该 cell 属于总计(不需要跳转查详情)
                    cell = new ResearchTableCellVO(value.getValue(), value.getSamePeriodLastYearValue(), 1, 1, null, null, null);
                } else { //否则，为默认的输出带连接的值
                    cell = new ResearchTableCellVO(value.getValue(), value.getSamePeriodLastYearValue(), 1, 1, value.getBase().replace("%", "%25"), value.getCondition(), value.getFydm());
                }
                valueList.get(level).add(cell);
            }
        }

        vo.setValueList(valueList);
        return vo;
    }

    public static List<List<ExcelCell>> vo2Excel(ResearchTableVO tablevo) {
        List<List<ResearchTableCellVO>> list = tablevo.getValueList();
        List<List<ExcelCell>> excelList = new ArrayList<List<ExcelCell>>();        //结果集
        boolean[][] hasValue = new boolean[list.size()][list.get(list.size() - 1).size() + tablevo.getRowlevel()];    //第二个值是最大值不是准确值

        //初始化
        for (int i = 0; i < hasValue.length; i++) {
            for (int j = 0; j < hasValue[0].length; j++) {
                hasValue[i][j] = false;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            boolean isHeader = i < tablevo.getCollevel();    //是否为标题行
            List<ResearchTableCellVO> row = list.get(i);
            List<ExcelCell> excelRow = new ArrayList<ExcelCell>();
            for (ResearchTableCellVO cell : row) {
                int j = getEmptyCell(hasValue[i]);    //获取此行起始位置
                ExcelCell excelCell = new ExcelCell(i, j, cell.getValue(), isHeader);
                if (cell.getColspan() > 1 || cell.getRowspan() > 1) {    //需要合并单元格
                    excelCell.setMerged(true);
                    excelCell.setEnd_x(i + cell.getRowspan() - 1);
                    excelCell.setEnd_y(j + cell.getColspan() - 1);
                }
                excelRow.add(excelCell);
                //把占值位标注
                for (int tmp_x = i; tmp_x <= excelCell.getEnd_x(); tmp_x++) {
                    for (int tmp_y = j; tmp_y <= excelCell.getEnd_y(); tmp_y++) {
                        hasValue[tmp_x][tmp_y] = true;
                    }
                }
            }
            excelList.add(excelRow);
        }
        return excelList;
    }

    private static int getEmptyCell(boolean[] row) {
        int i = 0;
        for (; i < row.length; i++) {
            if (row[i] == false) {
                return i;
            }
        }
        return i;
    }
}
