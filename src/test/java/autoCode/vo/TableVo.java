package autoCode.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class TableVo {

	private String name;
	
	private String desc;
	
	private List<AttrVo> attrs;
	
	private AttrVo pk;
	
	public void addAttrVo(AttrVo vo){
		if(CollectionUtils.isEmpty(attrs)){
			attrs=new ArrayList<AttrVo>();
		}
		attrs.add(vo);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<AttrVo> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<AttrVo> attrs) {
		this.attrs = attrs;
	}

	public AttrVo getPk() {
		return pk;
	}

	public void setPk(AttrVo pk) {
		this.pk = pk;
	}

	
	
	
}
