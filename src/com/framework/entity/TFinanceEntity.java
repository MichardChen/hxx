package com.framework.entity;

import java.io.Serializable;
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
	private Date createTime;
	//
	private Integer updateBy;
	//
	private Date updateTime;
	//
	private String descUrl;
	//
	private String status;
	//
	private String title;

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
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * 获取：
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setLowRate(String lowRate) {
		this.lowRate = lowRate;
	}
	/**
	 * 获取：
	 */
	public String getLowRate() {
		return lowRate;
	}
	/**
	 * 设置：
	 */
	public void setLowRefund(String lowRefund) {
		this.lowRefund = lowRefund;
	}
	/**
	 * 获取：
	 */
	public String getLowRefund() {
		return lowRefund;
	}
	/**
	 * 设置：
	 */
	public void setTimeDistance(String timeDistance) {
		this.timeDistance = timeDistance;
	}
	/**
	 * 获取：
	 */
	public String getTimeDistance() {
		return timeDistance;
	}
	/**
	 * 设置：
	 */
	public void setStandard(String standard) {
		this.standard = standard;
	}
	/**
	 * 获取：
	 */
	public String getStandard() {
		return standard;
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
	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}
	/**
	 * 获取：
	 */
	public String getDescUrl() {
		return descUrl;
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
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
	}
}
