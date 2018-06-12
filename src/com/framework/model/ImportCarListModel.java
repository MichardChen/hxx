package com.framework.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ImportCarListModel implements Serializable{
	
	//
	private Integer id;
	//
	private String brand;
	//
	private String carName;
	//
	private BigDecimal nowPrice;
	//
	private BigDecimal marketPrice;
	
	private String createBy;
	//
	private String updateBy;
	//
	private String createTime;
	//
	private String updateTime;
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
	public BigDecimal getNowPrice() {
		return nowPrice;
	}
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}
	public String getCreateBy() {
		return createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public String getUpdateTime() {
		return updateTime;
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
	public void setNowPrice(BigDecimal nowPrice) {
		this.nowPrice = nowPrice;
	}
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}
	
	

}
