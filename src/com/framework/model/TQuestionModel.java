package com.framework.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class TQuestionModel implements Serializable{

	private Integer id;
	//
	private String mobile;
	//
	private String linkMan;
	//
	private String question;
	//
	private String cartId;
	//
	private String employeeId;
	//
	private String status;
	//
	private String createBy;
	//
	private String createTime;
	//
	private String updateBy;
	//
	private String updateTime;
	
	private String typeCd;

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

	public String getCartId() {
		return cartId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getStatus() {
		return status;
	}

	public String getCreateBy() {
		return createBy;
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public String getTypeCd() {
		return typeCd;
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

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}

	
	
	
}
