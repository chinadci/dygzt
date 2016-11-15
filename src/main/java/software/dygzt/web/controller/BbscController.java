package software.dygzt.web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import software.dygzt.data.bbsc.dataobject.BbscTemplateDO;
import software.dygzt.data.dmb.dao.DyDmbDao;
import software.dygzt.data.dmb.dataobject.DyDmbDO;
import software.dygzt.data.research.dataobject.ResearchTableDO;
import software.dygzt.dynamicds.CustomerContextHolder;
import software.dygzt.dynamicds.DataSourceRouter;
import software.dygzt.dynamicds.FYDataSourceEnum;
import software.dygzt.service.dmb.model.DmVO;
import software.dygzt.service.fy.FyService;
import software.dygzt.service.manualResearch.ManualService;
import software.dygzt.service.research.ResearchService;
import software.dygzt.service.research.ResearchVariableFactory;
import software.dygzt.service.research.ResearchVariableService;
import software.dygzt.service.research.model.*;
import software.dygzt.service.share.Convertor;
import software.dygzt.service.share.model.ChartDataVO;
import software.dygzt.service.share.model.ChartSeriesVO;
import software.dygzt.service.share.model.ContextHolder;
import software.dygzt.service.share.model.DatatablesPagedVO;
import software.dygzt.service.user.XtyhService;
import software.dygzt.service.user.model.DyXtyhVO;
import software.dygzt.service.user.model.UserContextModel;
import software.dygzt.util.*;
import software.dygzt.web.ResponseBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
public class BbscController implements InitializingBean {
    @Autowired
    private ResearchVariableFactory factory;
    @Autowired
    private ResearchService researchBaseImpl;
    @Autowired
    private ManualService manualServiceImpl;
    @Autowired
    private FyService fyServiceImpl;
    @Autowired
    private DyDmbDao dydmbDao;
    @Autowired
    private XtyhService xtyhService;//用户管理service

    //在 afterPropertiesSet 方法中进行初始化的
    private Map<String, String> databaseMap = new HashMap<String, String>();

    private static Logger logger = Logger.getLogger(BbscController.class);

    /**
     * 显示报表生成的form
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/bbsc.do", method = RequestMethod.GET)
    public String showAutoIndex(HttpServletRequest request,
                                HttpServletResponse response, ModelMap model) {
        String tab = "bbsc_custom";
        ContextHolder.setTabContext2(tab);
        String[] lbList = new String[]{"qbtj", "xstj", "mstj", "xztj", "zxtj", "pctj", "qttj"};
        String[] lbmcList = new String[]{"所有", "刑事", "民事", "行政", "执行", "赔偿", "其他"};
        //xxbbMap,researchBaseImpl.getXxbbMap(),这个是为了初始化页面二级列展开面板的数据,一个 List ,list 里面的内容用 map 来保存,
        model.addAttribute("xxbbMap", researchBaseImpl.getXxbbMap());
        model.addAttribute("lbList", lbList);
        model.addAttribute("lbmcList", lbmcList);
        return "bbsc/bbscCustom";
    }

    /**
     * 自定义报表生成,BbscConditionVO condition 对象会根据前端页面传来的值进行自动匹配,字段一样的会 new 一个对象并自动复值。
     *
     * @param request
     * @param response
     * @param model
     * @param condition
     * @return
     */
    @RequestMapping(value = "/bbscCustom.aj", method = RequestMethod.POST)
    public String show(HttpServletRequest request,
                       HttpServletResponse response, ModelMap model, BbscConditionVO condition) {
        return createTable(condition, model, "pop/researchTable", "inc/error.inc");
    }

