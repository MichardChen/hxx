package com.framework.model;

import java.io.Serializable;

public class CarouselDetailModel implements Serializable{

	private Integer id;
	//
	private String imgUrl;
	//
	private String realUrl;
	//
	private String mark;
	//
	private String typeCd;
	public Integer getId() {
		return id;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public String getRealUrl() {
		return realUrl;
	}
	public String getMark() {
		return mark;
	}
	public String getTypeCd() {
		return typeCd;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public void setRealUrl(String realUrl) {
		this.realUrl = realUrl;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}
	
	
}
