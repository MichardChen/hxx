package com.framework.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class TSalecartListModel implements Serializable{
	
	//
	private Integer id;
	//
	private String mobile;
	//
	private String name;
	//
	private String mark;
	//
	private String createBy;
	//
	private String updateBy;
	//
	private String createTime;
	//
	private String updateTime;
	//
	private String status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	

}
