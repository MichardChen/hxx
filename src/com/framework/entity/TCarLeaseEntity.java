package com.framework.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-22 21:25:48
 */
public class TCarLeaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String icon;
	//
	private String brand;
	//
	private String carName;
	//
	private String year;
	//
	private String carTypeInfo;
	//
	private BigDecimal firstPayment;
	//
	private BigDecimal monthPayment;
	//
	private String titleLabel;
	//
	private Integer carSeriesId;
	//
	private BigDecimal carCost;
	//
	private String carColor;
	//
	private BigDecimal firmCost;
	//
	private Integer periods;
	//
	private Integer labelId;
	//
	private BigDecimal realFirstPayment;
	//
	private BigDecimal serviceFee;
	//
	private BigDecimal finalPayment;
	//
	private Integer cartParamsId;
	//
	private Integer createBy;
	//
	private Timestamp createTime;
	//
	private Timestamp updateTime;
	//
	private Integer updateBy;
	//
	private String descUrl;
	//
	private Integer cartParam2Id;
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
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCarTypeInfo() {
		return carTypeInfo;
	}
	public void setCarTypeInfo(String carTypeInfo) {
		this.carTypeInfo = carTypeInfo;
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
	public String getTitleLabel() {
		return titleLabel;
	}
	public void setTitleLabel(String titleLabel) {
		this.titleLabel = titleLabel;
	}
	public Integer getCarSeriesId() {
		return carSeriesId;
	}
	public void setCarSeriesId(Integer carSeriesId) {
		this.carSeriesId = carSeriesId;
	}
	public BigDecimal getCarCost() {
		return carCost;
	}
	public void setCarCost(BigDecimal carCost) {
		this.carCost = carCost;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	public BigDecimal getFirmCost() {
		return firmCost;
	}
	public void setFirmCost(BigDecimal firmCost) {
		this.firmCost = firmCost;
	}
	public Integer getPeriods() {
		return periods;
	}
	public void setPeriods(Integer periods) {
		this.periods = periods;
	}
	public Integer getLabelId() {
		return labelId;
	}
	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}
	public BigDecimal getRealFirstPayment() {
		return realFirstPayment;
	}
	public void setRealFirstPayment(BigDecimal realFirstPayment) {
		this.realFirstPayment = realFirstPayment;
	}
	public BigDecimal getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(BigDecimal serviceFee) {
		this.serviceFee = serviceFee;
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
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public String getDescUrl() {
		return descUrl;
	}
	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}
	public Integer getCartParam2Id() {
		return cartParam2Id;
	}
	public void setCartParam2Id(Integer cartParam2Id) {
		this.cartParam2Id = cartParam2Id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
