package com.framework.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class TCarSecondhandModel implements Serializable{

	private Integer id;
	//
	private String icon;
	//
	private String titleLabel;
	//
	private Integer brand;
	//
	private String carName;
	//
	private Integer provinceId;
	//
	private Integer cityId;
	//
	private BigDecimal kilomiters;
	//
	private String year;
	//
	private BigDecimal firstPayment;
	//
	private BigDecimal monthPayment;
	//
	private BigDecimal age;
	//
	private String carLevelCd;
	//
	private String carColor;
	//
	private BigDecimal carCost;
	//
	private Integer carSeriesId;
	//
	private BigDecimal carTaxCost;
	//
	private Integer periods;
	//
	private String labels;
	//
	private BigDecimal finalPayment;
	//
	private String content;
	
	private String pcIcon;
	
	private Integer flg;
	
	

	public Integer getFlg() {
		return flg;
	}

	public void setFlg(Integer flg) {
		this.flg = flg;
	}

	public Integer getId() {
		return id;
	}

	public String getIcon() {
		return icon;
	}

	public String getTitleLabel() {
		return titleLabel;
	}

	public Integer getBrand() {
		return brand;
	}

	public String getCarName() {
		return carName;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public Integer getCityId() {
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

	public BigDecimal getAge() {
		return age;
	}

	public String getCarLevelCd() {
		return carLevelCd;
	}

	public String getCarColor() {
		return carColor;
	}

	public BigDecimal getCarCost() {
		return carCost;
	}

	public Integer getCarSeriesId() {
		return carSeriesId;
	}

	public BigDecimal getCarTaxCost() {
		return carTaxCost;
	}

	public Integer getPeriods() {
		return periods;
	}

	public String getLabels() {
		return labels;
	}

	public BigDecimal getFinalPayment() {
		return finalPayment;
	}

	public String getContent() {
		return content;
	}

	public String getPcIcon() {
		return pcIcon;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setTitleLabel(String titleLabel) {
		this.titleLabel = titleLabel;
	}

	public void setBrand(Integer brand) {
		this.brand = brand;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public void setCityId(Integer cityId) {
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

	public void setAge(BigDecimal age) {
		this.age = age;
	}

	public void setCarLevelCd(String carLevelCd) {
		this.carLevelCd = carLevelCd;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public void setCarCost(BigDecimal carCost) {
		this.carCost = carCost;
	}

	public void setCarSeriesId(Integer carSeriesId) {
		this.carSeriesId = carSeriesId;
	}

	public void setCarTaxCost(BigDecimal carTaxCost) {
		this.carTaxCost = carTaxCost;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public void setFinalPayment(BigDecimal finalPayment) {
		this.finalPayment = finalPayment;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setPcIcon(String pcIcon) {
		this.pcIcon = pcIcon;
	}
	
	
}
