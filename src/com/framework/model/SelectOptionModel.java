package com.framework.model;

import java.io.Serializable;

public class SelectOptionModel implements Serializable{

	private Integer key;
	private String value;
	public Integer getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
