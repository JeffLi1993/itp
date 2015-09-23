package sedion.jeffli.wmuitp.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 日期格式化工具类
 * 
 */
public class DateUtil {

	/**
	 * 得到当前时间戳,格式:yyyy-mm-dd
	 * 
	 * @return
	 */
	public static Date getDate() {
		return java.sql.Date.valueOf(getFormatDate("yyyy-MM-dd"));
	}

	public static Date getDate(String date) {
		return java.sql.Date.valueOf(date);
	}
	public static Date getTomorrowDate(Date date) throws ParseException
	{
		date=new Date(1000L*60*60*24+date.getTime());
		return date;
	}
	public static Date getYesterdayDate(Date date) throws ParseException
	{
		//SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		date=new Date(-1000L*60*60*24+date.getTime());
		return date;
	}

	/**
	 * 返回年份
	 * 
	 * @return
	 */
	public static String getCurrentYear() {

		return getFormatDate("yyyy");
	}

	/**
	 * 返回月份
	 */
	public static String getCurrentMonth() {
		return getFormatDate("MM");
	}

	/**
	 * 返回特定格式的日期 格式如下: yyyy-mm-dd
	 * 
	 * @param formatString
	 * @return
	 */
	protected static String getFormatDate(String formatString) {
		String currentDate = "";
		SimpleDateFormat format1 = new SimpleDateFormat(formatString);
		currentDate = format1.format(new Date());
		return currentDate;
	}

	/**
	 * 返回时间格式样式
	 * 
	 * @return
	 */
	public static Timestamp getDateTime() {
		return Timestamp.valueOf(getFormatDate("yyyy-MM-dd HH:mm:ss"));
	}

	public static Timestamp getDateTime(String datetime) {
		return Timestamp.valueOf(datetime);
	}

	/**
	 * 获取简化时间格式
	 * 
	 * @return
	 */
	public static String getFormateDateSimple() {
		return getFormatDate("yyyyMMddHHmmss");
	}

	/**
	 * 
	 * @return
	 */
	public static List<String> getWantTimeList() {
		List<String> wantTimes = new ArrayList<String>();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		String nowTime = format.format(new Date());
		String[] dates = nowTime.split(":");
		int hour = Integer.valueOf(dates[0]);
		int minute = Integer.valueOf(dates[1]);
		wantTimes.add("立即送出");
		if (hour >= 7 && hour <= 18) {
			String wantTime = "";
			if (minute < 15) {
				wantTime = String.valueOf(hour) + ":" + 45 + ":" + "00";
				wantTimes.add(wantTime);
				hour = hour + 1;
			} else {
				for (int i = Integer.valueOf(minute / 15); i < 4; i++) {
					wantTime = String.valueOf(hour + 1) + ":" + 15 * i + ":" + "00";
					wantTimes.add(wantTime);
				}
				hour = hour + 2;
			}
			for (int i = hour; i < 20; i++) {
				wantTime = String.valueOf(i) + ":00:" + "00";
				wantTimes.add(wantTime);
				for (int j = 1; j < 4; j++) {
					wantTime = String.valueOf(i) + ":" + 15 * j + ":" + "00";
					wantTimes.add(wantTime);
				}
			}
		}
		return wantTimes;
	}



