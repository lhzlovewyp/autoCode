package autoCode.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {

	
	public static String genRealPath(String path){
		if(path == null || path.trim()==""){
			return path;
		}
		String re = "\\$\\{([^${}]*)}";
 
		Pattern p = Pattern.compile(re);
		Matcher m = p.matcher(path);
		while(m.find()){
			String str=m.group(1);
			String temp=ConfigerReader.get(str);
			path=path.replace("${"+str+"}", temp);
		}
       return path;
	}
}
