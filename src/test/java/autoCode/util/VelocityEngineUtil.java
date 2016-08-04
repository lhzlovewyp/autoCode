/**
 * 
 */
package autoCode.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * @author lvhaizhen
 *
 */
public class VelocityEngineUtil {
	
	private static VelocityEngine ve;
	
	static{
		ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
	}
	

	public static void genFile(String template, Map<String, Object> map, String outputFile) {
		 Template t = ve.getTemplate(template,"UTF-8");
		 VelocityContext ctx = new VelocityContext();
		 
		 Set<Entry<String,Object>> set=map.entrySet();
		 for(Iterator<Entry<String,Object>> it=set.iterator();it.hasNext();){
			 Entry<String,Object> entry=it.next();
			 ctx.put(entry.getKey(),entry.getValue());
		 }
		 
		 //生成文件.
		File file=new File(outputFile);
		if(!file.exists()){
			try {
				file.createNewFile();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream fos = null;  
        BufferedWriter writer = null;  
		try {
			fos = new FileOutputStream(file);  
            writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));// 设置写入的文件编码,解决中文问题
			t.merge(ctx, writer);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			IOUtils.closeQuietly(fos);
			IOUtils.closeQuietly(writer);
		}
		
	}
}
