package com.framework.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-22 21:25:48
 */
public class TCarImportEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String icon;
	//
	private Integer brand;
	//
	private String carName;
	//
	private BigDecimal nowPrice;
	//
	private BigDecimal marketPrice;
	//
	private BigDecimal maxSave;
	//
	private String mark;
	//
	private String carColor;
	//
	private String favour;
	//
	private String servicePiror;
	//
	private Integer hot;
	//
	private Integer cartParamsId;
	//
	private Integer cartParam2Id;
	//
	private String labels;
	
	private String titleLabel;
	//
	private Integer createBy;
	//
	private Integer updateBy;
	//
	private Timestamp createTime;
	//
	private Timestamp updateTime;
	//
	private String descUrl;
	//
	private Integer carSeriesId;
	//
	private String carLevelCd;
	//
	private String carClassCd;
	
	public String getTitleLabel() {
		return titleLabel;
	}
	public void setTitleLabel(String titleLabel) {
		this.titleLabel = titleLabel;
	}
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
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public BigDecimal getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(BigDecimal nowPrice) {
		this.nowPrice = nowPrice;
	}
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	public BigDecimal getMaxSave() {
		return maxSave;
	}
	public void setMaxSave(BigDecimal maxSave) {
		this.maxSave = maxSave;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	public String getFavour() {
		return favour;
	}
	public void setFavour(String favour) {
		this.favour = favour;
	}
	public String getServicePiror() {
		return servicePiror;
	}
	public void setServicePiror(String servicePiror) {
		this.servicePiror = servicePiror;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
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
	public String getLabels() {
		return labels;
	}
	public void setLabels(String labels) {
		this.labels = labels;
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
	public String getDescUrl() {
		return descUrl;
	}
	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}
	public Integer getCarSeriesId() {
		return carSeriesId;
	}
	public void setCarSeriesId(Integer carSeriesId) {
		this.carSeriesId = carSeriesId;
	}
	public String getCarLevelCd() {
		return carLevelCd;
	}
	public void setCarLevelCd(String carLevelCd) {
		this.carLevelCd = carLevelCd;
	}
	public String getCarClassCd() {
		return carClassCd;
	}
	public void setCarClassCd(String carClassCd) {
		this.carClassCd = carClassCd;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
