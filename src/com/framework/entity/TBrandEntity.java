package com.framework.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 23:00:02
 */
public class TBrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String brand;
	//
	private String brandIcon;
	//
	private Integer createBy;
	//
	private Integer updateBy;
	//
	private Date createTime;
	//
	private Date updateTime;

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
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * 获取：
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * 设置：
	 */
	public void setBrandIcon(String brandIcon) {
		this.brandIcon = brandIcon;
	}
	/**
	 * 获取：
	 */
	public String getBrandIcon() {
		return brandIcon;
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
}
