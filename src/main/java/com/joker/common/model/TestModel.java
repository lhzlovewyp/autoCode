package com.joker.common.model;

import com.joker.core.annotation.Attr;
import com.joker.core.annotation.Table;

@Table(name = "test_model",desc="测试模型")
public class TestModel extends BaseModel{

	@Attr(column="name",desc="名称",dataType="varchar",length=0)
	private String name;
	
	@Attr(column="code",desc="编码",dataType="varchar",length=100,isNull=false)
	private String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
