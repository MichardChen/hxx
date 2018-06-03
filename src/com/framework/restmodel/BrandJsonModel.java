package com.framework.restmodel;

import java.io.Serializable;

public class BrandJsonModel implements Serializable{
	
	private int id;
	private String name;
	private int pid;
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
