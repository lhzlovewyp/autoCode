package autoCode.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtils {

	public static List<Field> getDeclaredFileds(Object object){
		Class<?> clazz;
		if(object instanceof Class){
			 clazz=(Class)object;
		}else{
			 clazz=object.getClass();
		}
		
		List<Field> result=new ArrayList<Field>();
		for(;clazz!=Object.class;clazz=clazz.getSuperclass()){
			System.out.println(clazz.getName());
			Field[] fields=clazz.getDeclaredFields();
			
			result.addAll(0,Arrays.asList(fields));
		}
		return result;
	}
}
