package utils;

public class StringUtil {
	/**
	 * 字符串转换成整数
	 * @param str
	 * @return
	 */
	public static Integer toInt(String str){
		Integer val=null;
		if(str!=null){
			val=Integer.parseInt(str);
		}
		return val;
	}
	/**
	 * 字符串转换成小数
	 * @param str
	 * @return
	 */
	public static Double toDouble(String str){
		Double val=null;
		if(str!=null){
			val=Double.parseDouble(str);
		}
		return val;
	}
	/**
	 * 字符串转换成null
	 * @param str
	 * @return
	 */
	public static String toNull(String str){
		if(str!=null && str.trim().length()==0){
			return null;
		}
		return str;
	}

}
