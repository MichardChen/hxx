package com.framework.model;

import java.io.Serializable;

public class QuestionListModel implements Serializable{
	
	private Integer id;
	//
	private String mobile;
	//
	private String linkMan;
	//
	private String question;
	//
	private String employeeId;
	//
	private String status;
	//
	private String createTime;
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
	public String getEmployeeId() {
		return employeeId;
	}
	public String getStatus() {
		return status;
	}
	public String getCreateTime() {
		return createTime;
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
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	

}
