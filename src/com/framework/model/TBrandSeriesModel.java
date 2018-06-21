package com.framework.model;

import java.io.Serializable;

public class TBrandSeriesModel implements Serializable{
	
	private Integer id;
	//
	private Integer brandId;
	//
	private String carSerial;
	public Integer getId() {
		return id;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public String getCarSerial() {
		return carSerial;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public void setCarSerial(String carSerial) {
		this.carSerial = carSerial;
	}
	
	

}