    /**
     * 通过模板报表生成(ok)
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/bbscTemplate.do", method = RequestMethod.GET)
    public String showByTemplate(HttpServletRequest request,
                                 HttpServletResponse response, ModelMap model) {
        int id = Integer.parseInt(request.getParameter("id"));
        String ksrq = request.getParameter("ksrq");    //开始日期
        String jsrq = request.getParameter("jsrq");    //结束日期
        BbscConditionVO condition = researchBaseImpl.getConditionByTemplate(id);
        condition.setKsrq(ksrq);
        condition.setJsrq(jsrq);
        if (DateUtil.getDiffDays(DateUtil.parse(condition.getJsrq(),
                DateUtil.webFormat), new Date()) != 0) {
            model.addAttribute("hasHistory", true);    //	截止日期不为今天，才可以保存
        }
        return createTable(condition, model, "historyAutoTable", "error");
    }

    /**
     * 生成报表函数(ok)
     *
     * @param condition
     * @param model
     * @return
     */
    private String createTable(BbscConditionVO condition, ModelMap model, String success_jsp, String error_jsp) {
        //因为要统计 jzrh 中的数据,所以要 bblx 的值进行更改,统一改为对应的 jzrh 字串,然后对 condition 进行更改。
        String bblx = databaseMap.get(condition.getBblx());
        condition.setBblx(bblx);
        //因为使用 factory 实现的,factory 通过键值对的形式保存 ResearchVariableService 接口的 Imply 实体对象,作用是创建表。
        ResearchVariableService researchService = factory.getServiceByName(condition.getBblx());
        //researchBaseImpl是 ResearchService 类的实体,里面的方法包含了所有用来获取表格生成需要的数据。
        ResearchTableDO po = researchBaseImpl.findTable(condition.toString());
        if (po == null) {    //如果没生成过,则根据 conditon 来创建一个 ResearchTable 对象
            try {
                ResearchTable table = researchService.createTable(condition);
                if (DateUtil.getDiffDays(DateUtil.parse(condition.getJsrq(), DateUtil.webFormat), new Date()) != 0) {
                    Runnable r = new SaveThread(table, -1, 2);
                    Thread t = new Thread(r);
                    t.start();
                }
                model.addAttribute("table", Convertor.model2vo(table));//返回到前台页面的是 ResearchTableVO 对象。
                return success_jsp;
            } catch (Exception e) {
                logger.error("生成表格错误：", e);
                model.addAttribute("error", e.getMessage());
                return error_jsp;
            }
        } else if (StringUtil.isNotBlank(po.getSbfy())) {    //生成失败过
            String fylist[] = po.getSbfy().split(";");
            boolean connect = false;
            String curDb = CustomerContextHolder.getCustomerType();
            for (String fy : fylist) {
                if (fy.equals("jzrh")) {
                    DataSourceRouter.routerToJzrh();
                } else {
                    DataSourceRouter.routerTo(fy);// 各家法院库
                }
                logger.info("连接" + fy);
                if (researchBaseImpl.databaseIsConnected()) {
                    connect = true;
                    break;
                }
            }
            //切换数据库
            DataSourceRouter.routerTo(curDb);
            if (connect) {    //如果曾经没连上的连上了，重新create
                try {
                    ResearchTable table = researchService.createTable(condition);
                    Runnable r = new SaveThread(table, po.getId(), 2);
                    Thread t = new Thread(r);
                    t.start();
                    model.addAttribute("table", Convertor.model2vo(table));
                    return success_jsp;
                } catch (Exception e) {
                    logger.error("生成表格错误：", e);
                    model.addAttribute("error", e.getMessage());
                    return error_jsp;
                }
            } else {
                model.addAttribute("table", researchBaseImpl.findSavedTable(po.getId()));
                return success_jsp;
            }
        } else {
            model.addAttribute("table", researchBaseImpl.findSavedTable(po.getId()));
            return success_jsp;
        }
    }

