package com.framework.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class TFinanceListModel implements Serializable{
	
	private Integer id;
	//
	private String icon;
	//
	private String name;
	//
	private String lowRate;
	//
	private String lowRefund;
	//
	private String timeDistance;
	//
	private String standard;
	//
	private String createBy;
	//
	private String createTime;
	public Integer getId() {
		return id;
	}
	public String getIcon() {
		return icon;
	}
	public String getName() {
		return name;
	}
	public String getLowRate() {
		return lowRate;
	}
	public String getLowRefund() {
		return lowRefund;
	}
	public String getTimeDistance() {
		return timeDistance;
	}
	public String getStandard() {
		return standard;
	}
	public String getCreateBy() {
		return createBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLowRate(String lowRate) {
		this.lowRate = lowRate;
	}
	public void setLowRefund(String lowRefund) {
		this.lowRefund = lowRefund;
	}
	public void setTimeDistance(String timeDistance) {
		this.timeDistance = timeDistance;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	

}
