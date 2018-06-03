package com.framework.pcmodel;

import java.io.Serializable;

public class ImportCarPCListModel implements Serializable{
	
	private int id;
	private String titleLabel;
	private String icon;
	private String brandSeries;
	private String name;
	private String labels;
	private String nowPrice;
	private String marketPrice;
	private String savePrice;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitleLabel() {
		return titleLabel;
	}
	public void setTitleLabel(String titleLabel) {
		this.titleLabel = titleLabel;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getBrandSeries() {
		return brandSeries;
	}
	public void setBrandSeries(String brandSeries) {
		this.brandSeries = brandSeries;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabels() {
		return labels;
	}
	public void setLabels(String labels) {
		this.labels = labels;
	}
	public String getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(String nowPrice) {
		this.nowPrice = nowPrice;
	}
	public String getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getSavePrice() {
		return savePrice;
	}
	public void setSavePrice(String savePrice) {
		this.savePrice = savePrice;
	}
	
	

}
