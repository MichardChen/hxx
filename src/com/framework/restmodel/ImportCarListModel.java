package com.framework.restmodel;

import java.io.Serializable;

public class ImportCarListModel implements Serializable{
	
	private int id;
	private String icon;
	private String name;
	private String nowPrice;
	private String primePrice;
	private String labels;
	private String titleLabel;
	
	public String getTitleLabel() {
		return titleLabel;
	}
	public void setTitleLabel(String titleLabel) {
		this.titleLabel = titleLabel;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(String nowPrice) {
		this.nowPrice = nowPrice;
	}
	public String getPrimePrice() {
		return primePrice;
	}
	public void setPrimePrice(String primePrice) {
		this.primePrice = primePrice;
	}
	public String getLabels() {
		return labels;
	}
	public void setLabels(String labels) {
		this.labels = labels;
	}
}
