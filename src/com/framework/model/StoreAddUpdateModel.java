package com.framework.model;

import java.io.File;
import java.io.Serializable;

public class StoreAddUpdateModel implements Serializable{

	private int id;
	private File icon;
	private String title;
	private String content;
	private String url;
	public int getId() {
		return id;
	}
	public File getIcon() {
		return icon;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getUrl() {
		return url;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIcon(File icon) {
		this.icon = icon;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
