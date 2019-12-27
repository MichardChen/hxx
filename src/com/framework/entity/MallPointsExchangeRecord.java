package com.framework.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class MallPointsExchangeRecord implements Serializable{

	private int id;
	//用户类型
	private String userTypeCd;
	//用户ID
	private int userId;
	//产品ID
	private int productId;
	//备注
	private String mark;
	//状态
	private String status;
	//地址
	private String address;
	//物流
	private String logistic;
	//创建时间
	private Timestamp createTime;
	//更新时间
	private Timestamp updateTime;
	//消耗积分
	private int points;
	//兑换数量
	private int quality;
	//消耗金额
	private BigDecimal moneys;
	//兑换类型
	private String buyTypeCd;
	//收件人
	private String receiveName;
	//电话号码
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
