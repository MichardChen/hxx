package com.framework.model;

import java.io.Serializable;
import java.util.Date;

public class FinanceCommitListModel implements Serializable{
	
	private Integer id;
	//
	private String financeId;
	//
	private String brandId;
	//
	private String brandSeriesId;
	//
	private String provinceId;
	//
	private String cityId;
	//
	private String name;
	//
	private String age;
	//
	private String idcardNo;
	//
	private String sex;
	//
	private String mobile;
	//
	private String mark;
	//
	private String updateBy;
	//
	private String updateTime;
	
	private String createTime;
	//
	private String status;
	
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getId() {
		return id;
	}
	public String getFinanceId() {
		return financeId;
	}
	public String getBrandId() {
		return brandId;
	}
	public String getBrandSeriesId() {
		return brandSeriesId;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public String getName() {
		return name;
	}
	public String getAge() {
		return age;
	}
	public String getIdcardNo() {
		return idcardNo;
	}
	public String getSex() {
		return sex;
	}
	public String getMobile() {
		return mobile;
	}
	public String getMark() {
		return mark;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public void setBrandSeriesId(String brandSeriesId) {
		this.brandSeriesId = brandSeriesId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
