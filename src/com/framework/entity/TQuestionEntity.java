package com.framework.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 22:14:11
 */
public class TQuestionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String mobile;
	//
	private String linkMan;
	//
	private String question;
	//
	private Integer cartId;
	//
	private Integer employeeId;
	//
	private String status;
	//
	private Integer createBy;
	//
	private Timestamp createTime;
	//
	private Integer updateBy;
	//
	private Timestamp updateTime;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public String getMobile() {
		return mobile;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public String getQuestion() {
		return question;
	}
	public Integer getCartId() {
		return cartId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public String getStatus() {
		return status;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	
}
