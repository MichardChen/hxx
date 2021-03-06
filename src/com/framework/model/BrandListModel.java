package com.framework.model;

import java.io.Serializable;

public class BrandListModel implements Serializable{
	
	//
	private Integer id;
	//
	private String brand;
	//
	private String brandIcon;
	//
	private String createBy;
	//
	private String updateBy;
	//
	private String createTime;
	//
	private String updateTime;
	
	private String flg;
	
	private String showflg;
	
	private String importflg;
	
	public String getShowflg() {
		return showflg;
	}
	public String getImportflg() {
		return importflg;
	}
	public void setShowflg(String showflg) {
		this.showflg = showflg;
	}
	public void setImportflg(String importflg) {
		this.importflg = importflg;
	}
	public String getFlg() {
		return flg;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getBrandIcon() {
		return brandIcon;
	}
	public void setBrandIcon(String brandIcon) {
		this.brandIcon = brandIcon;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
