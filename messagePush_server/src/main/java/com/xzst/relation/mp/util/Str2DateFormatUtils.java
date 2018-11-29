package com.xzst.relation.mp.util;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * Created by LHT on 2018/3/7.
 *
 */
public class Str2DateFormatUtils {
	private static Logger LOG=Logger.getLogger(Str2DateFormatUtils.class);
	private static 	HashMap<String, String> dateRegFormat = new LinkedHashMap<String, String>();

	static {
		dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$", "yyyy-MM-dd-HH-mm-ss");//2014年3月12日 13时5分34秒，2014-03-12 12:05:34，2014/3/12 12:5:34
		dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$", "yyyy-MM-dd-HH-mm");//2014年3月12日 13时5分34秒，2014-03-12 12:05:34，2014/3/12 12:5:34
		dateRegFormat.put("^\\d{17}$", "yyyyMMddHHmmssSSS");//201403121205457
		dateRegFormat.put("^\\d{14}$", "yyyyMMddHHmmss");//201403121205
		dateRegFormat.put("^\\d{12}$", "yyyyMMddHHmm");//2014031212
	}

	public static String FormatDate(String dateStr,String needFormatStr){
		if(null==dateStr || "".equals(dateStr)){
			return "";
		}
		DateFormat formatterNeed =new SimpleDateFormat(needFormatStr);
		DateFormat formatterStandard;
		String dateReplace;
		String strSuccess="";
		try {
			for (String key : dateRegFormat.keySet()) {
				if (Pattern.compile(key).matcher(dateStr).matches()) {
					formatterStandard= new SimpleDateFormat(dateRegFormat.get(key));
					if (key.equals("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$") ||
							 key.equals("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$")) {
						dateReplace = dateStr.replaceAll("\\D+", "-");
						strSuccess = formatterNeed.format(formatterStandard.parse(dateReplace));

					} else if (key.equals("^\\d{17}$")) {
						strSuccess = formatterNeed.format(formatterStandard.parse(dateStr));
					}else if (key.equals("^\\d{14}$")) {
						strSuccess = formatterNeed.format(formatterStandard.parse(dateStr));
					}else if (key.equals("^\\d{12}$")) {
						strSuccess = formatterNeed.format(formatterStandard.parse(dateStr));
					}
					break;
				}
			}
		} catch (Exception e) {
			LOG.info("字符串: "+dateStr+ "   日期格式无效:");
//			throw new Exception( "日期格式无效");
		} finally {
			return strSuccess;
		}
	}

	public static String FormatDateFor17(String dateStr){
		return Str2DateFormatUtils.FormatDate(dateStr,"yyyyMMddHHmmssSSS");
	}


	public static void main(String[] args) {
		String[] dateStrArray=new String[]{
				"5612",
				"2014年03月12日 12时05分34秒",
				"2014/03/12 12:05:34",
				"2014-03-12 12:05:34",
				"2014-03-12 12:05",
				"20140312120534157",
				"20140312120534",
				"201403121205",
		};
		for(int i=0;i<dateStrArray.length;i++){
//			LOG.info(i+"  "+dateStrArray[i] +"------------------------------".substring(1,30-dateStrArray[i].length())+ FormatDate(dateStrArray[i],"yyyy-MM-dd HH:mm:ss.SSS"));
			LOG.info(i+"  "+dateStrArray[i] +"------------------------------".substring(1,30-dateStrArray[i].length())+ FormatDateFor17(dateStrArray[i]));
		}

	}

}
