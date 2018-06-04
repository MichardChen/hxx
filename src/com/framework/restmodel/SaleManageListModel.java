package com.framework.restmodel;

import java.io.Serializable;

public class SaleManageListModel implements Serializable{
	
	private long id;
	private String icon;
	private String introduce;
	private String skill;
	private String name;
	private String mobile;
	private int expertFlg;
	public long getId() {
		return id;
	}
	public String getIcon() {
		return icon;
	}
	public String getIntroduce() {
		return introduce;
	}
	public String getSkill() {
		return skill;
	}
	public String getName() {
		return name;
	}
	public String getMobile() {
		return mobile;
	}
	public int getExpertFlg() {
		return expertFlg;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setExpertFlg(int expertFlg) {
		this.expertFlg = expertFlg;
	}
	
	
}
