package com.framework.entity;

import java.io.Serializable;
import java.sql.Timestamp;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-30 10:21:33
 */
public class TVertifyCodeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String mobile;
	//
	private String userTypeCd;
	//
	private String code;
	//
	private Timestamp expireTime;
	//
	private Timestamp createTime;
	//
	private Timestamp updateTime;
	//
	private String codeTypeCd;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public String getMobile() {
		return mobile;
	}
	public String getUserTypeCd() {
		return userTypeCd;
	}
	public String getCode() {
		return code;
	}
	public Timestamp getExpireTime() {
		return expireTime;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public String getCodeTypeCd() {
		return codeTypeCd;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setUserTypeCd(String userTypeCd) {
		this.userTypeCd = userTypeCd;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setExpireTime(Timestamp expireTime) {
		this.expireTime = expireTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public void setCodeTypeCd(String codeTypeCd) {
		this.codeTypeCd = codeTypeCd;
	}

	
}
