package com.framework.restmodel;

import java.io.Serializable;

public class ListModelSecondhandCar implements Serializable{
	
	private int id;
	private String brand;
	private String carName;
	private String city;
	private String firstPayment;
	private String labels;
	private String date;
	private String monthPayment;
	private String kilomiters;
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
	public String getCity() {
		return city;
	}
	public String getFirstPayment() {
		return firstPayment;
	}
	public String getLabels() {
		return labels;
	}
	public String getDate() {
		return date;
	}
	public String getMonthPayment() {
		return monthPayment;
	}
	public String getKilomiters() {
		return kilomiters;
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
	public void setCity(String city) {
		this.city = city;
	}
	public void setFirstPayment(String firstPayment) {
		this.firstPayment = firstPayment;
	}
	public void setLabels(String labels) {
		this.labels = labels;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setMonthPayment(String monthPayment) {
		this.monthPayment = monthPayment;
	}
	public void setKilomiters(String kilomiters) {
		this.kilomiters = kilomiters;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	

}
