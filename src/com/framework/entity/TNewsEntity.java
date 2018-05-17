package com.framework.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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
	private Timestamp createTime;
	//
	private Timestamp updateTime;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
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
	public Integer getCreateBy() {
		return createBy;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public Integer getFlg() {
		return flg;
	}
	public String getContent() {
		return content;
	}
	public String getContentUrl() {
		return contentUrl;
	}
	public Integer getUpdateBy() {
		return updateBy;
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
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public void setFlg(Integer flg) {
		this.flg = flg;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public void setTopFlg(Integer topFlg) {
		this.topFlg = topFlg;
	}
	
	
}
