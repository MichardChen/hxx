package com.framework.model;

import java.io.Serializable;

public class StoryListModel implements Serializable{
	
	private int id;
	private String storyIcon;
	private String storyTitle;
	private String createTime;
	private String updateTime;
	private String createBy;
	private String updateBy;
	private String descUrl;
	private String flg;
	public int getId() {
		return id;
	}
	public String getStoryIcon() {
		return storyIcon;
	}
	public String getStoryTitle() {
		return storyTitle;
	}
	public String getCreateTime() {
		return createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public String getDescUrl() {
		return descUrl;
	}
	public String getFlg() {
		return flg;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setStoryIcon(String storyIcon) {
		this.storyIcon = storyIcon;
	}
	public void setStoryTitle(String storyTitle) {
		this.storyTitle = storyTitle;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	
	
	

}
