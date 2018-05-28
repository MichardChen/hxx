package com.framework.model;

import java.io.Serializable;

public class NewsListModel implements Serializable{

	//
	private Integer id;
	//
	private String newsTitle;
	//
	private String newsTypeCd;
	//
	private String hotFlg;
	//
	private String flg;
	//
	private String contentUrl;
	//
	private String topFlg;
	
	private String createBy;
	private String createTime;
	private String updateBy;
	private String updateTime;
	
	
	
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getHotFlg() {
		return hotFlg;
	}
	public void setHotFlg(String hotFlg) {
		this.hotFlg = hotFlg;
	}
	public String getFlg() {
		return flg;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	public String getTopFlg() {
		return topFlg;
	}
	public void setTopFlg(String topFlg) {
		this.topFlg = topFlg;
	}
	
	
	
	
}
