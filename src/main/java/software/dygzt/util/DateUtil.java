/**
 * created by 2010-7-2
 */
package software.dygzt.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.google.gson.Gson;

/**
 * 日期工具类
 * 
 */
public class DateUtil {
	public final static String shortFormat = "yyyyMMdd";
	public final static String longFormat = "yyyyMMddHHmmss";
	public final static String webFormat = "yyyy-MM-dd";
	public final static String timeFormat = "HHmmss";
	public final static String monthFormat = "yyyyMM";
	public final static String chineseDtFormat = "yyyy年MM月dd日";
	/**
	 * 最高院使用日期格式YYYY-MM-DD
	 */
	public final static String newFormat = "yyyy-MM-dd";
	public final static String zbFormat = "yyyy/MM/dd";
	public final static String tsfxFormat = "yyyy.MM.dd";
	public final static String noSecondFormat = "yyyy-MM-dd HH:mm";
	public final static String timestampFormat = "yyyy-MM-dd HH:mm:ss";
	public final static long ONE_DAY_MILL_SECONDS = 86400000;

	// -----------------------------------------------------------------------
	/**
	 * Adds to a date returning a new object. The original date object is
	 * unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param calendarField
	 *            the calendar field to add to
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date add(Date date, int calendarField, int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	public static Date addYears(Date date, int amount) {
		return add(date, Calendar.YEAR, amount);
	}

	public static Date addMonths(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
	}

	public static Date addWeeks(Date date, int amount) {
		return add(date, Calendar.WEEK_OF_YEAR, amount);
	}

	public static Date addDays(Date date, int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}

	public static Date addHours(Date date, int amount) {
		return add(date, Calendar.HOUR_OF_DAY, amount);
	}

	/**
	 * 标准化输出
	 * 
	 * @param date
	 *            日期对象
	 * @param format
	 *            日期输出格式
	 * @return 按照指定格式输出的字符串
	 */
	public static String format(Date date, String format) {
		if (date == null) {
			return null;
		}

		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 获得一个时间的年份数
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		if (date == null) {
			return -1;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获得一个时间的月份数
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		if (date == null) {
			return -1;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获得一个时间在一个月中的天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		if (date == null) {
			return -1;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 标准化输出
	 * 
	 * @param s_date
	 * @param format
	 * @return
	 */
	public static Date parse(String s_date, String format) {
		if (s_date == null)
			return null;
		try {
			return new SimpleDateFormat(format).parse(s_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取得两个日期间隔秒数（日期1-日期2）
	 * 
	 * @param one
	 *            日期1
	 * @param two
	 *            日期2
	 * 
	 * @return 间隔秒数
	 */
	public static long getDiffSeconds(Date one, Date two) {
		Calendar sysDate = new GregorianCalendar();

		sysDate.setTime(one);

		Calendar failDate = new GregorianCalendar();

		failDate.setTime(two);
		return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 1000;
	}

	/**
	 * 取得两个日期间隔分钟数（日期1-日期2）
	 * 
	 * @param one
	 * @param two
	 * @return
	 */
	public static long getDiffMinutes(Date one, Date two) {
		Calendar sysDate = new GregorianCalendar();

		sysDate.setTime(one);

		Calendar failDate = new GregorianCalendar();

		failDate.setTime(two);
		return (sysDate.getTimeInMillis() - failDate.getTimeInMillis())
				/ (60 * 1000);
	}

	/**
	 * 取得两个日期的间隔天数
	 * 
	 * @param one
	 * @param two
	 * 
	 * @return 间隔天数
	 */
	public static long getDiffDays(Date one, Date two) {
		Calendar sysDate = new GregorianCalendar();

		sysDate.setTime(one);

		Calendar failDate = new GregorianCalendar();

		failDate.setTime(two);
		return (sysDate.getTimeInMillis() - failDate.getTimeInMillis())
				/ (24 * 60 * 60 * 1000);
	}

	/**
	 * 比较两个日期的先后关系
	 * 
	 * @param one
	 *            日期1
	 * @param two
	 *            日期2
	 * @return 0 ： 表示相等 1 ： one大于two -1 ： two大于one null大于其他
	 */
	public static int compareDate(Date one, Date two) {
		// 合法性判断
		if (one == null && two != null)
			return -1;
		else if (one != null && two == null)
			return 1;
		else if (one == null && two == null)
			return 0;
		else {
			if (one.getTime() > two.getTime())
				return 1;
			if (one.getTime() == two.getTime())
				return 0;
			return -1;
		}
	}
	
	/**
	 * 判断是否是正确的日期格式
	 * 
	 * @param str_date
	 *            日期字符串
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static boolean isValidDateFormat(String strDate, String format) {
		// 检查长度
		if (strDate.length() != longFormat.length()) {
			return false;
		}

		//检查格式是否正确
		DateFormat df = new SimpleDateFormat(format);
		df.setLenient(false);
		try {
			df.parse(strDate);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * 获得当天的开始时间
	 * 
	 * @return
	 */
	public static Date today() {
		return parse(format(new Date(), webFormat), webFormat);
	}
	
	public static Date getDefaultBeginTime() {
		return addDays(new Date(), -7);
	}
	
	public static List<String> dateInterval(Date ksrq,Date jsrq,String sjhf){
		List<String> xAxis = new ArrayList<String>();
		if(sjhf.equals("year")){
			//归成1月1号
			ksrq = new Date(ksrq.getYear(),0,1);
			jsrq = new Date(jsrq.getYear()+1,0,1);
			for(Date i=ksrq;DateUtil.compareDate(i, jsrq)<0;i=DateUtil.addYears(i, 1)){
				xAxis.add((i.getYear()+1900)+"年");
			}
		}else if(sjhf.equals("month")){
			//归成1月1号
			ksrq = new Date(ksrq.getYear(),ksrq.getMonth(),1);
			jsrq = new Date(jsrq.getYear(),jsrq.getMonth()+1,1);
			for(Date i=ksrq;DateUtil.compareDate(i, jsrq)<0;i=DateUtil.addMonths(i, 1)){
				xAxis.add((i.getYear()+1900)+"年"+(i.getMonth()+1)+"月");
			}
		}else if(sjhf.equals("date")){
			jsrq = DateUtil.addDays(jsrq, 1);
			for(Date i=ksrq;DateUtil.compareDate(i, jsrq)<0;i=DateUtil.addDays(i, 1)){
				xAxis.add(DateUtil.format(i, DateUtil.webFormat));
			}
		}
		return xAxis;
	}
	
	public static List<List<String>> dateIntervals(Date ksrq,Date jsrq,String sjhf){
		List<List<String>> result = new ArrayList<List<String>>();
		List<String> ksrqList = new ArrayList<String>();
		List<String> jsrqList = new ArrayList<String>();
		if(sjhf.equals("year")){
			//归成1月1号
			ksrq = new Date(ksrq.getYear(),0,1);
			jsrq = DateUtil.addDays(new Date(jsrq.getYear()+1,0,1),-1);
			ksrqList.add(DateUtil.format(ksrq, DateUtil.webFormat));
			for(Date i=DateUtil.addYears(ksrq,1);DateUtil.compareDate(i, jsrq)<0;i=DateUtil.addYears(i, 1)){
				jsrqList.add(DateUtil.format(DateUtil.addDays(i,-1), DateUtil.webFormat));
				ksrqList.add(DateUtil.format(i, DateUtil.webFormat));
			}
			jsrqList.add(DateUtil.format(jsrq, DateUtil.webFormat));
		}else if(sjhf.equals("month")){
			//归成1月1号
			ksrq = new Date(ksrq.getYear(),ksrq.getMonth(),1);
			jsrq = DateUtil.addDays(new Date(jsrq.getYear(),jsrq.getMonth()+1,1),-1);
			ksrqList.add(DateUtil.format(ksrq, DateUtil.webFormat));
			for(Date i=DateUtil.addMonths(ksrq,1);DateUtil.compareDate(i,jsrq)<0;i=DateUtil.addMonths(i,1)){
				jsrqList.add(DateUtil.format(DateUtil.addDays(i,-1), DateUtil.webFormat));
				ksrqList.add(DateUtil.format(i, DateUtil.webFormat));
			}
			jsrqList.add(DateUtil.format(jsrq, DateUtil.webFormat));
		}else if(sjhf.equals("date")){
			for(Date i=ksrq;DateUtil.compareDate(i, jsrq)<=0;i=DateUtil.addDays(i, 1)){
				ksrqList.add(DateUtil.format(i, DateUtil.webFormat));
				jsrqList.add(DateUtil.format(i, DateUtil.webFormat));
			}
		}
		result.add(ksrqList);
		result.add(jsrqList);
		return result;
	}

}