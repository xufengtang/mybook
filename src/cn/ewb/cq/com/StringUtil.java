package cn.ewb.cq.com;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringUtil {

	/***
	 * 非空检查
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(String str){
		boolean result = false;
		if(str!=null && !"".equals(str.trim())){
			result = true;
		}
		return result;
	}
	
	/***
	 * 长度检查
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isLessLenth(String str,int len){
		boolean result = false;
		if(str.length()<len){
			result = true;
		}
		return result;
	}
	
	/***
	 * 长度检查
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEqLenth(String str,int len){
		boolean result = false;
		if(str.length()==len){
			result = true;
		}
		return result;
	}
	
	/***
	 * 长度检查
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isGtLenth(String str,int len){
		boolean result = false;
		if(str.length()>len){
			result = true;
		}
		return result;
	}
	
	public static String getNotNullStr(Object str){
		if (str != null){
			return str.toString();
		}else{
			return "";
		}
	}
	
	public static BigDecimal StringToBigDecimal(String str){
		if(isNotNull(str)){
			return	new BigDecimal(str);
		}else{
			return	new BigDecimal("0");
		}
	}
	
	public static String BigDecimalToString(BigDecimal big){
		if(big != null){
			return big.toString();
		}else{
			return "0";
		}
	}
	
	public static String nulltoZero(String val){
		if(isNotNull(val)){
			return val;
		}else{
			return "0";
		}
	}
	
	public static boolean checkBigDecimal(String str){
		boolean ret = true;
		try{
			BigDecimal value = new BigDecimal(str);
		}catch(Exception ex){
			ret = false;
		}
		return ret;
	}
	
	public static boolean checkPassd(String str){
		boolean ret = true;
		if(!"是".equals(str)&&!"否".equals(str)){
			ret = false;
		}
		return ret;
	}
	
	
	public static String converNulltoSpace(String val){
		if(!StringUtil.isNotNull(val)){
			return "&nbsp;";
		}else{
			return val;
		}
		
	}
	
	public static String getYesNoName(String val){
		if("0".equals(val)){
			return "否";
		}else if("1".equals(val)){
			return "是";
		}else{
			return "";
		}
	}
	
	
	public static String moneyFormat(Object val){
		DecimalFormat df = new DecimalFormat("##,##0.00");
		if(val != null && !"".equals(val)){
			return df.format(val);
		}else{
			return "";
		}
	}
	
	public static String objectToString(Object o) {
		if (o != null) {
			return o.toString();
		} else {
			return "";
		}
	}
	
	public static String addDay(String nowDay,int addCount)throws Exception{
		Date newTime;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{		
			if(!isNotNull(nowDay)){
				return "";
			}else{
				  Date dateTime =  dateFormat.parse(nowDay);
				  Calendar cal = Calendar.getInstance();
				  cal.setTime(dateTime);
				  cal.add(Calendar.DAY_OF_YEAR, addCount);
				  newTime = cal.getTime();
				  return dateFormat.format(newTime);
			}
		}catch(Exception ex){
			throw ex;
		}
	}
	
	
	public static String addYear(String nowDay,int addCount)throws Exception{
		Date newTime;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{		
			if(!isNotNull(nowDay)){
				return "";
			}else{
				  Date dateTime =  dateFormat.parse(nowDay);
				  Calendar cal = Calendar.getInstance();
				  cal.setTime(dateTime);
				  cal.add(Calendar.YEAR, addCount);
				  newTime = cal.getTime();
				  return dateFormat.format(newTime);
			}
		}catch(Exception ex){
			throw ex;
		}
	}
	
	
	public static String fullzero(String inputStr,int num){
		int length = inputStr.length();
		for(;length < num;length++){
			inputStr = "0" + inputStr;
		}
		return inputStr;
	}
	
	public static String makeTradeId(){
		String ret="";
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date now  = SystemUtil.getSystemTime();
		String head = df.format(now);
		Integer randomNum = (int)(1+Math.random()*(999999 - 100000 + 1));
		String tail = randomNum.toString();
		ret = head + tail;
		return ret;
	}
	
	
}
