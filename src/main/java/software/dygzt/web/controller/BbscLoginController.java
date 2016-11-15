package software.dygzt.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import software.dygzt.dynamicds.CustomerContextHolder;
import software.dygzt.dynamicds.DataSourceRouter;
import software.dygzt.dynamicds.FYDataSourceEnum;
import software.dygzt.service.share.model.ContextHolder;
import software.dygzt.service.user.XtyhService;
import software.dygzt.service.user.model.UserCheckResult;
import software.dygzt.service.user.model.UserContextModel;
/**
 * 报表生成系统用户登陆验证的Controller
 */

@Controller
public class BbscLoginController {
	private static Logger logger = Logger.getLogger(BbscLoginController.class);
	@Autowired
	private XtyhService xtyhService;
	/**
	 * 登陆
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login2.do", method = RequestMethod.GET)
	public String showStartPage(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {

		// 判断当前是否存在userContext，存在则先进行退出操作，清空userContext
		if (request.getSession().getAttribute("userContext") != null) {
			request.getSession().removeAttribute("userContext");
		}
		
		//通过判断访问地址 来设置登录页默认法院
		String accessIp = request.getRemoteAddr();
		FYDataSourceEnum fy = FYDataSourceEnum.getEnumByAccessIp(accessIp);
		if(fy != null){
			model.addAttribute("fymc", fy.getFymc());
			model.addAttribute("fydm", fy.getFydm());
		}else{
			model.addAttribute("fymc", "天津市高级人民法院");
			model.addAttribute("fydm", "120000 200");
		}
		return "bbsc/login";
	}

	/**
	 * 
	 * 获得登陆界面传入的用户名、密码和法院编号，验证用户是否有效 返回验证结果，成功进入主界面，失败返回错误界面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws SignatureException
	 */
	@RequestMapping(value = "/login2.do", method = RequestMethod.POST)
	public String showMainPage(HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		String yhm = request.getParameter("yhm");
		String password = request.getParameter("mm");
		String fydm = request.getParameter("fydm");

		String curDb = CustomerContextHolder.getCustomerType();
		DataSourceRouter.routerToYzjcJzk();
		try {
			UserCheckResult result = xtyhService.getXtyh(fydm, yhm,password);
			// 登录成功
			if (result.isSuccess() == true) {
				UserContextModel userContext = result.getXtyh();
				request.getSession().setAttribute("userContext", userContext);
				ContextHolder.setUserContext(userContext);
				//跳转到各角色主界面
				String doName = "";
				String yhqx = userContext.getYhqx();
				if(yhqx.contains("调研人")){
					doName = "bbsc.do";
				}else if(yhqx.contains("管理员")){
					doName = "bbscSearch.do";
				}
				return "redirect:" + doName;
			} else {
				boolean hasUserName;
				if (result.getResultCode().getCode().equals("ILLEGAL_PASSWORD")) {
					logger.warn(yhm+"：该用户密码错误");	
					hasUserName = true;
					model.addAttribute("message", "用户密码错误");
				} else if(result.getResultCode().getCode().equals("EMPTY_USERSYSTEM")){
					logger.warn(yhm+"：无权限");	
					hasUserName = false;
					model.addAttribute("message", "该用户没有配置系统");
				}else{
					logger.warn(yhm+"：没有该用户");	
					hasUserName = false;
					model.addAttribute("message", "系统不存在该用户");
				}
				model.addAttribute("hasUserName", hasUserName);
				return "bbsc/login";
			}
		} catch (Exception e) {
			logger.error("验证用户登陆信息时出现异常", e);
			return "error";
		} finally {
			DataSourceRouter.routerTo(curDb);
		}
	}
}