    /**
     * 显示我的模板列表
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/bbscByMyTemplate.do", method = RequestMethod.GET)
    public String showMyTemplate(HttpServletRequest request,
                                 HttpServletResponse response, ModelMap model) {
        String tab = "bbsc_template";
        ContextHolder.setTabContext2(tab);
        model.addAttribute("modelname", "我的模板");
        model.addAttribute("method", "bbscByMyTemplate.aj");
        return "bbsc/templateMyList";
    }

    /**
     * 显示我的模板列表（分页）
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bbscByMyTemplate.aj", method = RequestMethod.GET)
    public void showMyTemplateList(HttpServletRequest request,
                                   HttpServletResponse response, ModelMap model, DatatablesPagedVO pagedVO) throws IOException {
        UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
        List<BbscTemplateDO> dos = researchBaseImpl.myTemplateList(user.getYhmc(), user.getFydm());
        List<BbscTemplateVO> vos = new ArrayList<BbscTemplateVO>();
        for (BbscTemplateDO templatedo : dos) {
            BbscTemplateVO vo = Convertor.do2vo(templatedo);
            vo.setBtn("<div class='sjxz'><a class='bbsc_btn' href='javascript:void(0)' data-id='" + templatedo.getId() + "'>生成报表</a></div>");
            vos.add(vo);
        }
        //搜索的抬头
        List<String> searchHeader = new ArrayList<String>();
        searchHeader.add("cjsj");
        searchHeader.add("name");
        searchHeader.add("yjCondition");
        searchHeader.add("colCondition");
        searchHeader.add("bblx");
        searchHeader.add("fyfw");
        //排序的抬头
        String[] sortHeader = new String[]{"cjsj", "name", "yjCondition", "colCondition", "bblx", "fyfw"};
        //搜索、排序、分页
        DataTablePageUtil.search_sort_page(vos, pagedVO, searchHeader, sortHeader, response);
    }

    /**
     * 显示所有模板列表(ok)
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/bbscByAllTemplate.do", method = RequestMethod.GET)
    public String sshowAllTemplate(HttpServletRequest request,
                                   HttpServletResponse response, ModelMap model) {
        String tab = "bbsc_template";
        ContextHolder.setTabContext2(tab);
        model.addAttribute("modelname", "所有模板");
        model.addAttribute("method", "bbscByAllTemplate.aj");
        return "bbsc/templateAllList";
    }

    /**
     * 显示所有模板列表（分页）(ok)
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bbscByAllTemplate.aj", method = RequestMethod.GET)
    public void showAllTemplateList(HttpServletRequest request,
                                    HttpServletResponse response, ModelMap model, DatatablesPagedVO pagedVO) throws IOException {
        List<BbscTemplateDO> dos = researchBaseImpl.allTemplateList();
        List<BbscTemplateVO> vos = new ArrayList<BbscTemplateVO>();
        for (BbscTemplateDO templatedo : dos) {
            BbscTemplateVO vo = Convertor.do2vo(templatedo);
            vo.setBtn("<div class='sjxz'><a class='bbsc_btn' href='javascript:void(0)' data-id='" + templatedo.getId() + "'>生成报表</a></div>");
            vos.add(vo);
        }
        //搜索的抬头
        List<String> searchHeader = new ArrayList<String>();
        searchHeader.add("cjsj");
        searchHeader.add("name");
        searchHeader.add("yjCondition");
        searchHeader.add("colCondition");
        searchHeader.add("bblx");
        searchHeader.add("fyfw");
        searchHeader.add("username");
        searchHeader.add("userfymc");
        //排序的抬头
        String[] sortHeader = new String[]{"cjsj", "name", "yjCondition", "colCondition", "bblx", "fyfw", "username", "userfymc"};
        //搜索、排序、分页
        DataTablePageUtil.search_sort_page(vos, pagedVO, searchHeader, sortHeader, response);
    }

    /**
     * 显示我的模板管理列表(ok)
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/templateManagement.do", method = RequestMethod.GET)
    public String showMyTemplateManagement(HttpServletRequest request,
                                           HttpServletResponse response, ModelMap model) {
        String tab = "templateManagement";
        ContextHolder.setTabContext2(tab);
        model.addAttribute("modelname", "模板管理");
        model.addAttribute("method", "templateManagement.aj");
        return "bbsc/templateManagement";
    }

    /**
     * 显示我的模板管理列表（分页）(ok)
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/templateManagement.aj", method = RequestMethod.GET)
    public void showMyTemplateManagementList(HttpServletRequest request,
                                             HttpServletResponse response, ModelMap model, DatatablesPagedVO pagedVO) throws IOException {
        UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
        List<BbscTemplateDO> dos = researchBaseImpl.myTemplateList(user.getYhmc(), user.getFydm());
        List<BbscTemplateVO> vos = new ArrayList<BbscTemplateVO>();
        for (BbscTemplateDO templatedo : dos) {
            BbscTemplateVO vo = Convertor.do2vo(templatedo);
            vo.setName("<input type='text' name='name' value='" + templatedo.getName() + "' data-edit='true' disabled><input type='hidden' name='id' value='" + templatedo.getId() + "'/>");
            vo.setBtn("<div class='cpt_menu_update' title='修改'></div><div class='cpt_menu_del' title='删除'></div>");
            vos.add(vo);
        }
        //搜索的抬头
        List<String> searchHeader = new ArrayList<String>();
        searchHeader.add("cjsj");
        searchHeader.add("name");
        searchHeader.add("yjCondition");
        searchHeader.add("colCondition");
        searchHeader.add("bblx");
        searchHeader.add("fyfw");
        //排序的抬头
        String[] sortHeader = new String[]{"cjsj", "name", "yjCondition", "colCondition", "bblx", "fyfw"};
        //搜索、排序、分页
        DataTablePageUtil.search_sort_page(vos, pagedVO, searchHeader, sortHeader, response);
    }

    /**
     * 删除模板(ok)
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/deleteTemplate.aj", method = RequestMethod.POST)
    public void deleteTemplate(HttpServletRequest request,
                               HttpServletResponse response, ModelMap model) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        boolean success = false;
        String result = researchBaseImpl.deleteTemplate(id);
        if (result == null) {
            success = true;
            result = "已删除";
        }

        JsonObject json = new JsonObject();
        json.addProperty("success", success);
        json.addProperty("text", result);
        ResponseBuilder builder = new ResponseBuilder();
        builder.writeJsonResponse(response, json.toString());
    }

    /**
     * 修改模板(ok)
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/changeTemplate.aj", method = RequestMethod.POST)
    public void changeTemplate(HttpServletRequest request,
                               HttpServletResponse response, ModelMap model) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        boolean success = false;
        String data = researchBaseImpl.modifyTemplate(id, name);
        if (data == null) {
            success = true;
            data = "已修改";
        }

        JsonObject json = new JsonObject();
        json.addProperty("success", success);
        json.addProperty("text", data);
        ResponseBuilder builder = new ResponseBuilder();
        builder.writeJsonResponse(response, json.toString());
    }

    /**
     * 保存模板时填写描述(ok)
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/saveTemplateForm.aj", method = RequestMethod.POST)
    public String saveTemplateForm(HttpServletRequest request,
                                   HttpServletResponse response, ModelMap model) {
        return "bbsc/pop/saveTemplateDiscribe";
    }

    /**
     * 保存模板(ok)
     *
     * @param request
     * @param response
     * @param model
     * @throws IOException
     */
    @RequestMapping(value = "/saveTemplate.aj", method = RequestMethod.POST)
    public void saveResearch(HttpServletRequest request,
                             HttpServletResponse response, BbscConditionVO condition, ModelMap model) throws IOException {
        String name = request.getParameter("name");
        boolean success = false;
        String data = researchBaseImpl.saveTemplate(condition, name);
        if (data == null) {
            success = true;
            data = "保存成功";
        }
        JsonObject json = new JsonObject();
        json.addProperty("success", success);
        json.addProperty("text", data);
        ResponseBuilder builder = new ResponseBuilder();
        builder.writeJsonResponse(response, json.toString());
    }

