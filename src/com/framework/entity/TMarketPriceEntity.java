package com.framework.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-17 17:10:04
 */
public class TMarketPriceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String brandSeries;
	//
	private String size;
	//
	private String price;
	//
	private Date createTime;
	//
	private Integer createBy;
	//
	private Date updateTime;
	//
	private Integer updateBy;

	private String whendate;

	private int flg;

	public int getFlg() {
		return flg;
	}

	public void setFlg(int flg) {
		this.flg = flg;
	}

	public String getWhendate() {
        return whendate;
    }

    public void setWhendate(String whendate) {
        this.whendate = whendate;
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
	public void setBrandSeries(String brandSeries) {
		this.brandSeries = brandSeries;
	}
	/**
	 * 获取：
	 */
	public String getBrandSeries() {
		return brandSeries;
	}
	/**
	 * 设置：
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * 获取：
	 */
	public String getSize() {
		return size;
	}
	/**
	 * 设置：
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * 获取：
	 */
	public String getPrice() {
		return price;
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
}
