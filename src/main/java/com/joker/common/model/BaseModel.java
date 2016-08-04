package com.joker.common.model;

import com.joker.core.annotation.Attr;

public abstract class BaseModel {

	@Attr(column="id",desc="id",dataType="char",pk=true,defaultValue="1",length=36)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