    /**
     * 显示报表生成历史记录(ok)
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/bbscHistory.do", method = RequestMethod.GET)
    public String showBbscHistory(HttpServletRequest request,
                                  HttpServletResponse response, ModelMap model) {
        String tab = "bbscHistory";
        ContextHolder.setTabContext2(tab);
        return "bbsc/history";
    }

    /**
     * 显示报表生成历史纪录list（分页）(ok)
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bbscHistoryList.aj", method = RequestMethod.GET)
    public void showBbscHistoryList(HttpServletRequest request,
                                    HttpServletResponse response, ModelMap model, DatatablesPagedVO pagedVO) throws IOException {
        //获取列表
        String ksrq = request.getParameter("ksrq");    //开始日期
        String jsrq = request.getParameter("jsrq");    //结束日期
        UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
        List<Object[]> dos = researchBaseImpl.savedTables(user.getYhmc(), user.getFydm(), ksrq, jsrq, 2);
        List<AutoHistoryVO> vos = new ArrayList<AutoHistoryVO>();
        for (Object[] researchdo : dos) {
            AutoHistoryVO vo = new AutoHistoryVO();
            vo.setDyrq(DateUtil.format((Date) researchdo[1], DateUtil.webFormat));
            vo.setBgmc(String.valueOf(researchdo[2]));
            vo.setBgms(String.valueOf(researchdo[3]).equals("null") ? "" : String.valueOf(researchdo[3]));
            vo.setKsrq(DateUtil.format((Date) researchdo[4], DateUtil.webFormat));
            vo.setJsrq(DateUtil.format((Date) researchdo[5], DateUtil.webFormat));
            vo.setBtn("<div class='cpt_menu_view' data-url='historyAutoTable.do?tableid=" + (Integer) researchdo[0] + "' title='查看'>" +
                    "</div><div class='cpt_menu_del' title='删除' data-tableid='" + (Integer) researchdo[0] + "'></div>");
            vos.add(vo);
        }
        //排序的抬头
        String[] sortHeader = new String[]{"dyrq", "bgmc", "bgms", "ksrq", "jsrq"};
        //搜索、排序、分页
        DataTablePageUtil.search_sort_page(vos, pagedVO, null, sortHeader, response);
    }

    class SaveThread implements Runnable {

        private ResearchTable table;
        private int tableid;
        private int type;

        public SaveThread(ResearchTable table, int tableid, int type) {
            super();
            this.table = table;
            this.tableid = tableid;
            this.type = type;
        }

        public void run() {
            researchBaseImpl.saveTable(tableid, table, type);
        }

    }

    //当通过 Spring 容器的组件扫并实例化 BbscController 类对象后，就会调用 afterPropertiesSet 方法，这里利用这个特点对databaseMap进行初始化
    public void afterPropertiesSet() throws Exception {
        List<DyDmbDO> dmbs = dydmbDao.getDmListByLbbh("ResearchDB");
        for (DyDmbDO dydmb : dmbs) {
            databaseMap.put(dydmb.getDmbh(), dydmb.getDmms());
        }
    }


    /**
     * 自助查询form
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/bbscSearch.do", method = RequestMethod.GET)
    public String showSearchForm(HttpServletRequest request,
                                 HttpServletResponse response, ModelMap model) {
        String tab2 = "bbscSearch";
        ContextHolder.setTabContext2(tab2);
        model.addAttribute("fylist", fyServiceImpl.getTjFyList());
        model.addAttribute("fylistHxq", fyServiceImpl.getTjFyListHxq());//含辖区的法院list
        return "bbsc/bbscAutoSearch";//跳转到 /WEB-INF/views/bbsc/bbscAutoSearch.jsp,因为前缀是/WEB-INF/views/,后缀是.jsp
    }

    /**
     * 显示自助统计查询list（BBSC有关的分页）搜索和直接显示都共用这个.aj,如果有搜索条件会自动复制到 SearchConditionVO condition 对象中,
     * 没有的话,全部搜索出来
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bbscAutoSearchList.aj", method = RequestMethod.GET)
    public void showSearchList(HttpServletRequest request,
                               HttpServletResponse response, ModelMap model,
                               SearchConditionVO condition, DatatablesPagedVO pagedVO, BindingResult result) throws IOException {
        /*
        *dos是对象数组结果集,其中对象数组保存的是DY_AUTO_SAVEDINFO中的各个字段
        *字段分别为tableid, dyrq,bbmc,ksrq,jsrq,ajzt,dyr,dyrfydm
         *  */
        List<Object[]> dos = researchBaseImpl.search(condition, 2);//2为 type 的类型,对应统计工作台和报表统计的区分数据(ResearchSavedInfoDO中)
        List<AutoHistoryVO> vos = new ArrayList<AutoHistoryVO>();
        for (Object[] researchdo : dos) {
            AutoHistoryVO vo = new AutoHistoryVO();
            vo.setDyrq(DateUtil.format((Date) researchdo[1], DateUtil.webFormat));
            vo.setBgmc(String.valueOf(researchdo[2]));
            vo.setKsrq(DateUtil.format((Date) researchdo[3], DateUtil.webFormat));
            vo.setJsrq(DateUtil.format((Date) researchdo[4], DateUtil.webFormat));
            vo.setAjzt(String.valueOf(researchdo[5]));
            vo.setDyr(String.valueOf(researchdo[6]));
            vo.setDyrfydm(FYDataSourceEnum.getDataSourceByFydm(String.valueOf(researchdo[7])).getFymc());
            vo.setBtn("<div class='cpt_menu_view' data-url='historyAutoTable.do?tableid=" + (Integer) researchdo[0] + "' title='查看'></div>");
            vos.add(vo);
        }
        //设置排序的抬头
//		String[] sortHeader = new String[]{"dyrq","bgmc","ksrq","jsrq","ajzt","dyr","dyrfydm"};
        String[] sortHeader = new String[]{"dyrq", "bgmc", "ksrq", "jsrq", "dyr", "dyrfydm"};

