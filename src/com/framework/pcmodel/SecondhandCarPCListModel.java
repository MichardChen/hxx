package com.framework.pcmodel;

import java.io.Serializable;

public class SecondhandCarPCListModel implements Serializable{
	
	private int id;
	private String icon;
	private String brandSeries;
	private String brand;
	private String name;
	private String labels;
	private String firstPayment;
	private String monthPayment;
	private String kilomiters;
	private String date;
	
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
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
	public String getKilomiters() {
		return kilomiters;
	}
	public void setKilomiters(String kilomiters) {
		this.kilomiters = kilomiters;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
