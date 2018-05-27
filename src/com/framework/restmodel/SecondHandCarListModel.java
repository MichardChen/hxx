package com.framework.restmodel;

import java.io.Serializable;

public class SecondHandCarListModel implements Serializable{
	
	private int id;
	private String name;
	private String icon;
	private String city;
	private String date;
	private String kilometers;
	private String firstPayment;
	private String monthPayment;
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getKilometers() {
		return kilometers;
	}
	public void setKilometers(String kilometers) {
		this.kilometers = kilometers;
	}
	public String getFirstPayment() {
		return firstPayment;
	}
	public void setFirstPayment(String firstPayment) {
		this.firstPayment = firstPayment;
	}
	public String getMonthPayment() {
		return monthPayment;
	}
	public void setMonthPayment(String monthPayment) {
		this.monthPayment = monthPayment;
	}
	
	

}