        //搜索、排序、分页
        DataTablePageUtil.search_sort_page(vos, pagedVO, null, sortHeader, response);
    }


    //从这里开始是统计人统计图表所需要获取的数据(包含两个)

    /**
     * 跳转到统计人数据统计图表:统计人法院的统计图表页面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/bbscStatisticsDyrfy.do", method = RequestMethod.GET)
    public String showStatisticsDyrfyForm(HttpServletRequest request,
                                          HttpServletResponse response, ModelMap model) {
        String tab2 = "sta_dyrfy";//为了和dygzt 区分,这里用tab2
        ContextHolder.setTabContext2(tab2);
        return "bbsc/bbscStatisticsDyrfy";
    }

    /**
     * 统计人数据统计图表:统计x轴需要的数据
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bbscStatisticXAxisDyrfy.aj", method = RequestMethod.POST)
    public void showStatisticXAxisDyrfy(HttpServletRequest request,
                                        HttpServletResponse response, ModelMap model) throws IOException {
        boolean success = false;
        String result = "\"信息获取错误\"";
        List<DmVO> fys = fyServiceImpl.getTjFyList();
        List<String> fyList = new ArrayList<String>();
        for (DmVO fy : fys) {
            fyList.add(fy.getMs());
        }
        if (fyList.size() > 0) {
            success = true;
            Gson gson = new Gson();
            result = gson.toJson(fyList);
        }
        String text = "{\"success\":" + success + ",\"text\":" + result + "}";
        ResponseBuilder builder = new ResponseBuilder();
        builder.writeJsonResponse(response, text);
    }


    /**
     * 统计人数据统计图表:统计分析统计人法院(自助统计结果数据)
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bbscStatisticsResearchDyrfy.aj", method = RequestMethod.POST)
    public void showStatisticsDyrfyAuto(HttpServletRequest request,
                                        HttpServletResponse response, ModelMap model) throws IOException {
        boolean success = false;
        String result = "\"信息获取错误\"";
        String ksrq = request.getParameter("ksrq");    //开始日期
        String jsrq = request.getParameter("jsrq");    //结束日期
        //自助，为0不null
        ChartSeriesVO autosery = new ChartSeriesVO();
        autosery.setName("自助统计");
        autosery.setStack("自助统计");
        List<DmVO> fys = fyServiceImpl.getTjFyList();
        List data = new ArrayList();
        for (DmVO fy : fys) {
            SearchConditionVO condition = new SearchConditionVO();
            condition.setKsrq(ksrq);
            condition.setJsrq(jsrq);
            condition.setDyrfydm(fy.getBh());
            List<Object[]> dos = researchBaseImpl.search(condition, 2);//这取得统计图中每个法院要显示的数据
            ChartDataVO da = new ChartDataVO(dos.size(), "bbscAutoStatisticForm.do?dyrfydm=" + fy.getBh() + "&ksrq=" + ksrq + "&jsrq=" + jsrq);
            data.add(da);
        }
        autosery.setData(data);
        success = true;
        Gson gson = new Gson();
        result = gson.toJson(autosery);
        String text = "{\"success\":" + success + ",\"text\":" + result + "}";
        ResponseBuilder builder = new ResponseBuilder();
        builder.writeJsonResponse(response, text);
    }


    //从这里开始是时间统计表格所需要获取的数据(包含两个)

    /**
     * 跳转到统计时间统计图表:跳转到统计时间的统计图表
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/bbscStatisticsSj.do", method = RequestMethod.GET)
    public String showStatisticsSjForm(HttpServletRequest request,
                                       HttpServletResponse response, ModelMap model) {
        String tab2 = "sta_sj";
        ContextHolder.setTabContext2(tab2);
        return "bbsc/bbscStatisticsSj";
    }

    /**
     * 时间统计图表的:统计分析统计时间(x轴)
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bbscStatisticsXAxisSj.aj", method = RequestMethod.POST)
    public void showStatisticsXAxisSj(HttpServletRequest request,
                                      HttpServletResponse response, ModelMap model) throws IOException {
        String sjhf = request.getParameter("sjhf");
        Date ksrq = DateUtil.parse(request.getParameter("ksrq"), DateUtil.webFormat);
        Date jsrq = DateUtil.parse(request.getParameter("jsrq"), DateUtil.webFormat);
        boolean success = false;
        String result = "\"信息获取错误\"";
        if (StringUtil.isNotBlank(sjhf)) {
            List<String> xAxis = DateUtil.dateInterval(ksrq, jsrq, sjhf);
            if (xAxis.size() > 31) {
                result = "\"日期数过多，超出统计范围\"";
            } else {
                success = true;
                Gson gson = new Gson();
                result = gson.toJson(xAxis);
            }
        }
        String text = "{\"success\":" + success + ",\"text\":" + result + "}";
        ResponseBuilder builder = new ResponseBuilder();
        builder.writeJsonResponse(response, text);
    }

    /**
     * 时间统计图表的(自助类型结果数据)
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bbscStatisticsResearchSj.aj", method = RequestMethod.POST)
    public void showStatisticsSjAuto(HttpServletRequest request,
                                     HttpServletResponse response, ModelMap model) throws IOException {
        String sjhf = request.getParameter("sjhf");
        Date ksrq = DateUtil.parse(request.getParameter("ksrq"), DateUtil.webFormat);
        Date jsrq = DateUtil.parse(request.getParameter("jsrq"), DateUtil.webFormat);
        boolean success = false;
        String result = "\"信息获取错误\"";
        if (StringUtil.isNotBlank(sjhf)) {
            List<List<String>> intervals = DateUtil.dateIntervals(ksrq, jsrq, sjhf);
            List<String> ksrqList = intervals.get(0);
            List<String> jsrqList = intervals.get(1);
            //自助，为0不null
            ChartSeriesVO autosery = new ChartSeriesVO();
            autosery.setName("自助统计");
            List data = new ArrayList();
            for (int i = 0; i < ksrqList.size(); i++) {
                String ksrqCon = ksrqList.get(i);
                String jsrqCon = jsrqList.get(i);
                SearchConditionVO condition = new SearchConditionVO();
                condition.setKsrq(ksrqCon);
                condition.setJsrq(jsrqCon);
                List<Object[]> dos = researchBaseImpl.search(condition, 2);
                ChartDataVO da = new ChartDataVO(dos.size(), "autoStatisticForm.do?ksrq=" + ksrqCon + "&jsrq=" + jsrqCon);
                data.add(da);
            }
            autosery.setData(data);
            success = true;
            Gson gson = new Gson();
            result = gson.toJson(autosery);
        }
        String text = "{\"success\":" + success + ",\"text\":" + result + "}";
        ResponseBuilder builder = new ResponseBuilder();
        builder.writeJsonResponse(response, text);
    }

    /**
     * 自助统计统计列表表头(BBSC 使用)
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/bbscAutoStatisticForm.do", method = RequestMethod.GET)
    public String showStatistic(HttpServletRequest request,
                                HttpServletResponse response, ModelMap model,
                                SearchConditionVO condition, BindingResult result) {
        model.addAttribute("condition", condition.toString());
        return "bbsc/bbscAutoStatisticsTable";
    }



    /*BBSC 的用户管理*/

    /**
     * 跳转到法院人员管理列表界面(先获取数据，再在上面进行人员管理)
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/bbscChangePerson.do", method = RequestMethod.GET)
    public String changePersonForm(HttpServletRequest request,
                                   HttpServletResponse response, ModelMap model) {
        String tab2 = "bbscChangePerson";
        ContextHolder.setTabContext2(tab2);
        return "bbsc/bbscChangePerson"; //web-inf/views/bbsc/bbscChangePerson.jsp
    }

    /**
     * 获取法院人员列表(分页)
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bbscChangePersonList.aj", method = RequestMethod.GET)
    public void changePersonList(HttpServletRequest request,
                                 HttpServletResponse response, ModelMap model, DatatablesPagedVO pagedVO) throws IOException {
        //得到人员列表
       // String curDb = CustomerContextHolder.getCustomerType();
        //DataSourceRouter.routerToYzjcJzk(); //切换该数据库去读取要获得的数据
        List<String> qxList = new ArrayList<String>();
        qxList.add("调研人");
//		qxList.add("审批人"); //这里只要要查询调研人权限的用户
        //查找的是dyxtyhb ，getListByQx 方法是查出符合 qxList 中任一要求的用户 list
        List<DyXtyhVO> list = xtyhService.getListByQx(qxList);
        //搜索
        if (StringUtil.isNotBlank(pagedVO.getsSearch())) {
            List<String> headers = new ArrayList<String>();
            headers.add("fymc");
            headers.add("yhm");
            headers.add("name");
            list = SearchUtil.listSearching(list, pagedVO.getsSearch(), headers); //找出符合搜索条件的用户 list
        }

        //排序
        String[] sortHeader = new String[]{"xh", "fymc", "yhm", "name"};
        String sortField = CompareUtil.getSortColumn(sortHeader, pagedVO.getiSortCol_0(), sortHeader[0]);
        List<CompareModel> models = new ArrayList<CompareModel>();
        models.add(new CompareModel(pagedVO.getsSortDir_0(), sortField));
        Collections.sort(list, CompareUtil.createComparator(models));
        //分页
        List<DyXtyhVO> pagedAjs = PageUtil.listPaging(list, pagedVO.getiDisplayStart(),
                pagedVO.getiDisplayLength());

        //转换，这个转换是为了方便数据对象在前台页面进行显示和操作
        List<DyXtyhVO> result = new ArrayList<DyXtyhVO>();
        for (DyXtyhVO src : pagedAjs) {
            DyXtyhVO des = new DyXtyhVO();
            des.setXh(src.getXh());
            des.setFymc(src.getFymc()+"<input type='hidden' name='fydm' value='"+src.getFydm()+"'/>");
            des.setYhm(src.getYhm()+"<input type='hidden' name='yhm' value='"+src.getYhm()+"'/>");
            des.setName(src.getName());
            des.setBtn("<div class='cpt_menu_del' title='删除'></div>");
            result.add(des);
        }
        //DataSourceRouter.routerTo(curDb);

        //标记值加一
        pagedVO.setsEcho(pagedVO.getsEcho() + 1);//问题，echo 操作好有什么用？
        //返回值
        Map map = new HashMap();
        map.put("data", result);
        map.put("recordsTotal", list.size());
        map.put("recordsFiltered", list.size());
        map.put("sEcho", pagedVO.getsEcho());
        ResponseBuilder builder = new ResponseBuilder();
        builder.writeJsonResponse(response, map);
    }

    /**
     * 删除法院人员
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bbscDeletePerson.aj", method = RequestMethod.POST)
    public void deletePerson(HttpServletRequest request,
                             HttpServletResponse response, ModelMap model) throws IOException {
        String fydm = request.getParameter("fydm");
        String yhm = request.getParameter("yhm");

        String curDb = CustomerContextHolder.getCustomerType();
        DataSourceRouter.routerToYzjcJzk();

        boolean success = false;
        String result = null;
        DyXtyhVO user = xtyhService.getXtyh(fydm, yhm);

        /*如果该用户用除了调研权限外的其他权限，那么就只进行修改(删除调研权限)，否则直接删除该用户*/
        if (StringUtil.contains(user.getQx(), "审批人")) { //权限是否包含审批人或者其他权限(是否选出来的用户只有这两个权限?)
            user.setQx("审批人");
            result = xtyhService.changeDyXtyh(user); //修改
        } else {
            result = xtyhService.deleteDyXtyh(fydm, yhm); //不包含则直接删除
        }
        if (result == null) {
            success = true;
            result = "已删除";
        }

        DataSourceRouter.routerTo(curDb);

        String text = "{\"success\":" + success + ",\"text\":\"" + result + "\"}";
        ResponseBuilder builder = new ResponseBuilder();
        builder.writeJsonResponse(response, text);
    }


    /**
     * 增加人界面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/bbscAddPerson.do", method = RequestMethod.GET)
    public String addPersonForm(HttpServletRequest request,
                                HttpServletResponse response, ModelMap model) {
        String tab = "changePerson";
        ContextHolder.setTabContext(tab);
        return "bbsc/bbscAddPerson";
    }

    /**
     * 增加用户
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bbscAddPerson.aj", method = RequestMethod.POST)
    public void addPerson(HttpServletRequest request,
                          HttpServletResponse response, ModelMap model, DyXtyhVO vo, BindingResult result) throws IOException {
        String curDb = CustomerContextHolder.getCustomerType();
        DataSourceRouter.routerToYzjcJzk();

        boolean success = false;
        String data = null;

        DyXtyhVO user = xtyhService.getXtyh(vo.getFydm(), vo.getYhm()); //判断调研系统系统中是否有该用户

        /*如果该用户用除了调研权限外的其他权限，那么就只进行修改(添加调研权限)，否则直接添加该用户*/
        if (user == null) {//没有该用户，直接添加
            data = xtyhService.addDyXtyh(vo);//返回null 就是添加成功

        } else {//有该用户
            user.setQx("调研人;审批人");//这里有疑问？是追加还是修改，数据库中显示如果有调研人权限的话，就不会具有管理员和计算人权限，所以可以直接修改
            data = xtyhService.changeDyXtyh(user); //修改，返回null 就是修改成功
        }
        if (data == null) {
            success = true;
            data = "提交成功！";
        }
        DataSourceRouter.routerTo(curDb);
        String text = "{\"success\":" + success + ",\"text\":\"" + data + "\"}";
        ResponseBuilder builder = new ResponseBuilder();
        builder.writeJsonResponse(response, text);
    }

}

