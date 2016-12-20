package software.dygzt.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import software.dygzt.dynamicds.CustomerContextHolder;
import software.dygzt.dynamicds.DataSourceEnum;
import software.dygzt.dynamicds.DataSourceRouter;
import software.dygzt.service.bm.BmbService;
import software.dygzt.service.share.MD5Signature;
import software.dygzt.service.share.model.ContextHolder;
import software.dygzt.service.user.XtyhService;
import software.dygzt.service.user.model.DyXtyhVO;
import software.dygzt.service.user.model.UserContextModel;
import software.dygzt.service.user.model.XtglyhModel;
import software.dygzt.util.Base64Util;
import software.dygzt.util.EscapeUnescape;
import software.dygzt.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

/**
 * Created by Pzy on 12/19/16.
 */
@Controller
public class DygztTrustController {
    private static Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private XtyhService xtyhService;
    @Autowired
    BmbService bmbService;

    /**
     * 接收方信任登录过程(gateway.do 会被 AccessInterceptor 访问拦截器拦截，使之特殊处理)
     * 1. 获取数据
     * 2. 使用 base64 解码数据，获得原字符串
     * 3. 拆解原字串，获取需要的信息
     * 4. 根据传递的用户信息，去数据库中查询是否有该用户
     * 调研工作台中的用户是独立的，所以需要做判断:
     * 1. 如果该用户属于高院，则查询该用户所属部门，以部门的身份登录
     * 2. 如果该用户属于下级法院，则只需要获取该用户的所属法院即可，以法院身份登录
     * 5. 有该用户，则直接跳转，否则跳转到登录解码
     * <p>
     * 从其他系统的信任登录传递的参数为
     * fydm=120000 200&yhdm=ydb（用户代码为用户的拼音检查，不是用户名）
     */
    @RequestMapping(value = "gateway.do", method = RequestMethod.GET)
    public String gateway(HttpServletRequest request,
                          HttpServletResponse response, ModelMap model) {
        /*获取从其他系统传递来的验证信息*/
        String base64Param = request.getParameter("param");
        String signature = request.getParameter("sign");
        String serverPath = request.getParameter("serverPath");

        String requestURL = request.getRequestURL().toString();//请求的 URL

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
        //取fydm
        int start = StringUtil.indexOf(params[0], "=");
        String fydm = params[0].substring(start + 1);
        if (!(fydm.indexOf(" ") >= 0))
            fydm = EscapeUnescape.unescape(fydm);

        //取yhm 用户名
        start = StringUtil.indexOf(params[1], "=");
        String yhm = params[1].substring(start + 1);

        //取要登录的系统(type = 1 为调研工作台，type  = 2 为报表定制系统)
        int type = 1; //默认为调研工作台
        if (params.length >= 3) {
            start = StringUtil.indexOf(params[2], "=");
            type = Integer.valueOf(params[2].substring(start + 1));
        }

        /*doName 请求的服务为可选*/
        String doName = null;
        if (params.length >= 4) {
            start = StringUtil.indexOf(params[2], "=");
            doName = params[2].substring(start + 1);
        }


        DyXtyhVO dyXtyhVO = null;

        /*如果传递过来是天津高院，则查询该用户所属部门*/
        if (fydm.equals(DataSourceEnum.TJGY.getFydm())) {
            dyXtyhVO = xtyhService.getXtyh(fydm, yhm);
            if (dyXtyhVO != null) {
                UserContextModel userContext = new UserContextModel();
                userContext.setYhbm(dyXtyhVO.getBm());
                /*把 userContext 的用户名设置为所属的法院名称*/
                userContext.setYhmc(dyXtyhVO.getName()); //dyXtyhVO 中 Name 对应的中文名
                userContext.setYhdm(dyXtyhVO.getYhm()); //dyXtyhVO 中 yhm 保存的是拼音简称
                userContext.setYhqx("调研人"); //别的系统来的用户，预设权限为调研人
                userContext.setFydm(dyXtyhVO.getFydm());

                /*设置 ContextHolder*/
                request.getSession().setAttribute("userContext", userContext);
                ContextHolder.setUserContext(userContext);

                //信任登录的用户，都跳转到两个系统的默认主界面
                String yhqx = userContext.getYhqx();
                if (type == 1) {
                    doName = "autoresearch.do";
                    logger.info("TRUST LOGIN SUCCESS TO DYGZT: requestURL from " + requestURL);

                } else if (type == 2) {
                    doName = "bbsc.do";
                    logger.info("TRUST LOGIN SUCCESS TO BBDZ: requestURL from " + requestURL); //BBDZ 为报表定制系统
                } else {
                    return "login";
                }
                return "redirect:" + doName;
            } else {
                /*调研工作台无该用户所属法院，跳转到登录，给予相应提示*/
                logger.warn(yhm + "：调研工作台系统无该用户");
                boolean hasUserName = false;
                model.addAttribute("hasUserName", hasUserName);
                model.addAttribute("message", "调研工作台系统无该用户");
                return "login"; //如果用户身份不合法，则跳转到登录界面
            }

        } else {/*如果该用户不属于高院的话，直接使用法院代码和用户名从 dy_xtyh 取得该用户*/
            /*转集中库去查用户表*/
            String curDb = CustomerContextHolder.getCustomerType();
            DataSourceRouter.routerToJzk();
            XtglyhModel xtglyhModel = xtyhService.getYhByYhm(yhm);
            //要根据用户的 yhbm 取得对应的中文名称，xtglyhModel.getYhbm() 取得的是编码编号，根据bmbh 取部门名，然后设置
            String bmmc = bmbService.getBm(xtglyhModel.getYhbm()).getBmmc(); //bmmc 部门名称

            DataSourceRouter.routerTo(curDb);//切换原数据库

            if (xtglyhModel != null) {
                /*设置 userContext 的用户信息*/
                UserContextModel userContext = new UserContextModel();
                userContext.setYhbm(xtglyhModel.getYhbm());
                userContext.setYhmc(bmmc); /*把 userContext 的用户名设置为所属的部门名称*/
                userContext.setYhqx("调研人;审批人"); //别的系统来的用户，预设权限为调研人和审批人
                userContext.setYhsf(xtglyhModel.getYhsf());
                /*设置 ContextHolder*/
                request.getSession().setAttribute("userContext", userContext);
                ContextHolder.setUserContext(userContext);

                //信任登录的用户，都跳转到两个系统的默认主界面
                String yhqx = userContext.getYhqx();
                if (type == 1) {
                    doName = "autoresearch.do";
                    logger.info("TRUST LOGIN SUCCESS TO DYGZT: requestURL from " + requestURL);
                } else if (type == 2) {
                    doName = "bbsc.do";
                    logger.info("TRUST LOGIN SUCCESS TO BBDZ: requestURL from " + requestURL);//BBDZ 为报表定制系统

                } else {
                    return "login";
                }
                return "redirect:" + doName;
            } else {
                /*如果用户身份不合法，则跳转到登录界面，并给予相应显示*/
                logger.warn(yhm + "：系统无该用户");
                boolean hasUserName = false;
                model.addAttribute("hasUserName", hasUserName);
                model.addAttribute("message", "系统无该用户");
                return "login";
            }
        }
    }


    /**
     * 接收方信任登录测试
     */
    @RequestMapping(value = "/testgateway.do", method = RequestMethod.GET)
    public String gatewayTest(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws SignatureException {
        UserContextModel user = (UserContextModel) request.getSession().getAttribute("userContext");
        if (user != null) {
            String fydm = user.getFydm();
            String yhdm = user.getYhdm();
            String param = "fydm=" + fydm + "&yhdm=" + yhdm + "&type=" + 2;
            String sign = "";
            String key = "211bfa7efbcbe28431ceb328969cb15e";
            MD5Signature md5 = new MD5Signature();
            String parambase64 = Base64Util.getBASE64(param.getBytes());
            sign = md5.sign(parambase64, key, "UTF-8");
            String url = "http://localhost:8080/dygzt/gateway.do?param=" + parambase64 + "&sign=" + sign;
            return "redirect:" + url;
        } else {
            return "login";
        }
    }

}
