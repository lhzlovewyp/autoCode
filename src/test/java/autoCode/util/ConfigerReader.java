/**
 * 
 */
package autoCode.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


/**
 * @author lvhaizhen
 *
 */
public class ConfigerReader {

	private static Properties properties=new Properties();
	
	public ConfigerReader(String path){
		loadProperties(path);
	}
	
	public static Properties getProperties(){
		return properties;
	}
	public static String get(String key){
		return properties.getProperty(key);
	}
	
	private static boolean loadProperties(String path){
		InputStream fileInputStream=null;
		InputStreamReader read=null;
		try{
			String classPath=ConfigerReader.class.getResource("/").getPath();
			File file=new File(classPath+path);
			if(file.exists()){
				fileInputStream=new FileInputStream(file);
				read=new InputStreamReader(fileInputStream,"UTF-8");
				properties.load(read);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}finally{
			try{
				if(fileInputStream!=null){
					fileInputStream.close();
				}
			}catch(Exception e){
			}
			try{
				if(read!=null){
					read.close();
				}
			}catch(Exception e){
			}
		}
	}
}
