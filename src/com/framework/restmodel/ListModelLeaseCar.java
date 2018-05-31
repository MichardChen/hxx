package com.framework.restmodel;

import java.io.Serializable;

public class ListModelLeaseCar implements Serializable{
	
	private int id;
	private String brand;
	private String carName;
	private String guidePrice;
	private String firstPayment;
	private String labels;
	private String realFirstPayment;
	private String monthPayment;
	private String icon;
	
	public int getId() {
		return id;
	}
	public String getBrand() {
		return brand;
	}
	public String getCarName() {
		return carName;
	}
	public String getGuidePrice() {
		return guidePrice;
	}
	public String getFirstPayment() {
		return firstPayment;
	}
	public String getLabels() {
		return labels;
	}
	public String getRealFirstPayment() {
		return realFirstPayment;
	}
	public String getMonthPayment() {
		return monthPayment;
	}
	public String getIcon() {
		return icon;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public void setGuidePrice(String guidePrice) {
		this.guidePrice = guidePrice;
	}
	public void setFirstPayment(String firstPayment) {
		this.firstPayment = firstPayment;
	}
	public void setLabels(String labels) {
		this.labels = labels;
	}
	public void setRealFirstPayment(String realFirstPayment) {
		this.realFirstPayment = realFirstPayment;
	}
	public void setMonthPayment(String monthPayment) {
		this.monthPayment = monthPayment;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}
