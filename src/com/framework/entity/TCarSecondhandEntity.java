package com.framework.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-22 21:25:47
 */
public class TCarSecondhandEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
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
	private Integer cartParamsId;
	//
	private Integer cartParam2Id;
	//
	private Integer createBy;
	//
	private Integer updateBy;
	//
	private Timestamp updateTime;
	//
	private Timestamp createTime;
	//
	private String descUrl;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTitleLabel() {
		return titleLabel;
	}
	public void setTitleLabel(String titleLabel) {
		this.titleLabel = titleLabel;
	}
	public Integer getBrand() {
		return brand;
	}
	public void setBrand(Integer brand) {
		this.brand = brand;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public BigDecimal getKilomiters() {
		return kilomiters;
	}
	public void setKilomiters(BigDecimal kilomiters) {
		this.kilomiters = kilomiters;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public BigDecimal getFirstPayment() {
		return firstPayment;
	}
	public void setFirstPayment(BigDecimal firstPayment) {
		this.firstPayment = firstPayment;
	}
	public BigDecimal getMonthPayment() {
		return monthPayment;
	}
	public void setMonthPayment(BigDecimal monthPayment) {
		this.monthPayment = monthPayment;
	}
	public BigDecimal getAge() {
		return age;
	}
	public void setAge(BigDecimal age) {
		this.age = age;
	}
	public String getCarLevelCd() {
		return carLevelCd;
	}
	public void setCarLevelCd(String carLevelCd) {
		this.carLevelCd = carLevelCd;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	public BigDecimal getCarCost() {
		return carCost;
	}
	public void setCarCost(BigDecimal carCost) {
		this.carCost = carCost;
	}
	public Integer getCarSeriesId() {
		return carSeriesId;
	}
	public void setCarSeriesId(Integer carSeriesId) {
		this.carSeriesId = carSeriesId;
	}
	public BigDecimal getCarTaxCost() {
		return carTaxCost;
	}
	public void setCarTaxCost(BigDecimal carTaxCost) {
		this.carTaxCost = carTaxCost;
	}
	public Integer getPeriods() {
		return periods;
	}
	public void setPeriods(Integer periods) {
		this.periods = periods;
	}
	public String getLabels() {
		return labels;
	}
	public void setLabels(String labels) {
		this.labels = labels;
	}
	public BigDecimal getFinalPayment() {
		return finalPayment;
	}
	public void setFinalPayment(BigDecimal finalPayment) {
		this.finalPayment = finalPayment;
	}
	public Integer getCartParamsId() {
		return cartParamsId;
	}
	public void setCartParamsId(Integer cartParamsId) {
		this.cartParamsId = cartParamsId;
	}
	public Integer getCartParam2Id() {
		return cartParam2Id;
	}
	public void setCartParam2Id(Integer cartParam2Id) {
		this.cartParam2Id = cartParam2Id;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getDescUrl() {
		return descUrl;
	}
	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
