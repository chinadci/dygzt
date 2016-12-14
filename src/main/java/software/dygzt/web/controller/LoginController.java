package software.dygzt.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import software.dygzt.dynamicds.CustomerContextHolder;
import software.dygzt.dynamicds.DataSourceEnum;
import software.dygzt.dynamicds.DataSourceRouter;
import software.dygzt.dynamicds.FYDataSourceEnum;
import software.dygzt.service.fy.FyService;
import software.dygzt.service.fy.model.FyVO;
import software.dygzt.service.share.MD5Signature;
import software.dygzt.service.share.model.ContextHolder;
import software.dygzt.service.share.model.DatatablesPagedVO;
import software.dygzt.service.user.XtyhService;
import software.dygzt.service.user.model.*;
import software.dygzt.util.*;
import software.dygzt.web.ResponseBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.*;

/**
 * 用户登陆验证的Controller
 */

@Controller
public class LoginController {
    private static Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private FyService fyService;

    @Autowired
    private XtyhService xtyhService;

    /**
     * 获取法院列表
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/xzfy.aj", method = RequestMethod.POST)
    public String chooseFy(HttpServletRequest request,
                           HttpServletResponse response, ModelMap model) throws Exception {
        String curDb = CustomerContextHolder.getCustomerType();
        DataSourceRouter.routerToTjgy();
        FyVO zgyVO = new FyVO();
        // 获得法院列表信息
        zgyVO = fyService.getFyList();
        model.addAttribute("fylist", zgyVO);
        DataSourceRouter.routerTo(curDb);
        return "pop/xzfy";
    }

    /**
     * 获取含辖区的法院列表
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/xzfyhxq.aj", method = RequestMethod.POST)
    public String chooseFyHxq(HttpServletRequest request,
                              HttpServletResponse response, ModelMap model) throws Exception {
        String curDb = CustomerContextHolder.getCustomerType();
        DataSourceRouter.routerToTjgy();
        FyVO zgyVO = new FyVO();
        // 获得法院列表信息
        zgyVO = fyService.getFyListHxq();
        model.addAttribute("fylist", zgyVO);
        DataSourceRouter.routerTo(curDb);
        return "pop/xzfy";
    }

    /**
     * 获取时间范围选择dlg
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/xzsj.aj", method = RequestMethod.GET)
    public String getCommonSjfw(HttpServletRequest request,
                                HttpServletResponse response, ModelMap model) {
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);

        //获得年界面的年和月
        List<Integer> yearList = new ArrayList<Integer>();
        List<Integer> monthList = new ArrayList<Integer>();
        for (int i = year; i >= 1990; i--) {
            yearList.add(i);
        }
        for (int i = 1; i <= 12; i++) {
            monthList.add(i);
        }
        model.addAttribute("yearList", yearList);
        model.addAttribute("monthList", monthList);
        return "pop/xzsj";
    }

    /**
     * 按部门获取法院人员
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/xzry.aj", method = RequestMethod.POST)
    public String chooseRy(HttpServletRequest request,
                           HttpServletResponse response, ModelMap model) throws Exception {
        String fydm = request.getParameter("fydm");
        String curDb = CustomerContextHolder.getCustomerType();
        DataSourceRouter.routerTo(fydm);
        List<BmVO> bms = xtyhService.getAllXtyh(fydm);
        model.addAttribute("bmlist", bms);
        DataSourceRouter.routerTo(curDb);
        return "pop/xzry";
    }

    /**
     * 登陆
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String showStartPage(HttpServletRequest request,
                                HttpServletResponse response, ModelMap model) {

        // 判断当前是否存在userContext，存在则先进行退出操作，清空userContext
        if (request.getSession().getAttribute("userContext") != null) {
            request.getSession().removeAttribute("userContext");
        }

        //通过判断访问地址 来设置登录页默认法院
        String accessIp = request.getRemoteAddr();
        FYDataSourceEnum fy = FYDataSourceEnum.getEnumByAccessIp(accessIp);
        if (fy != null) {
            model.addAttribute("fymc", fy.getFymc());
            model.addAttribute("fydm", fy.getFydm());
        } else {
            model.addAttribute("fymc", "天津市高级人民法院");
            model.addAttribute("fydm", "120000 200");
        }
        return "login";
    }

    /**
     * 获得登陆界面传入的用户名、密码和法院编号，验证用户是否有效 返回验证结果，成功进入主界面，失败返回错误界面
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws SignatureException
     */
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String showMainPage(HttpServletRequest request,
                               HttpServletResponse response, ModelMap model) {
        String yhm = request.getParameter("yhm");
        String password = request.getParameter("mm");
        String fydm = request.getParameter("fydm");

        String curDb = CustomerContextHolder.getCustomerType();
        DataSourceRouter.routerToYzjcJzk();//TJFX 库是在天津高院院长决策库的
        try {
            UserCheckResult result = xtyhService.getXtyh(fydm, yhm, password);
            // 登录成功
            if (result.isSuccess() == true) {
                UserContextModel userContext = result.getXtyh();
                request.getSession().setAttribute("userContext", userContext);
                ContextHolder.setUserContext(userContext);
                //跳转到各角色主界面
                String doName = "";
                String yhqx = userContext.getYhqx();
                if (yhqx.contains("调研人")) {
                    doName = "autoresearch.do";
                } else if (yhqx.contains("审批人")) {
                    doName = "approve.do";
                } else if (yhqx.contains("计算人")) {
                    doName = "compute.do";
                } else if (yhqx.contains("管理员")) {
                    doName = "distribute.do";
                }
                return "redirect:" + doName;
            } else {
                boolean hasUserName;
                if (result.getResultCode().getCode().equals("ILLEGAL_PASSWORD")) {
                    logger.warn(yhm + "：该用户密码错误");
                    hasUserName = true;
                    model.addAttribute("message", "用户密码错误");
                } else if (result.getResultCode().getCode().equals("EMPTY_USERSYSTEM")) {
                    logger.warn(yhm + "：无权限");
                    hasUserName = false;
                    model.addAttribute("message", "该用户没有配置系统");
                } else {
                    logger.warn(yhm + "：没有该用户");
                    hasUserName = false;
                    model.addAttribute("message", "系统不存在该用户");
                }
                model.addAttribute("hasUserName", hasUserName);
                return "login";
            }
        } catch (Exception e) {
            logger.error("验证用户登陆信息时出现异常", e);
            return "error";
        } finally {
            DataSourceRouter.routerTo(curDb);
        }
    }


