package com.framework.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 22:14:11
 */
public class TQuestionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String mobile;
	//
	private String linkMan;
	//
	private String question;
	//
	private Integer cartId;
	//
	private Integer employeeId;
	//
	private String status;
	//
	private Integer createBy;
	//
	private Date createTime;
	//
	private Integer updateBy;
	//
	private Date updateTime;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：
	 */
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	/**
	 * 获取：
	 */
	public String getLinkMan() {
		return linkMan;
	}
	/**
	 * 设置：
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * 获取：
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * 设置：
	 */
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	/**
	 * 获取：
	 */
	public Integer getCartId() {
		return cartId;
	}
	/**
	 * 设置：
	 */
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * 获取：
	 */
	public Integer getEmployeeId() {
		return employeeId;
	}
	/**
	 * 设置：
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：
	 */
	public Integer getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：
	 */
	public Integer getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
