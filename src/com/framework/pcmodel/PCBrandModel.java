package com.framework.pcmodel;

import java.io.Serializable;
import java.util.List;

public class PCBrandModel implements Serializable{

	private int id;
	private String brandIcon;
	private String brand;
	private List<PCBrandSeriesModel> seriesList;
	
	public List<PCBrandSeriesModel> getSeriesList() {
		return seriesList;
	}
	public void setSeriesList(List<PCBrandSeriesModel> seriesList) {
		this.seriesList = seriesList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrandIcon() {
		return brandIcon;
	}
	public void setBrandIcon(String brandIcon) {
		this.brandIcon = brandIcon;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
}
