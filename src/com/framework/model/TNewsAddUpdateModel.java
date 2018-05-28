package com.framework.model;

import java.io.Serializable;

public class TNewsAddUpdateModel implements Serializable{
	
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
	private String content;
	//
	private Integer topFlg;
	public Integer getId() {
		return id;
	}
	public String getNewsLogo() {
		return newsLogo;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public String getNewsTypeCd() {
		return newsTypeCd;
	}
	public Integer getHotFlg() {
		return hotFlg;
	}
	public String getContent() {
		return content;
	}
	public Integer getTopFlg() {
		return topFlg;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNewsLogo(String newsLogo) {
		this.newsLogo = newsLogo;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public void setNewsTypeCd(String newsTypeCd) {
		this.newsTypeCd = newsTypeCd;
	}
	public void setHotFlg(Integer hotFlg) {
		this.hotFlg = hotFlg;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setTopFlg(Integer topFlg) {
		this.topFlg = topFlg;
	}
	
	

}
