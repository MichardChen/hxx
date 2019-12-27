package com.framework.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class MallPointsRecord implements Serializable{

	private int id;
	//用户类型
	private String userTypeCd;
	//用户ID
	private int userId;
	//操作类型(注册积分、下单积分)
	private String operateTypeCd;
	//备注
	private String mark;
	//获得积分
	private String point;
	//创建时间
	private Timestamp createTime;
	//更新时间
	private Timestamp updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserTypeCd() {
		return userTypeCd;
	}

	public void setUserTypeCd(String userTypeCd) {
		this.userTypeCd = userTypeCd;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOperateTypeCd() {
		return operateTypeCd;
	}

	public void setOperateTypeCd(String operateTypeCd) {
		this.operateTypeCd = operateTypeCd;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
