package com.framework.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author R & D
 * @email adang369@126.com
 * @date 2020-03-15 21:27:53
 */
public class BankCardEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String bankNo;
	//
	private String bankOpen;
	//
	private String bank;
	//
	private String bankOwner;
	//
	private Date createTime;
	//
	private Integer createBy;
	//
	private Date updateTime;
	//
	private Integer updateBy;
	//
	private String mark;
	//
	private Integer flg;

	private String createUserName;

	private String modifyUserName;

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
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
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	/**
	 * 获取：
	 */
	public String getBankNo() {
		return bankNo;
	}
	/**
	 * 设置：
	 */
	public void setBankOpen(String bankOpen) {
		this.bankOpen = bankOpen;
	}
	/**
	 * 获取：
	 */
	public String getBankOpen() {
		return bankOpen;
	}
	/**
	 * 设置：
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}
	/**
	 * 获取：
	 */
	public String getBank() {
		return bank;
	}
	/**
	 * 设置：
	 */
	public void setBankOwner(String bankOwner) {
		this.bankOwner = bankOwner;
	}
	/**
	 * 获取：
	 */
	public String getBankOwner() {
		return bankOwner;
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
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：
	 */
	public Integer getCreateBy() {
		return createBy;
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
	/**
	 * 设置：
	 */
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：
	 */
	public Integer getUpdateBy() {
		return updateBy;
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
	public void setFlg(Integer flg) {
		this.flg = flg;
	}
	/**
	 * 获取：
	 */
	public Integer getFlg() {
		return flg;
	}
}
