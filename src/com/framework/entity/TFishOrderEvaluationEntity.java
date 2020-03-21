package com.framework.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author R & D
 * @email adang369@126.com
 * @date 2020-03-15 21:28:02
 */
public class TFishOrderEvaluationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String orderNo;
	//
	private String orderTypeCd;
	//
	private String evaluation;
	//
	private String points;
	//
	private Date createTime;
	//
	private Integer createBy;
	//
	private Date updateTime;
	//
	private Integer updateBy;
	//
	private Integer flg;

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
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：
	 */
	public void setOrderTypeCd(String orderTypeCd) {
		this.orderTypeCd = orderTypeCd;
	}
	/**
	 * 获取：
	 */
	public String getOrderTypeCd() {
		return orderTypeCd;
	}
	/**
	 * 设置：
	 */
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	/**
	 * 获取：
	 */
	public String getEvaluation() {
		return evaluation;
	}
	/**
	 * 设置：
	 */
	public void setPoints(String points) {
		this.points = points;
	}
	/**
	 * 获取：
	 */
	public String getPoints() {
		return points;
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
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
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
	public void setFlg(Integer flg) {
		this.flg = flg;
	}
	/**
	 * 获取：
	 */
	public Integer getFlg() {
		return flg;
	}
}
