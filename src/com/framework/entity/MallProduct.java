package com.framework.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class MallProduct implements Serializable{
	
	private int id;
	//产品名称
	private String productTitle;
	//产品详情主图片
	private String detailLogo;
	//产品logo
	private String logos;
	//所需兑换积分
	private int needPoints;
	//产品详情
	private String productDetail;
	//备注
	private String mark;
	//产品类型
	private String productTypeCd;
	//用户类型(要面向哪类用户显示，目前固定1001-普通用户)
	private String userTypeCd;
	//创建时间
	private Timestamp createTime;
	//更新时间
	private Timestamp updateTime;
	//价格
	private BigDecimal price;
	//库存
	private int quality;
	//新增产品详情的url
	private String productDetailUrl;
	//状态 见常量 MALL_STATUS 值
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProductDetailUrl() {
		return productDetailUrl;
	}

	public void setProductDetailUrl(String productDetailUrl) {
		this.productDetailUrl = productDetailUrl;
	}

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
