package com.framework.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 22:46:34
 */
public class TCarouselEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String imgUrl;
	//
	private String realUrl;
	//
	private String mark;
	//
	private Date createTime;
	//
	private Date updateTime;
	//
	private Integer flg;
	//
	private Integer createBy;
	//
	private Integer updateBy;
	//
	private String typeCd;

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
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * 获取：
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * 设置：
	 */
	public void setRealUrl(String realUrl) {
		this.realUrl = realUrl;
	}
	/**
	 * 获取：
	 */
	public String getRealUrl() {
		return realUrl;
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
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}
	/**
	 * 获取：
	 */
	public String getTypeCd() {
		return typeCd;
	}
}
