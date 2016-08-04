package autoCode;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.joker.core.annotation.Attr;
import com.joker.core.annotation.Table;

import autoCode.util.ConfigerReader;
import autoCode.util.Constants;
import autoCode.util.FileUtils;
import autoCode.util.ReflectionUtils;
import autoCode.util.StringUtils;
import autoCode.util.VelocityEngineUtil;
import autoCode.vo.AttrVo;
import autoCode.vo.TableVo;

/**
 * 代码生成器主方法.
 * @author lvhaizhen
 *
 */
public class Main {

	private String CONFIG_FILE="config-test.properties";
	private  Map<String,Object> params=new HashMap<String,Object>();
	private Class modelClass;
	private Map<String,String> templateMap=new HashMap<String,String>();
	private Map<String,String> outputMap=new HashMap<String,String>(); 
	
	
	public static void main(String[] args) {
		//生成主对象方法.
		Main main=new Main();
		//加载配置文件,生成map对象.
		main.init();
	}
	
	public void init(){
		ConfigerReader reader=new ConfigerReader(CONFIG_FILE);
		//系统参数初始化.
		initParams();
		//读取模版数据.
		initTemplate();
		//开始生成数据
		render();
	}
	
	public void initParams(){
		String model=ConfigerReader.get("model");
		try {
			this.modelClass= Class.forName(model);
			String simpleName=modelClass.getSimpleName();
			String simpleNameFirstLow=StringUtils.firstLow(simpleName);
			
			
			initTableParams();
			
			
			
			params.put(Constants.MODEL_NAME, simpleName);
			params.put(Constants.MODEL_NAME_FIRSTLOW, simpleNameFirstLow);
			params.put(Constants.MAPPER_CLASS_PATH, ConfigerReader.get("project_path_mapperClass"));
			params.put("package_service", ConfigerReader.get("package_service"));
			params.put("package_service_impl", ConfigerReader.get("package_service_impl"));
			params.put("package_mapper", ConfigerReader.get("package_mapper"));
			params.put("package_controller", ConfigerReader.get("package_controller"));
			
			System.out.println("modelName:"+modelClass.getSimpleName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//生成sql需要的关键属性.
	private void initTableParams(){
		Table table =(Table) modelClass.getAnnotation(Table.class);
		TableVo tableVo=new TableVo();
		String tableName = table.name();
		String tableDesc=table.desc();
		tableVo.setName(tableName.toUpperCase());
		tableVo.setDesc(tableDesc);
		
		List<Field> fields=ReflectionUtils.getDeclaredFileds(modelClass);
		for(Field field:fields){
			AttrVo vo=this.getAttrVo(field);
			if(vo.isPk()){
				tableVo.setPk(vo);
			}
			tableVo.addAttrVo(vo);
		}
		params.put(Constants.TABLE, tableVo);
	}
	
	private AttrVo getAttrVo(Field field){
		Attr attr=(Attr)field.getAnnotation(Attr.class);
		if(attr==null){
			return null;
		}
		AttrVo result=new AttrVo();
		result.setColumn(attr.column());
		result.setDataType(attr.dataType());
		result.setDefaultValue(attr.defaultValue());
		result.setDesc(attr.desc());
		result.setLength(attr.length());
		result.setPk(attr.pk());
		result.setNull(attr.isNull());
		result.setVoname(field.getName());
		return result;
	}
	
	private void initTemplate(){
		String templatePrefix=FileUtils.genRealPath(ConfigerReader.get("template_path"));
		String project_path_sql=FileUtils.genRealPath(ConfigerReader.get("project_path_sql"));
		project_path_sql+="/"+params.get(Constants.MODEL_NAME_FIRSTLOW)+".sql";
		
		//sql目录
		templateMap.put("sql",templatePrefix+"/"+"sql.vm");
		outputMap.put("sql",project_path_sql);
		
		//maper.xml文件.
		String mapper_path=FileUtils.genRealPath(ConfigerReader.get("project_path_mapper"));
		templateMap.put("mapperXml",templatePrefix+"/"+"mapperXml.vm");
		outputMap.put("mapperXml",mapper_path+"/"+params.get(Constants.MODEL_NAME)+".xml");
		
	}
	
	//渲染数据,生成数据.
	private void render(){
		Set<Map.Entry<String,String>> set=templateMap.entrySet();
		for(Iterator<Map.Entry<String, String>> it=set.iterator();it.hasNext();){
			Map.Entry<String,String> entry=it.next();
			String key=entry.getKey();
			String template=entry.getValue();
			String output=outputMap.get(key);
			VelocityEngineUtil.genFile(template, params, output);
		}
	}

}
