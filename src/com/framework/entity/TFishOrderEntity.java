package com.framework.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-14 21:01:28
 */
public class TFishOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String orderNo;
	//
	private String orderTypeCd;
	//
	private Integer infoId;
	//
	private Integer fromUserId;
	//
	private Integer toUserId;
	//
	private BigDecimal firstPay;
	//
	private BigDecimal secondPay;
	//
	private String receiveAddress;
	//
	private String receivePerson;
	//
	private String receiveMobile;
	//
	private String mark;
	//
	private String needs;
	//
	private String status;
	//
	private Date createTime;
	//
	private Date updateTime;

	private int updateBy;

	private String firstPayType;

	private String secondPayType;

	private String logisticsNo;

	private String logisticsInfo;

	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	public String getLogisticsInfo() {
		return logisticsInfo;
	}

	public void setLogisticsInfo(String logisticsInfo) {
		this.logisticsInfo = logisticsInfo;
	}

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：
	 */
	public void setOrderTypeCd(String orderTypeCd) {
		this.orderTypeCd = orderTypeCd;
	}
	/**
	 * 获取：
	 */
	public String getOrderTypeCd() {
		return orderTypeCd;
	}
	/**
	 * 设置：
	 */
	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}
	/**
	 * 获取：
	 */
	public Integer getInfoId() {
		return infoId;
	}
	/**
	 * 设置：
	 */
	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}
	/**
	 * 获取：
	 */
	public Integer getFromUserId() {
		return fromUserId;
	}
	/**
	 * 设置：
	 */
	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}
	/**
	 * 获取：
	 */
	public Integer getToUserId() {
		return toUserId;
	}
	/**
	 * 设置：
	 */
	public void setFirstPay(BigDecimal firstPay) {
		this.firstPay = firstPay;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getFirstPay() {
		return firstPay;
	}
	/**
	 * 设置：
	 */
	public void setSecondPay(BigDecimal secondPay) {
		this.secondPay = secondPay;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getSecondPay() {
		return secondPay;
	}
	/**
	 * 设置：
	 */
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
	/**
	 * 获取：
	 */
	public String getReceiveAddress() {
		return receiveAddress;
	}
	/**
	 * 设置：
	 */
	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}
	/**
	 * 获取：
	 */
	public String getReceivePerson() {
		return receivePerson;
	}
	/**
	 * 设置：
	 */
	public void setReceiveMobile(String receiveMobile) {
		this.receiveMobile = receiveMobile;
	}
	/**
	 * 获取：
	 */
	public String getReceiveMobile() {
		return receiveMobile;
	}
	/**
	 * 设置：
	 */
	public void setMark(String mark) {
		this.mark = mark;
	}
	/**
	 * 获取：
	 */
	public String getMark() {
		return mark;
	}
	/**
	 * 设置：
	 */
	public void setNeeds(String needs) {
		this.needs = needs;
	}
	/**
	 * 获取：
	 */
	public String getNeeds() {
		return needs;
	}
	/**
	 * 设置：
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}


	public int getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	public String getFirstPayType() {
		return firstPayType;
	}

	public void setFirstPayType(String firstPayType) {
		this.firstPayType = firstPayType;
	}

	public String getSecondPayType() {
		return secondPayType;
	}

	public void setSecondPayType(String secondPayType) {
		this.secondPayType = secondPayType;
	}
}
