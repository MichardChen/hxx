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
	private Integer createBy;
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
	private Integer updateBy;
	//
	private Integer topFlg;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNewsLogo() {
		return newsLogo;
	}
	public void setNewsLogo(String newsLogo) {
		this.newsLogo = newsLogo;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsTypeCd() {
		return newsTypeCd;
	}
	public void setNewsTypeCd(String newsTypeCd) {
		this.newsTypeCd = newsTypeCd;
	}
	public Integer getHotFlg() {
		return hotFlg;
	}
	public void setHotFlg(Integer hotFlg) {
		this.hotFlg = hotFlg;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getFlg() {
		return flg;
	}
	public void setFlg(Integer flg) {
		this.flg = flg;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public Integer getTopFlg() {
		return topFlg;
	}
	public void setTopFlg(Integer topFlg) {
		this.topFlg = topFlg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
