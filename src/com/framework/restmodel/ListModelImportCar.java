package com.framework.restmodel;

import java.io.Serializable;

public class ListModelImportCar implements Serializable{
	
	private int id;
	private String icon;
	private String brand;
	private String carName;
	private String nowPrice;
	private String markPrice;
	private String saveMoneys;
	
	
	public String getSaveMoneys() {
		return saveMoneys;
	}
	public void setSaveMoneys(String saveMoneys) {
		this.saveMoneys = saveMoneys;
	}
	public int getId() {
		return id;
	}
	public String getIcon() {
		return icon;
	}
	public String getBrand() {
		return brand;
	}
	public String getCarName() {
		return carName;
	}
	public String getNowPrice() {
		return nowPrice;
	}
	public String getMarkPrice() {
		return markPrice;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public void setNowPrice(String nowPrice) {
		this.nowPrice = nowPrice;
	}
	public void setMarkPrice(String markPrice) {
		this.markPrice = markPrice;
	}
	
	

}
