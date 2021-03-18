package cn.ewb.cq.com;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;

public class SystemUtil {
	private static Properties props;

	public static Properties getProperties(HttpServletRequest request){
		if(props==null){
	        try {
	        	 props = new Properties();
	        	 ClassLoader cl  = Thread.currentThread().getContextClassLoader();
		         String filePath = "System.properties";
		         InputStream in = cl.getResourceAsStream(filePath);
		         props.load(in);
//		         String value = props.getProperty ("M001");
//		         System.out.println(value);
		    } catch (Exception e) {
	        	e.printStackTrace();
		    }
		}
		return props;
	}
	
	public static String getLoginUser(HttpServletRequest request){
		String userCode =  (String)request.getSession().getAttribute("userCode");
		return userCode;
	}
	
	public static Date getSystemTime(){
		Date now  = new Date();
		return now;
	}
	
	public static String getInfoLog(HttpServletRequest request,String className,String methodName,boolean outFlg){
		String out = "";
		String infoLog = "";
		if(outFlg==true){
			out = "调用开始";
		}else{
			out = "调用结束";
		}
		infoLog ="======用户" + SystemUtil.getLoginUser(request)+ out + className + ":"+ methodName + "======";
		return infoLog;
	}
	
	public static String getErrorLog(HttpServletRequest request,String className,String methodName,String errorMsg){
		String errorLog = "";
		errorLog = "=======调用" + className + ":"+ methodName + "出现系统异常=======" + errorMsg + "=======";
		return errorLog;
	}
	
	public static String pwdEncryption(String pwd){
		String pwdEncrpt="";
		char[] array = pwd.toCharArray();
		for(int i=0;i<array.length;i++){
			array[i] = (char)(array[i] ^ 'B');
		}
		pwdEncrpt = new String(array);
		return pwdEncrpt;
	}
	
	public static String ShowValueName(String [] valueArray,String[] valueName, String value){
		String result="";
		for(int i=0;i<valueArray.length;i++){
			if(valueArray[i].equals(value)){
				result = valueName[i];
			}
		}
		return result;
	}
	
	public static int getQuota(Integer level){
		if(level==null){
			return 99;
		}else{
			return level;
		}
	}
	
	public static String getQuota2(Integer level){
		if(level==null){
			return "无限制";
		}else{
			return level.toString();
		}
	}
	
	public static String getRandom(int num){
		String ret ="";
		Integer data = 1+ (int)(Math.random()*num);
		ret = data.toString();
		return ret;
	}
	
}
