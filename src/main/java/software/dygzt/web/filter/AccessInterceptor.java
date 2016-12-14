package software.dygzt.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import software.dygzt.dynamicds.CustomerContextHolder;
import software.dygzt.service.share.model.ContextHolder;
import software.dygzt.service.user.model.UserContextModel;



/**
 * 访问拦截器
 */
public class AccessInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = Logger.getLogger(AccessInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String requestURI = request.getRequestURI();
		String toLogin = request.getRequestURL().toString();
		String doName = request.getServletPath();
		String cannotAccess = toLogin.substring(0,toLogin.length() - doName.length())+ "/login.do";		
		
		UserContextModel user = (UserContextModel) request.getSession().getAttribute(
				"userContext");
		ContextHolder.setUserContext(user);
		CustomerContextHolder.setCustomerType("Default");
		if (user != null) {
			return true;
		}else{
			if (requestURI.endsWith("/login2.do")||requestURI.endsWith("/login.do")||requestURI.endsWith("/browser.do")||requestURI.endsWith("xzfy.aj")) {
				return true;
			} else {
				log.warn("登陆失效。requestURI:" + requestURI);
				//跳转登录页面
				response.sendRedirect(cannotAccess);
				return false;
			}
		}
	}
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		String requestURI = request.getRequestURI();
		if (requestURI.endsWith(".do")){	//不是.aj,.aj没有modelAndView
			UserContextModel user = (UserContextModel)request.getSession().getAttribute("userContext");
			if (user != null) {
				modelAndView.addObject("yhxx" , user);
			}
			String tab = (String)ContextHolder.getTabContext();
			if (tab != null) {
				modelAndView.addObject("tab" , tab);
			}
			String tab2 = (String)ContextHolder.getTabContext2();
			if (tab2 != null) {
				modelAndView.addObject("tab2" , tab2);
			}
		}
	}

}
