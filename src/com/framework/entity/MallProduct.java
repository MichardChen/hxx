package com.framework.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class MallProduct implements Serializable{
	
	private int id;
	
	private String productTitle;
	
	private String detailLogo;
	
	private String logos;
	
	private int needPoints;
	
	private String productDetail;
	
	private String mark;
	
	private String productTypeCd;
	
	private String userTypeCd;
	
	private Timestamp createTime;
	
	private Timestamp updateTime;
	
	private BigDecimal price;
	
	private int quality;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getDetailLogo() {
		return detailLogo;
	}

	public void setDetailLogo(String detailLogo) {
		this.detailLogo = detailLogo;
	}

	public String getLogos() {
		return logos;
	}

	public void setLogos(String logos) {
		this.logos = logos;
	}

	public int getNeedPoints() {
		return needPoints;
	}

	public void setNeedPoints(int needPoints) {
		this.needPoints = needPoints;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getProductTypeCd() {
		return productTypeCd;
	}

	public void setProductTypeCd(String productTypeCd) {
		this.productTypeCd = productTypeCd;
	}

	public String getUserTypeCd() {
		return userTypeCd;
	}

	public void setUserTypeCd(String userTypeCd) {
		this.userTypeCd = userTypeCd;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}
}
