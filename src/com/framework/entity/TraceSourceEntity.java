package com.framework.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-09 20:42:50
 */
public class TraceSourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	//订单号,对应t_fish_buy和t_fish_supply的order_no字段
	private String orderNo;
	//270001:求购订单表，270002:供应订单表,270003:自行添加的数据,与订单无关
	private String orderTypeCd;
	//富文本标签内容
	private String content;
	//生成的网页url
	private String contentUrl;
	//
	private Date createTime;
	//
	private Date updateTime;
	//创建人id
	private int createBy;
	//修改人id
	private int updateBy;

	public int getCreateBy() {
		return createBy;
	}

	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	public int getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

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
	 * 设置：订单号,对应t_fish_buy和t_fish_supply的order_no字段
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：订单号,对应t_fish_buy和t_fish_supply的order_no字段
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：270001:求购订单表，270002:供应订单表,270003:自行添加的数据,与订单无关
	 */
	public void setOrderTypeCd(String orderTypeCd) {
		this.orderTypeCd = orderTypeCd;
	}
	/**
	 * 获取：270001:求购订单表，270002:供应订单表,270003:自行添加的数据,与订单无关
	 */
	public String getOrderTypeCd() {
		return orderTypeCd;
	}
	/**
	 * 设置：富文本标签内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：富文本标签内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：生成的网页url
	 */
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	/**
	 * 获取：生成的网页url
	 */
	public String getContentUrl() {
		return contentUrl;
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
