package com.framework.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CarSecondhandListModel implements Serializable{
	
	//
	private Integer id;
	//
	private String brand;
	//
	private String carName;
	//
	private String provinceId;
	//
	private String cityId;
	//
	private BigDecimal kilomiters;
	//
	private String year;
	//
	private BigDecimal firstPayment;
	//
	private BigDecimal monthPayment;
	//
	private String createBy;
	//
	private String updateBy;
	//
	private String updateTime;
	//
	private String createTime;
	//
	private String descUrl;
	
	private String flg;
	
	public String getFlg() {
		return flg;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	public Integer getId() {
		return id;
	}
	public String getBrand() {
		return brand;
	}
	public String getCarName() {
		return carName;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public BigDecimal getKilomiters() {
		return kilomiters;
	}
	public String getYear() {
		return year;
	}
	public BigDecimal getFirstPayment() {
		return firstPayment;
	}
	public BigDecimal getMonthPayment() {
		return monthPayment;
	}
	public String getCreateBy() {
		return createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public String getCreateTime() {
		return createTime;
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
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public void setKilomiters(BigDecimal kilomiters) {
		this.kilomiters = kilomiters;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setFirstPayment(BigDecimal firstPayment) {
		this.firstPayment = firstPayment;
	}
	public void setMonthPayment(BigDecimal monthPayment) {
		this.monthPayment = monthPayment;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}
}
