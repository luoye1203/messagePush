package com.xzst.relation.mp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by LHT on 2018/7/10.
 */
public class DateUtils {


	//获取上周周一
	public static String  getToday(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Date dateTime=new Date();
		dateTime.setHours(0);
		dateTime.setMinutes(0);
		dateTime.setSeconds(0);
		String time = df.format(dateTime);


		return time;
	}


	//获取上周周一
	public static String  getYestoday(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Calendar date = Calendar.getInstance();
		date.add(Calendar.DATE,-1);
		Date dateTime=date.getTime();
		dateTime.setHours(0);
		dateTime.setMinutes(0);
		dateTime.setSeconds(0);
		String time = df.format(dateTime);


		return time;
	}







	//获取上周周一
	public static String  getLastWeekBeginDay(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar date = Calendar.getInstance(Locale.CHINA);
		date.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
		date.add(Calendar.WEEK_OF_MONTH,-1);//周数减一，即上周
		date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//日子设为星期一
		Date dateTime=date.getTime();
		dateTime.setHours(0);
		dateTime.setMinutes(0);
		dateTime.setSeconds(0);
		String time = df.format(dateTime);


		return time;
	}

	//获取上周周日
	public static String  getLastWeekEndDay(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar date = Calendar.getInstance(Locale.CHINA);
		date.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
		date.add(Calendar.WEEK_OF_MONTH,-1);//周数减一，即上周
		date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//日子设为星期一
		date.add(Calendar.DATE,6);
		Date dateTime=date.getTime();
		dateTime.setHours(0);
		dateTime.setMinutes(0);
		dateTime.setSeconds(0);
		String time = df.format(dateTime);

		return time;
	}

	//获取上上周周一
	public static String  getTwoWeeksAgoBeginDay(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar date = Calendar.getInstance(Locale.CHINA);
		date.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
		date.add(Calendar.WEEK_OF_MONTH,-2);//周数减一，即上周
		date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//日子设为星期一
		Date dateTime=date.getTime();
		dateTime.setHours(0);
		dateTime.setMinutes(0);
		dateTime.setSeconds(0);
		String time = df.format(dateTime);

		return time;
	}


	//获取上上周周日
	public static String  getTwoWeeksAgoEndDay(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar date = Calendar.getInstance(Locale.CHINA);
		date.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
		date.add(Calendar.WEEK_OF_MONTH,-2);//周数减一，即上周
		date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//日子设为星期一
		date.add(Calendar.DATE,6);
		Date dateTime=date.getTime();
		dateTime.setHours(0);
		dateTime.setMinutes(0);
		dateTime.setSeconds(0);
		String time = df.format(dateTime);

		return time;
	}



	//获取本周周一
	public static String  getThisWeekBeginDay(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar date = Calendar.getInstance(Locale.CHINA);
		date.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
		date.add(Calendar.WEEK_OF_MONTH,0);//本周
		date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//日子设为星期一
		Date dateTime=date.getTime();
		dateTime.setHours(0);
		dateTime.setMinutes(0);
		dateTime.setSeconds(0);
		String time = df.format(dateTime);


		return time;
	}
	//获取获取本月1号
	public static String  getThisMonthBeginDay(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar date = Calendar.getInstance(Locale.CHINA);
		date.add(Calendar.MONTH,0);//本月
		date.set(Calendar.DAY_OF_MONTH,1);
		Date dateTime=date.getTime();
		dateTime.setHours(0);
		dateTime.setMinutes(0);
		dateTime.setSeconds(0);
		String time = df.format(dateTime);


		return time;
	}

	//获取获取上月1号
	public static String  getLastMonthBeginDay(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar date = Calendar.getInstance(Locale.CHINA);
		date.add(Calendar.MONTH,-1);//上月
		date.set(Calendar.DAY_OF_MONTH,1);
		Date dateTime=date.getTime();
		dateTime.setHours(0);
		dateTime.setMinutes(0);
		dateTime.setSeconds(0);
		String time = df.format(dateTime);


		return time;
	}


	//获取获取上上月1号
	public static String  getTwoMonthsAgoBeginDay(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar date = Calendar.getInstance(Locale.CHINA);
		date.add(Calendar.MONTH,-2);//上上月
		date.set(Calendar.DAY_OF_MONTH,1);
		Date dateTime=date.getTime();
		dateTime.setHours(0);
		dateTime.setMinutes(0);
		dateTime.setSeconds(0);
		String time = df.format(dateTime);
		return time;
	}



	public static Date randomDate(String beginDate,String endDate){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start = format.parse(beginDate);
			Date end = format.parse(endDate);

			if(start.getTime() >= end.getTime()){
				return null;
			}

			long date = random(start.getTime(),end.getTime());

			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static long random(long begin,long end){
		long rtn = begin + (long)(Math.random() * (end - begin));
		if(rtn == begin || rtn == end){
			return random(begin,end);
		}
		return rtn;
	}


	public static void main(String[] args) {

		System.out.println(DateUtils.getLastWeekBeginDay());
		System.out.println(DateUtils.getLastWeekEndDay());
		System.out.println(DateUtils.getThisWeekBeginDay());
		System.out.println(DateUtils.getTwoWeeksAgoBeginDay());
		System.out.println(DateUtils.getTwoWeeksAgoEndDay());
		System.out.println("本月1号:"+DateUtils.getThisMonthBeginDay());
		System.out.println("上月1号:"+DateUtils.getLastMonthBeginDay());
		System.out.println("上上月1号:"+DateUtils.getTwoMonthsAgoBeginDay());
		System.out.println("今天:"+DateUtils.getToday());
		System.out.println("昨天天:"+DateUtils.getYestoday());


		Date date = randomDate("2018-01-01","2018-08-01");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
	}
}
