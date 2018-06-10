package com.framework.restmodel;

import java.io.Serializable;
import java.util.List;

public class BrandJsonModel implements Serializable{
	
	private int id;
	private String name;
	private int pid;
	private List<BrandSeriesMJsonModel> models;
	
	public List<BrandSeriesMJsonModel> getModels() {
		return models;
	}
	public void setModels(List<BrandSeriesMJsonModel> models) {
		this.models = models;
	}
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
