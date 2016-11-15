package software.dygzt.exceptions;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import software.dygzt.util.StringUtil;


/**
 * 系统异常处理
 * @author ruanhao
 *
 */
public class SystemExceptionResolver extends SimpleMappingExceptionResolver {

	/**
	 * modified modified at 2012-7-6 by YU 更正了日志记录方式logException
	 * 增加了异常记录的Logger
	 */
	private static Logger logger = Logger.getLogger(SystemExceptionResolver.class);
		
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		String  url = request.getRequestURI();
		//Ajax请求
		if(url.endsWith(".aj")){
			//上传大小超过配置
			/*String error = null;
			if(ex instanceof MaxUploadSizeExceededException){
				long fileSize=((MaxUploadSizeExceededException)ex).getMaxUploadSize();
				error = "{\"exception\":\"文件超过限制大小，最大限制为"+(fileSize/1024)+"K\"}";
				
			}else{
				error ="{\"exception\":\"发生系统异常，请与系统管理员联系。\"}";
			}*/
			String msg = null;
			msg = this.buildExceptionMessage(request);
			if(msg == null){
				msg="Unknown Request.";
			}
			if(ex == null){
				ex = new Exception(msg);
			}
			logger.error(msg,ex);
//			String error = "{\"exception\":\"发生系统异常，请与系统管理员联系。\"}";
//			try {
//				if(null!=error){
//					writeJsonResponse(response, error);
//				}
//			} catch (Exception e) {
//				logger.error("返回信息失败！", e);
//			}
			return null;
		}else{
			String[] urlArray = StringUtil.split(url, '/');
			String curUrl = urlArray[urlArray.length - 1];
			String ytxh = request.getParameter("ytxh");
			if (StringUtil.isEmpty(ytxh)) {
				ytxh = "";
			}else {
				curUrl += "?ytxh=" + ytxh;
			}
			request.setAttribute("ytxh", ytxh);
			request.setAttribute("curUrl", curUrl);
			return super.doResolveException(request, response, handler, ex);
		}
	}
	/**
	 * 重载了SimpleMappingExceptionResolver的logException方法
	 * modified modified at 2012-7-6 by YU 更正了日志记录方式logException
	 * 定制异常日志记录方式
	 */
	protected void logException(Exception ex, HttpServletRequest request) {
//		ExceptionLoggerInterface logger = null;
//		ExceptionLoggerFactory factory = ExceptionLoggerFactory.getInstance();
//		logger = factory.create(ex);		//依据ex的运行时实例创建对应的异常记录器
//		logger.logException(ex, request);	//记录日志
		String msg = null;
		msg = this.buildExceptionMessage(request);
		if(msg == null){
			msg="Unknown Request.";
		}
		if(ex == null){
			ex = new Exception(msg);
		}
		logger.error(msg,ex);
	}
	
	/**
	 * 根据异常构造日志内容
	 * 
	 * @param request
	 * @return
	 */
	private String buildExceptionMessage(HttpServletRequest request){
		String msg = "parameters:";
		try {
			for (Enumeration e = request.getParameterNames() ; e.hasMoreElements() ;) {
	            String parameterName = (String) e.nextElement();
	            if(parameterName == null){
	            	continue;
	            }
	            String parameterValue = request.getParameter(parameterName);
	            if(parameterValue == null){
	            	parameterValue="null";
	            }
	            msg += ("["+parameterName+"="+parameterValue+"]");
	        } 
		} catch (Exception e) {
			logger.error("Error in buildExceptionMessage", e);
		}
		
		if(msg.equals("parameters:")){
			msg += "No HttpServletRequest parameter.";
		}
		return msg;
		
	}
}