	/**
	 * 获取几天后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfterDay(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	public static String getDateAfterDay(String d, int day) throws ParseException {
		Date date = stringToDate(d);
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return dateToString(now.getTime());
	}

	/**
	 * 得到几天前的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static String getDateBefore(String d, int day) throws ParseException {
		Date date = stringToDate(d);
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return dateToString(now.getTime());
	}

	/**
	 * 返回几个小时后的时间
	 * 
	 * @param d
	 * @param hour
	 * @return
	 */
	public static Date getDateAfterHour(Date d, int hour) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.HOUR, now.get(Calendar.HOUR) + hour);
		return now.getTime();
	}

	/**
	 * 返回几分钟后的某个时间
	 * 
	 * @param d
	 * @param minutes
	 * @return
	 */
	public static Date getDateAfterMinute(Date d, int minutes) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + minutes);
		return now.getTime();
	}

	/**
	 * 比较两个日期的大小 true date1比date2前 false date1比date2后
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean dateCompare(Date date1, Date date2) {
		boolean dateComPareFlag = true;
		if (date2.compareTo(date1) != 1) {
			dateComPareFlag = false;
		}
		return dateComPareFlag;
	}

	/**
	 * 返回两时间之差
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static long dateMinus(Date startTime, Date endTime) {
		return (endTime.getTime() - startTime.getTime()) / (1000 * 60 * 60);
	}

	//计算两个string类的时间差
	public static String getTimeDifference(Date startTime, Date endTime) throws ParseException {
		long totalhours = (startTime.getTime() - endTime.getTime()) / (1000 * 60 * 60);//时   
		long totalminutes = (startTime.getTime() - endTime.getTime()) / (1000 * 60) - totalhours * 60;//分   
		long totalseconds = (startTime.getTime() - endTime.getTime()) / (1000) - totalminutes * 60;//秒   
		String total_time = totalhours + "时" + totalminutes + "分" + totalseconds + "秒";
		return total_time;
	}

	//计算考试的剩余时间
	public static String examRemainTime(String startTime, String endTime) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间   
		Date outdate = sdf.parse(endTime);
		Date indate = sdf.parse(startTime);
		long totalminutes = (outdate.getTime() - indate.getTime()) / (1000 * 60);//分   
		long totalseconds = (outdate.getTime() - indate.getTime()) / (1000) - totalminutes * 60;//秒  
		String remainTime = totalminutes + "#" + totalseconds;
		return remainTime;
	}

	/**
	 * String类型日期转Date
	 * 
	 * @throws ParseException
	 * */
	public static Date stringToDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化时间   
		Date toDate = sdf.parse(date);
		return toDate;
	}

	/**
	 * Date类型日期转String类型
	 * */
	public static String dateToString(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化时间   
		String toStrDate = sdf.format(date);
		return toStrDate;
	}

	/**
	 * 取得两个时间段的时间间隔
	 * 
	 * @author color
	 * @param t1
	 *            时间1
	 * @param t2
	 *            时间2
	 * @return t2 与t1的间隔天数
	 * @throws ParseException
	 *             如果输入的日期格式不是0000-00-00 格式抛出异常
	 */
	public static int getBetweenDays(String t1, String t2) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int betweenDays = 0;
		Date d1 = format.parse(t1);
		Date d2 = format.parse(t2);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		// 保证第二个时间一定大于第一个时间      
		if (c1.after(c2)) {
			c1 = c2;
			c2.setTime(d1);
		}
		int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
		for (int i = 0; i < betweenYears; i++) {
			c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
			betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
		}
		return betweenDays;
	}

	/**
	 * 根据年月得到最后一天和第一天 需要注意的是：月份是从0开始的，比如说如果输入5的话，实际上显示的是4月份的最后一天 这里好像有点问题
	 * */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	public static String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * 获取某年第一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 * @throws ParseException
	 */
	public static String getCurrYearFirst(int year) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return dateToString(currYearFirst);
	}

	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 * @throws ParseException
	 */
	public static String getCurrYearLast(int year) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		return dateToString(currYearLast);
	}

	//获取季度
	public static String getThisSeasonTime(int month) {
		String quarter = "";
		if (month >= 1 && month <= 3) {
			quarter = "Spring";
		}
		if (month >= 4 && month <= 6) {
			quarter = "Summer";
		}
		if (month >= 7 && month <= 9) {
			quarter = "Autumn";
		}
		if (month >= 10 && month <= 12) {
			quarter = "Winter";
		}
		return quarter;
	}

	//获取几月到几月
	public static String getMonthToMonth(int month) {
		String monthToMonth = "";
		String[] months = { "(January-March)", "(April-June)", "(July-September)", "(October-December)" };
		int index = month / 3;
		monthToMonth = months[index];
		return monthToMonth;
	}

	//根据月得到该季度的第一个月
	public static int getFirstMonthByQuarter(int month) {
		int quarter = 0;
		if (month >= 1 && month <= 3) {
			quarter = 1;
		}
		if (month >= 4 && month <= 6) {
			quarter = 4;
		}
		if (month >= 7 && month <= 9) {
			quarter = 7;
		}
		if (month >= 10 && month <= 12) {
			quarter = 10;
		}
		return quarter;
	}

	//取得当前时间
	//	public static Date getDateTime(String dateTime) {
	//		Date strDate = java.sql.Date.valueOf(dateTime);
	//		return strDate;
	//	}

	@SuppressWarnings("static-access")
	public static int getMonth(String dateTime) {
		Calendar c = Calendar.getInstance();
		c.setTime(getDateTime(dateTime));
		int month = c.get(c.MONTH) + 1;
		return month;
	}

	@SuppressWarnings("static-access")
	public static int getYear(String dateTime) {
		Calendar c = Calendar.getInstance();
		c.setTime(getDateTime(dateTime));
		int year = c.get(c.YEAR);
		return year;
	}

	// 用来全局控制 上一周，本周，下一周的周数变化
	private static int weeks = 0;

	// 获得当前日期与本周一相差的天数
	private static int getMondayPlus(String date) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(getDateTime(date));
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return 2 - dayOfWeek;
		}
	}

	// 获得上周星期一的日期
	public static String getPreviousMonday(String date) {
		weeks--;
		int mondayPlus = getMondayPlus(date);
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.setTime(getDateTime(date));
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得本周星期一的日期
	public static String getCurrentMonday(String date) {
		weeks = 0;
		int mondayPlus = getMondayPlus(date);
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.setTime(getDateTime(date));
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得下周星期一的日期
	public static String getNextMonday(String date) {
		weeks++;
		int mondayPlus = getMondayPlus(date);
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.setTime(getDateTime(date));
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得相应周的周日的日期
	public static String getSunday(String date) {
		int mondayPlus = getMondayPlus(date);
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.setTime(getDateTime(date));
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	//获得该日期所在周的所有日期
	public static List<String> getWeekAllDate(String date) {
		SimpleDateFormat dateformat = new SimpleDateFormat("MM-dd");
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(getDateTime(date));
		List<String> dateList = new ArrayList<String>();
		for (int i = 0; i < 7; i++) {
			c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + i);
			dateList.add(dateformat.format(c.getTime()));
		}
		return dateList;
	}

	//获得两个日期所在周的所有日期组合
	public static List<String> getTwoWeekAllDate(String date1, String date2) {
		System.out.println("ppp" + date1 + date2);
		SimpleDateFormat dateformat = new SimpleDateFormat("MM-dd");
		Calendar c1 = new GregorianCalendar();
		c1.setFirstDayOfWeek(Calendar.MONDAY); //中国传统每个星期是从星期一开始的，如需从星期天开始则为  Calendar.MONDAY-1
		c1.setTime(getDateTime(date1));
		Calendar c2 = new GregorianCalendar();
		c2.setFirstDayOfWeek(Calendar.MONDAY);
		c2.setTime(getDateTime(date2));
		List<String> dateList = new ArrayList<String>();
		for (int i = 0; i < 7; i++) {
			c1.set(Calendar.DAY_OF_WEEK, c1.getFirstDayOfWeek() + i);
			c2.set(Calendar.DAY_OF_WEEK, c2.getFirstDayOfWeek() + i);
			dateList.add(dateformat.format(c1.getTime()) + "/" + dateformat.format(c2.getTime()));
		}
		return dateList;
	}

	/*
	 * public static void main(String[] args) {
	 * System.out.println(getTwoWeekAllDate("2012-08-22","2012-08-30").get(0));
	 * //System.out.println(getPreviousMonday("2012-08-30"));
	 * //System.out.println(getLastDayOfMonth(2012, 1)); List<Date> list =
	 * getAllTheDateOftheMonth(new Date()); for(Date date: list){
	 * SimpleDateFormat matter1=new SimpleDateFormat("MM-dd");
	 * System.out.println(matter1.format(date));
	 * //System.out.println(date.toString()); } }
	 */
	/**
	 * 获得指定日期的月中的所有日期
	 * */
	public static List<Date> getAllTheDateOftheMonth(Date date) {
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		int month = cal.get(Calendar.MONTH);
		while (cal.get(Calendar.MONTH) == month) {
			list.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}

	/**
	 * 根据日期计算所在周的周一和周日
	 * 
	 * @param time
	 *            指定的日期
	 */
	public static String convertWeekByDate(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		//判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		System.out.println("要计算日期为:" + sdf.format(cal.getTime())); //输出要计算日期
		cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值 
		String imptimeBegin = sdf.format(cal.getTime());
		System.out.println("所在周星期一的日期：" + imptimeBegin);
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		System.out.println("所在周星期日的日期：" + imptimeEnd);
		return imptimeBegin + "," + imptimeEnd;
	}

	/*
	 * public static void main(String[] args) { Calendar c_begin = new
	 * GregorianCalendar(); Calendar c_end = new GregorianCalendar();
	 * DateFormatSymbols dfs = new DateFormatSymbols(); String[] weeks =
	 * dfs.getWeekdays();
	 * 
	 * c_begin.set(2011, 11, 31); // Calendar的月从0-11，所以4月是3. c_end.set(2012, 11,
	 * 31); // Calendar的月从0-11，所以5月是4.
	 * 
	 * int count = 1; c_end.add(Calendar.DAY_OF_YEAR, 1); // 结束日期下滚一天是为了包含最后一天
	 * 
	 * while (c_begin.before(c_end)) { System.out.println("第" + count + "周  日期："
	 * + new java.sql.Date(c_begin.getTime().getTime()) + ", " +
	 * weeks[c_begin.get(Calendar.DAY_OF_WEEK)]);
	 * 
	 * if (c_begin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) { count++; }
	 * c_begin.add(Calendar.DAY_OF_YEAR, 1); }
	 * 
	 * }
	 */

	/*
	 * public static void main(String[] args) throws ParseException { String
	 * startDate =
	 * FormatDate.dateToString(FormatDate.getDateAfterDay(FormatDate.
	 * stringToDate("2012-01-01"), 7)); System.out.println(startDate);
	 * convertWeekByDate(new Date()); }
	 */

	/*
	 * public static void main(String[] args) { List<String> testStr =
	 * getDays(new Date(), 4, "yyyy-MM-dd");
	 * 
	 * for (int i = 0; i < testStr.size(); i++) {
	 * System.out.println(testStr.get(i)); }
	 * 
	 * }
	 */

	public static List<String> getDays(Date day, int preDays, String format) {
		List<String> returnValue = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		for (int i = 0; i < preDays; i++) {
			c.add(Calendar.DAY_OF_YEAR, -1);
			/*
			 * while(c.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY ||
			 * c.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY) {
			 * c.add(Calendar.DAY_OF_YEAR, -1); }
			 */
			returnValue.add(new String(sdf.format(c.getTime())));
		}
		return returnValue;
	}

}
