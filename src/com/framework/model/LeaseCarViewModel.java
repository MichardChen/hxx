package com.framework.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class LeaseCarViewModel {

	//
	private Integer id;
	//
	private String icon;
	//
	private Integer brand;
	
	private Integer salecount;
	//
	private String carName;
	//
	private String year;
	//
	private String carTypeInfo;
	//48期首付
	private BigDecimal firstPayment;
	//48期月供
	private BigDecimal monthPayment;
	//
	private String titleLabel;
	
	private Integer flg;
	//
	private Integer carSeriesId;
	//
	private BigDecimal carCost;
	//
	private String carColor;
	//
	private BigDecimal firmCost;
	//48期期数
	private Integer periods;
	//48期备注
	private String mark;
	//36期
	private BigDecimal firstPayment1;
	//36期月供
	private BigDecimal monthPayment1;
	//36期期数
	private Integer periods1;
	//36期备注
	private String mark1;
	//1+3首年首付
	private BigDecimal tfirstYearFirstPay;
	//1+3首年月供
	private BigDecimal tfirstYearMonthPayment;
	//1+3，一年后分期数
	private Integer tperiods;
	//1+3，一年后分期月供
	private BigDecimal tmonthPayment;
	//
	private String labels;
	//
	private BigDecimal realFirstPayment;
	//
	private BigDecimal serviceFee;
	////1+3，一年后尾款
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
	
	private String carLevelCd;
	
	private String content;

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

	public Integer getBrand() {
		return brand;
	}

	public void setBrand(Integer brand) {
		this.brand = brand;
	}

	public Integer getSalecount() {
		return salecount;
	}

	public void setSalecount(Integer salecount) {
		this.salecount = salecount;
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

	public Integer getFlg() {
		return flg;
	}

	public void setFlg(Integer flg) {
		this.flg = flg;
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

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public BigDecimal getFirstPayment1() {
		return firstPayment1;
	}

	public void setFirstPayment1(BigDecimal firstPayment1) {
		this.firstPayment1 = firstPayment1;
	}

	public BigDecimal getMonthPayment1() {
		return monthPayment1;
	}

	public void setMonthPayment1(BigDecimal monthPayment1) {
		this.monthPayment1 = monthPayment1;
	}

	public Integer getPeriods1() {
		return periods1;
	}

	public void setPeriods1(Integer periods1) {
		this.periods1 = periods1;
	}

	public String getMark1() {
		return mark1;
	}

	public void setMark1(String mark1) {
		this.mark1 = mark1;
	}

	public BigDecimal getTfirstYearFirstPay() {
		return tfirstYearFirstPay;
	}

	public void setTfirstYearFirstPay(BigDecimal tfirstYearFirstPay) {
		this.tfirstYearFirstPay = tfirstYearFirstPay;
	}

	public BigDecimal getTfirstYearMonthPayment() {
		return tfirstYearMonthPayment;
	}

	public void setTfirstYearMonthPayment(BigDecimal tfirstYearMonthPayment) {
		this.tfirstYearMonthPayment = tfirstYearMonthPayment;
	}

	public Integer getTperiods() {
		return tperiods;
	}

	public void setTperiods(Integer tperiods) {
		this.tperiods = tperiods;
	}

	public BigDecimal getTmonthPayment() {
		return tmonthPayment;
	}

	public void setTmonthPayment(BigDecimal tmonthPayment) {
		this.tmonthPayment = tmonthPayment;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
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

	public String getCarLevelCd() {
		return carLevelCd;
	}

	public void setCarLevelCd(String carLevelCd) {
		this.carLevelCd = carLevelCd;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
