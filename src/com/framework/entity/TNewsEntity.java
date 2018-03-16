package com.framework.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-03-16 17:13:03
 */
public class TNewsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String newsLogo;
	//
	private String newsTitle;
	//
	private String newsTypeCd;
	//
	private Integer hotFlg;
	//
	private Integer createUser;
	//
	private Date createTime;
	//
	private Date updateTime;
	//
	private Integer flg;
	//
	private String content;
	//
	private String contentUrl;
	//
	private Integer updateUserId;
	//
	private Integer topFlg;

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
	public void setNewsLogo(String newsLogo) {
		this.newsLogo = newsLogo;
	}
	/**
	 * 获取：
	 */
	public String getNewsLogo() {
		return newsLogo;
	}
	/**
	 * 设置：
	 */
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	/**
	 * 获取：
	 */
	public String getNewsTitle() {
		return newsTitle;
	}
	/**
	 * 设置：
	 */
	public void setNewsTypeCd(String newsTypeCd) {
		this.newsTypeCd = newsTypeCd;
	}
	/**
	 * 获取：
	 */
	public String getNewsTypeCd() {
		return newsTypeCd;
	}
	/**
	 * 设置：
	 */
	public void setHotFlg(Integer hotFlg) {
		this.hotFlg = hotFlg;
	}
	/**
	 * 获取：
	 */
	public Integer getHotFlg() {
		return hotFlg;
	}
	/**
	 * 设置：
	 */
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：
	 */
	public Integer getCreateUser() {
		return createUser;
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
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}
	/**
	 * 获取：
	 */
	public Integer getUpdateUserId() {
		return updateUserId;
	}
	/**
	 * 设置：
	 */
	public void setTopFlg(Integer topFlg) {
		this.topFlg = topFlg;
	}
	/**
	 * 获取：
	 */
	public Integer getTopFlg() {
		return topFlg;
	}
}
