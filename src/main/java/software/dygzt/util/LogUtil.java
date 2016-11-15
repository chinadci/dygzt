package software.dygzt.util;

import org.apache.log4j.Logger;

import software.dygzt.service.user.model.UserContextModel;


public class LogUtil {
	public static final int type_error = 1;
	public static final int type_info = 2;
	public static final int type_warn = 3;

	public static void logging(String logStr, Logger log, int logType) {
		logging(logStr, log, logType, null);
	}

	public static void logging(String logStr, Logger log, int logType,
			Exception e) {
		switch (logType) {
		case 1:
			if (e != null)
				log.error(logStr, e);
			else
				log.error(logStr);
			break;
		case 2:
			if (e != null)
				log.info(logStr, e);
			else
				log.info(logStr);
			break;
		case 3:
			if (e != null)
				log.warn(logStr, e);
			else
				log.warn(logStr);
			break;
		default:
			log.error("logging 不支持的logType：" + logType);
		}
	}
	
	private static Logger logger_s = Logger.getLogger("statistics");
	public static void logStatistics(long ajxh, String ah, String caseState){
		String logString = "("+String.valueOf(ajxh)+","+ah+","+caseState+")";
		logger_s.info(logString);
	}
	
	private static Logger logger_l = Logger.getLogger("loginstatistics");
	public static void loginStatistics(UserContextModel user){
		String logString = "用户("+user.getYhmc()+":"+user.getYhbh()+":"+user.getYhdm()+")已登录";
		logger_l.info(logString);
	}
}
