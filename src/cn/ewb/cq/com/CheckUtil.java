package cn.ewb.cq.com;

public class CheckUtil {
	
	public static boolean checkIsNum(String numStr){
		boolean result = false;
		result = numStr.matches("^\\d+$");
		return result;
	}
	
	
	public static boolean checkIsUserCode(String numStr){
		boolean result = false;
		result = numStr.matches("^\\d{8}$");
		return result;
	}
}
