package com.framework.restmodel;

import java.io.Serializable;

public class PCImportCarListModel implements Serializable{
	
	private int id;
	private String icon;
	private String name;
	private String nowPrice;
	private String primePrice;
	private String labels;
	private String titleLabels;
	public int getId() {
		return id;
	}
	public String getIcon() {
		return icon;
	}
	public String getName() {
		return name;
	}
	public String getNowPrice() {
		return nowPrice;
	}
	public String getPrimePrice() {
		return primePrice;
	}
	public String getLabels() {
		return labels;
	}
	public String getTitleLabels() {
		return titleLabels;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNowPrice(String nowPrice) {
		this.nowPrice = nowPrice;
	}
	public void setPrimePrice(String primePrice) {
		this.primePrice = primePrice;
	}
	public void setLabels(String labels) {
		this.labels = labels;
	}
	public void setTitleLabels(String titleLabels) {
		this.titleLabels = titleLabels;
	}
	
	

}
