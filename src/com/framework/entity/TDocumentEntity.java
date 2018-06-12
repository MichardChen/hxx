package com.framework.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-06-12 17:37:37
 */
public class TDocumentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String title;
	//
	private String content;
	//
	private String typeCd;
	//
	private Timestamp createTime;
	//
	private Timestamp updateTime;
	//
	private Integer flg;
	//
	private String descUrl;
	//
	private Integer createBy;
	//
	private Integer updateBy;
	public Integer getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getTypeCd() {
		return typeCd;
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
	public String getDescUrl() {
		return descUrl;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
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
	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	
}