    /**
     * 信任登录过程
     * 1. 获取数据
     * 2. 使用 base64 解码数据，获得原字符串
     * 3. 拆解原字串，获取需要的信息
     * 4. 根据传递的用户信息，去数据库中查询是否有该用户
     * 调研工作台中的用户是独立的，所以需要做判断:
     * 1. 如果该用户属于高院，则查询该用户所属部门，以部门的身份登录
     * 2. 如果该用户属于下级法院，则只需要获取该用户的所属法院即可，以法院身份登录
     * <p>
     * 5. 有该用户，则直接跳转，否则跳转到登录解码
     * <p>
     * 从其他系统的信任登录
     */
    @RequestMapping(value = "dygztgateway.do", method = RequestMethod.GET)
    public String gateway(HttpServletRequest request,
                          HttpServletResponse response, ModelMap model) {
        /*获取从其他系统传递来的验证信息*/
        String base64Param = request.getParameter("param");
        String signature = request.getParameter("sign");

		/*如果验证信息为空的话，跳转到登录界面，并退出*/
        if (StringUtil.isBlank(base64Param) || StringUtil.isBlank(signature)) {
            return "login";
        }

        String key = "211bfa7efbcbe28431ceb328969cb15e";
        MD5Signature md5Signature = new MD5Signature();
        boolean isChecked = false;
        try {
            isChecked = md5Signature.check(base64Param, signature, key, "utf-8"); //检查是否合法
        } catch (SignatureException e) {
            logger.error(e);
        }

		/*如果解密字串失败，则跳转到登录界面*/
        if (!isChecked) {
            boolean hasUserName = false;
            model.addAttribute("hasUserName", hasUserName);
            model.addAttribute("message", "请求出错");
            return "login";
        }

		/*字串解密成功，则进一步确认用户身份*/
        String paramStr = "";
        try {
            /*把传递过来的 para 数据进行base64 解密，因为对方系统对传递的数据进行了 base64 加密*/
            paramStr = new String(Base64Util.getFromBASE64(base64Param), "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
        }
        //为加密前要传递的数据格式为：fydm=120000 200&yhdm=gaojh&service=yzjc，现在需要进行拆分
        String[] params = paramStr.split("&");

        /*把需要的数据取出来*/
        //fydm
        int start = StringUtil.indexOf(params[0], "=");
        String fydm = params[0].substring(start + 1);
        if (!(fydm.indexOf(" ") >= 0))
            fydm = EscapeUnescape.unescape(fydm);

        //yhm 用户名
        start = StringUtil.indexOf(params[1], "=");
        String yhm = params[1].substring(start + 1);

        //doName 请求的服务
        start = StringUtil.indexOf(params[2], "=");
        String doName = params[2].substring(start + 1);

        DyXtyhVO dyXtyhVO = null;

        /*如果传递过来是天津高院，则查询该用户所属部门*/
        if (fydm.equals(DataSourceEnum.TJGY.getFydm())) {
            /*转到用户所属法院数据库去查用户表*/
            String curDb = CustomerContextHolder.getCustomerType();
            DataSourceRouter.routerTo(fydm);//切换相应用户所属的数据库
            XtglyhModel xtglyhModel = xtyhService.getYhByYhm(yhm); //去所属法院取用户信息，

            DataSourceRouter.routerTo(curDb);//切换原数据库

            if (xtglyhModel != null) {
                UserContextModel userContext = new UserContextModel();
                userContext.setYhbm(xtglyhModel.getYhbm());
            /*把 userContext 的用户名设置为所属的部门*/
                userContext.setYhmc(xtglyhModel.getYhbm());
                userContext.setYhqx("调研人;审批人"); //别的系统来的用户，预设权限为调研人和生僻人
                userContext.setYhsf(xtglyhModel.getYhsf());
            /*设置 ContextHolder*/
                request.getSession().setAttribute("userContext", userContext);
                ContextHolder.setUserContext(userContext);

                //跳转到各角色主界面
                String yhqx = userContext.getYhqx();
                if (yhqx.contains("调研人")) {
                    doName = "autoresearch.do";
                } else if (yhqx.contains("审批人")) {
                    doName = "approve.do";
                } else if (yhqx.contains("计算人")) {
                    doName = "compute.do";
                } else if (yhqx.contains("管理员")) {
                    doName = "distribute.do";
                }
                return "redirect:" + doName;
            } else {
                logger.warn(yhm + "：系统无该用户");
                boolean hasUserName = false;
                model.addAttribute("hasUserName", hasUserName);
                model.addAttribute("message", "系统无该用户");
                return "login"; //如果用户身份不合法，则跳转到登录界面
            }

        } else {
            dyXtyhVO = xtyhService.getXtyh(fydm, yhm);//不是高院的话，直接使用法院代码即可

            if (dyXtyhVO != null) {
                UserContextModel userContext = new UserContextModel();
                userContext.setYhbm(dyXtyhVO.getBm());
            /*把 userContext 的用户名设置为所属的法院名称*/
                userContext.setYhmc(dyXtyhVO.getFymc());
                userContext.setYhqx("调研人;审批人"); //别的系统来的用户，预设权限为调研人和生僻人

            /*设置 ContextHolder*/
                request.getSession().setAttribute("userContext", userContext);
                ContextHolder.setUserContext(userContext);

                //跳转到各角色主界面
                String yhqx = userContext.getYhqx();
                if (yhqx.contains("调研人")) {
                    doName = "autoresearch.do";
                } else if (yhqx.contains("审批人")) {
                    doName = "approve.do";
                } else if (yhqx.contains("计算人")) {
                    doName = "compute.do";
                } else if (yhqx.contains("管理员")) {
                    doName = "distribute.do";
                }
                return "redirect:" + doName;

            } else {
                logger.warn(yhm + "：系统无该用户");
                boolean hasUserName = false;
                model.addAttribute("hasUserName", hasUserName);
                model.addAttribute("message", "系统无该用户");
                return "login"; //如果用户身份不合法，则跳转到登录界面
            }

        }
    }

    /**
     * 显示修改联系方式form
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/changeLxfsForm.aj", method = RequestMethod.POST)
    public String lxfsForm(HttpServletRequest request,
                           HttpServletResponse response, ModelMap model) {
        //提前填好信息
        UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
        model.addAttribute("yhxx", user);
        return "pop/changeLxfs";
    }

    /**
     * 修改联系方式
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/changeLxfs.aj", method = RequestMethod.POST)
    public void changeLxfs(HttpServletRequest request,
                           HttpServletResponse response, ModelMap model) throws IOException {
        String phone = request.getParameter("phone").trim();
        String lxfs = request.getParameter("lxfs").trim();

        String curDb = CustomerContextHolder.getCustomerType();
        DataSourceRouter.routerToYzjcJzk();

        boolean success = false;
        String result = xtyhService.changeLxfs(phone, lxfs);
        if (result == null) {
            success = true;
            result = "修改成功!";
            //更新session里的user信息
            UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
            user.setPhone(phone);
            user.setLxfs(lxfs);
            request.getSession().setAttribute("userContext", user);
            ContextHolder.setUserContext(user);
        }

        DataSourceRouter.routerTo(curDb);

        String text = "{\"success\":" + success + ",\"text\":\"" + result + "\"}";
        ResponseBuilder builder = new ResponseBuilder();
        builder.writeJsonResponse(response, text);
    }

    /**
     * 显示修改密码form
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/changePasswordForm.aj", method = RequestMethod.POST)
    public String passwordForm(HttpServletRequest request,
                               HttpServletResponse response, ModelMap model) {
        return "pop/changePassword";
    }

    /**
     * 修改密码
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/changePassword.aj", method = RequestMethod.POST)
    public void changePassword(HttpServletRequest request,
                               HttpServletResponse response, ModelMap model) throws IOException {
        String oldPass = request.getParameter("oldpass");
        String newPass = request.getParameter("newpass");

        String curDb = CustomerContextHolder.getCustomerType();
        DataSourceRouter.routerToYzjcJzk();

        boolean success = false;
        String result = xtyhService.changePassword(oldPass, newPass);
        if (result == null) {
            success = true;
            result = "修改成功";
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
    @RequestMapping(value = "/addPerson.do", method = RequestMethod.GET)
    public String addPersonForm(HttpServletRequest request,
                                HttpServletResponse response, ModelMap model) {
        String tab = "changePerson";
        ContextHolder.setTabContext(tab);
        return "addPerson";
    }

    /**
     * 增加人、程序员
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/addPerson.aj", method = RequestMethod.POST)
    public void addPerson(HttpServletRequest request,
                          HttpServletResponse response, ModelMap model, DyXtyhVO vo, BindingResult result) throws IOException {
        String curDb = CustomerContextHolder.getCustomerType();
        DataSourceRouter.routerToYzjcJzk();

        boolean success = false;
        String data = xtyhService.addDyXtyh(vo);
        if (data == null) {
            success = true;
            data = "提交成功！";
        }
        DataSourceRouter.routerTo(curDb);
        String text = "{\"success\":" + success + ",\"text\":\"" + data + "\"}";
        ResponseBuilder builder = new ResponseBuilder();
        builder.writeJsonResponse(response, text);
    }

    /**
     * 增加程序员界面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/addSE.do", method = RequestMethod.GET)
    public String addSEForm(HttpServletRequest request,
                            HttpServletResponse response, ModelMap model) {
        String tab = "changeSE";
        ContextHolder.setTabContext(tab);
        return "addSE";
    }

    /**
     * 法院人员列表界面(ok)
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/changePerson.do", method = RequestMethod.GET)
    public String changePersonForm(HttpServletRequest request,
                                   HttpServletResponse response, ModelMap model) {
        String tab = "changePerson";
        ContextHolder.setTabContext(tab);
        return "changePerson";
    }

    /**
     * 法院人员列表界面(分页)
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/changePersonList.aj", method = RequestMethod.GET)
    public void changePersonList(HttpServletRequest request,
                                 HttpServletResponse response, ModelMap model, DatatablesPagedVO pagedVO) throws IOException {
        //得到人员列表
        String curDb = CustomerContextHolder.getCustomerType();
        DataSourceRouter.routerToYzjcJzk();
        List<String> qxList = new ArrayList<String>();
        qxList.add("调研人");
        qxList.add("审批人");
        List<DyXtyhVO> list = xtyhService.getListByQx(qxList);
        //搜索
        if (StringUtil.isNotBlank(pagedVO.getsSearch())) {
            List<String> headers = new ArrayList<String>();
            headers.add("fymc");
            headers.add("yhm");
            headers.add("name");
            headers.add("qx");
            headers.add("phone");
            headers.add("lxfs");
            list = SearchUtil.listSearching(list, pagedVO.getsSearch(), headers);
        }
        //排序
        String[] sortHeader = new String[]{"xh", "fymc", "yhm", "name", "qx", "phone", "lxfs"};
        String sortField = CompareUtil.getSortColumn(sortHeader, pagedVO.getiSortCol_0(), sortHeader[0]);
        List<CompareModel> models = new ArrayList<CompareModel>();
        models.add(new CompareModel(pagedVO.getsSortDir_0(), sortField));
        Collections.sort(list, CompareUtil.createComparator(models));
        //分页
        List<DyXtyhVO> pagedAjs = PageUtil.listPaging(list, pagedVO.getiDisplayStart(),
                pagedVO.getiDisplayLength());
        //转换
        List<DyXtyhVO> result = new ArrayList<DyXtyhVO>();
        for (DyXtyhVO src : pagedAjs) {
            DyXtyhVO des = new DyXtyhVO();
            des.setXh(src.getXh());
            des.setFymc(src.getFymc() + "<input type='hidden' name='fydm' value='" + src.getFydm() + "'/>");
            des.setYhm(src.getYhm() + "<input type='hidden' name='yhm' value='" + src.getYhm() + "'/>");
            des.setName(src.getName());
            String qx = "<input type='checkbox' name='qx' value='调研人' data-edit='true' disabled";
            if (src.getQx().contains("调研人")) {
                qx += " checked";
            }
            qx += ">调研人<input type='checkbox' name='qx' value='审批人' data-edit='true' disabled";
            if (src.getQx().contains("审批人")) {
                qx += " checked";
            }
            qx += ">审批人";
            des.setQx(qx);
            des.setPhone("<input type='text' name='phone' value='" + src.getPhone() + "' data-edit='true' disabled>");
            des.setLxfs("<input type='text' name='lxfs' value='" + src.getLxfs() + "' data-edit='true' disabled>");
            des.setBtn("<div class='cpt_menu_update' title='修改'></div><div class='cpt_menu_del' title='删除'></div>");
            result.add(des);
        }
        DataSourceRouter.routerTo(curDb);

        //标记值加一
        pagedVO.setsEcho(pagedVO.getsEcho() + 1);
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
    @RequestMapping(value = "/deletePerson.aj", method = RequestMethod.POST)
    public void deletePerson(HttpServletRequest request,
                             HttpServletResponse response, ModelMap model) throws IOException {
        String fydm = request.getParameter("fydm");
        String yhm = request.getParameter("yhm");

        String curDb = CustomerContextHolder.getCustomerType();
        DataSourceRouter.routerToYzjcJzk();

        boolean success = false;
        String result = xtyhService.deleteDyXtyh(fydm, yhm);
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
     * 修改法院人员
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/changePerson.aj", method = RequestMethod.POST)
    public void changePerson(HttpServletRequest request,
                             HttpServletResponse response, ModelMap model, DyXtyhVO vo, BindingResult result) throws IOException {

        String curDb = CustomerContextHolder.getCustomerType();
        DataSourceRouter.routerToYzjcJzk();

        boolean success = false;
        String data = xtyhService.changeDyXtyh(vo);
        if (data == null) {
            success = true;
            data = "已修改";
        }

        DataSourceRouter.routerTo(curDb);

        String text = "{\"success\":" + success + ",\"text\":\"" + data + "\"}";
        ResponseBuilder builder = new ResponseBuilder();
        builder.writeJsonResponse(response, text);
    }

    /**
     * 系统人员列表界面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/changeSE.do", method = RequestMethod.GET)
    public String changeSEForm(HttpServletRequest request,
                               HttpServletResponse response, ModelMap model) {
        String tab = "changeSE";
        ContextHolder.setTabContext(tab);
        return "changeSE";
    }

    /**
     * 系统人员列表界面(分页)
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/changeSEList.aj", method = RequestMethod.GET)
    public void changeSEList(HttpServletRequest request,
                             HttpServletResponse response, ModelMap model, DatatablesPagedVO pagedVO) throws IOException {
        String curDb = CustomerContextHolder.getCustomerType();
        DataSourceRouter.routerToYzjcJzk();

        List<String> qxList = new ArrayList<String>();
        qxList.add("计算人");
        qxList.add("管理员");
        List<DyXtyhVO> list = xtyhService.getListByQx(qxList);
        //搜索
        if (StringUtil.isNotBlank(pagedVO.getsSearch())) {
            List<String> headers = new ArrayList<String>();
            headers.add("yhm");
            headers.add("name");
            headers.add("qx");
            headers.add("phone");
            headers.add("lxfs");
            list = SearchUtil.listSearching(list, pagedVO.getsSearch(), headers);
        }
        //排序
        String[] sortHeader = new String[]{"xh", "yhm", "name", "qx", "phone", "lxfs"};
        String sortField = CompareUtil.getSortColumn(sortHeader, pagedVO.getiSortCol_0(), sortHeader[0]);
        List<CompareModel> models = new ArrayList<CompareModel>();
        models.add(new CompareModel(pagedVO.getsSortDir_0(), sortField));
        Collections.sort(list, CompareUtil.createComparator(models));
        //分页
        List<DyXtyhVO> pagedAjs = PageUtil.listPaging(list, pagedVO.getiDisplayStart(),
                pagedVO.getiDisplayLength());
        //转换
        List<DyXtyhVO> result = new ArrayList<DyXtyhVO>();
        for (DyXtyhVO src : pagedAjs) {
            DyXtyhVO des = new DyXtyhVO();
            des.setXh(src.getXh());
            des.setYhm(src.getYhm() + "<input type='hidden' name='fydm' value='" + src.getFydm() + "'/><input type='hidden' name='yhm' value='" + src.getYhm() + "'/>");
            des.setName(src.getName());
            String qx = "<input type='checkbox' name='qx' value='计算人' data-edit='true' disabled";
            if (src.getQx().contains("计算人")) {
                qx += " checked";
            }
            qx += ">计算人<input type='checkbox' name='qx' value='管理员' data-edit='true' disabled";
            if (src.getQx().contains("管理员")) {
                qx += " checked";
            }
            qx += ">管理员";
            des.setQx(qx);
            des.setPhone("<input type='text' name='phone' value='" + src.getPhone() + "' data-edit='true' disabled>");
            des.setLxfs("<input type='text' name='lxfs' value='" + src.getLxfs() + "' data-edit='true' disabled>");
            des.setBtn("<div class='cpt_menu_update' title='修改'></div><div class='cpt_menu_del' title='删除'></div>");
            result.add(des);
        }

        DataSourceRouter.routerTo(curDb);
        //标记值加一
        pagedVO.setsEcho(pagedVO.getsEcho() + 1);
        //返回值
        Map map = new HashMap();
        map.put("data", result);
        map.put("recordsTotal", list.size());
        map.put("recordsFiltered", list.size());
        map.put("sEcho", pagedVO.getsEcho());
        ResponseBuilder builder = new ResponseBuilder();
        builder.writeJsonResponse(response, map);
    }
}
