package com.framework.model;

import java.io.Serializable;

public class CarouselListModel implements Serializable{
	
	private Integer id;
	//
	private String imgUrl;
	//
	private String realUrl;
	//
	private String mark;
	//
	private String createTime;
	//
	private String updateTime;
	//
	private String flg;
	//
	private String createBy;
	//
	private String updateBy;
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
	public String getCreateTime() {
		return createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public String getFlg() {
		return flg;
	}
	public String getCreateBy() {
		return createBy;
	}
	public String getUpdateBy() {
		return updateBy;
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
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}

	
	
}
