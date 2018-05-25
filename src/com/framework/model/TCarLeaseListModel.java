package com.framework.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class TCarLeaseListModel implements Serializable{
	
	private Integer id;
	//
	private String brand;
	//
	private String carName;
	//
	private BigDecimal firstPayment;
	//
	private BigDecimal monthPayment;
	//
	//
	private String carSeriesId;
	//
	private String createBy;
	//
	private String createTime;
	//
	private String updateTime;
	//
	private String updateBy;
	//
	private String descUrl;
	public Integer getId() {
		return id;
	}
	public String getBrand() {
		return brand;
	}
	public String getCarName() {
		return carName;
	}
	public BigDecimal getFirstPayment() {
		return firstPayment;
	}
	public BigDecimal getMonthPayment() {
		return monthPayment;
	}
	public String getCarSeriesId() {
		return carSeriesId;
	}
	public String getCreateBy() {
		return createBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public String getDescUrl() {
		return descUrl;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public void setFirstPayment(BigDecimal firstPayment) {
		this.firstPayment = firstPayment;
	}
	public void setMonthPayment(BigDecimal monthPayment) {
		this.monthPayment = monthPayment;
	}
	public void setCarSeriesId(String carSeriesId) {
		this.carSeriesId = carSeriesId;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}
}
