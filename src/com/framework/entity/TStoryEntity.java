package com.framework.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-14 11:40:45
 */
public class TStoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//车主故事
	private Integer id;
	//
	private String storyIcon;
	//
	private String storyTitle;
	//
	private Timestamp createTime;
	//
	private String descUrl;
	//
	private Integer createBy;
	//
	private Integer updateBy;
	//车主故事
	private Timestamp updateTime;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public String getStoryIcon() {
		return storyIcon;
	}
	public String getStoryTitle() {
		return storyTitle;
	}
	public Timestamp getCreateTime() {
		return createTime;
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
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setStoryIcon(String storyIcon) {
		this.storyIcon = storyIcon;
	}
	public void setStoryTitle(String storyTitle) {
		this.storyTitle = storyTitle;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
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
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
