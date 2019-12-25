package com.framework.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class MallPointsExchangeRecord implements Serializable{

	private int id;
	
	private String userTypeCd;
	
	private int userId;
	
	private int productId;
	
	private String mark;
	
	private String status;
	
	private String address;
	
	private String logistic;
	
	private Timestamp createTime;
	
	private Timestamp updateTime;
	
	private int points;
	
	private int quality;
	
	private BigDecimal moneys;
	
	private String buyTypeCd;

	private String receiveName;
	private String receiveMobile;

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getReceiveMobile() {
		return receiveMobile;
	}

	public void setReceiveMobile(String receiveMobile) {
		this.receiveMobile = receiveMobile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserTypeCd() {
		return userTypeCd;
	}

	public void setUserTypeCd(String userTypeCd) {
		this.userTypeCd = userTypeCd;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogistic() {
		return logistic;
	}

	public void setLogistic(String logistic) {
		this.logistic = logistic;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public BigDecimal getMoneys() {
		return moneys;
	}

	public void setMoneys(BigDecimal moneys) {
		this.moneys = moneys;
	}

	public String getBuyTypeCd() {
		return buyTypeCd;
	}

	public void setBuyTypeCd(String buyTypeCd) {
		this.buyTypeCd = buyTypeCd;
	}
}
