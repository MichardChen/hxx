package com.framework.model;

import java.io.Serializable;
import java.util.Date;

public class DocumentListModel implements Serializable{
	
	private Integer id;
	//
	private String title;
	//
	private String typeCd;
	//
	private String createTime;
	//
	private String updateTime;
	//
	private String flg;
	//
	private String descUrl;
	//
	private String createBy;
	//
	private String updateBy;
	public Integer getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getTypeCd() {
		return typeCd;
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
	public String getDescUrl() {
		return descUrl;
	}
	public String getCreateBy() {
		return createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
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
	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	

}
