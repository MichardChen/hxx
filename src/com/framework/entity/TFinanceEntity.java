package com.framework.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 22:28:57
 */
public class TFinanceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
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
	private Integer createBy;
	//
	private Timestamp createTime;
	//
	private Integer updateBy;
	//
	private Timestamp updateTime;
	//
	private String descUrl;
	//
	private String status;
	//
	private String title;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
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
	public String getDescUrl() {
		return descUrl;
	}
	public String getStatus() {
		return status;
	}
	public String getTitle() {
		return title;
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
	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	
}
