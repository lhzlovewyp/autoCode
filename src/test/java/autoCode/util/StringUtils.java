package autoCode.util;

public class StringUtils {
	
	/**首字母小写.*/
	public static String firstLow(String str){
		char[] chars=new char[1];  
        chars[0]=str.charAt(0);  
        String temp=new String(chars);  
        if(chars[0]>='A'  &&  chars[0]<='Z')  
        {  
            return (str.replaceFirst(temp,temp.toLowerCase()));  
        }  
        return str;
	}
	
}
