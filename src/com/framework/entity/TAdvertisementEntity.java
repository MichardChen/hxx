package com.framework.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-16 10:46:39
 */
public class TAdvertisementEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String typeCd;
	//
	private String title;
	//
	private String logo;
	//
	private String content;
	//
	private String contentUrl;
	//
	private String realUrl;
	//
	private Date createTime;
	//
	private Date updateTime;
	//
	private Integer createBy;
	//
	private Integer updateBy;
	//
	private String mark;
	//
	private Integer flg;

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
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}
	/**
	 * 获取：
	 */
	public String getTypeCd() {
		return typeCd;
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
	/**
	 * 设置：
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	/**
	 * 获取：
	 */
	public String getLogo() {
		return logo;
	}
	/**
	 * 设置：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：
	 */
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	/**
	 * 获取：
	 */
	public String getContentUrl() {
		return contentUrl;
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
	public void setFlg(Integer flg) {
		this.flg = flg;
	}
	/**
	 * 获取：
	 */
	public Integer getFlg() {
		return flg;
	}
}
