package com.framework.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 22:28:57
 */
public class TFinanceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//产品图标
	private String icon;
	//产品名称
	private String name;
	//最低费率
	private String lowRate;
	//最低贷款金额
	private String lowRefund;
	//期限
	private String timeDistance;
	//标准
	private String standard;
	//创建者
	private Integer createBy;
	//创建时间
	private Timestamp createTime;
	//更新者
	private Integer updateBy;
	//更新时间
	private Timestamp updateTime;
	//产品详情
	private String content;
	//产品详情url
	private String descUrl;
	//产品状态
	private String status;
	//产品标题
	private String title;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public String getIcon() {
		return icon;
	}
	public String getName() {
		return name;
	}
	public String getLowRate() {
		return lowRate;
	}
	public String getLowRefund() {
		return lowRefund;
	}
	public String getTimeDistance() {
		return timeDistance;
	}
	public String getStandard() {
		return standard;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public String getDescUrl() {
		return descUrl;
	}
	public String getStatus() {
		return status;
	}
	public String getTitle() {
		return title;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLowRate(String lowRate) {
		this.lowRate = lowRate;
	}
	public void setLowRefund(String lowRefund) {
		this.lowRefund = lowRefund;
	}
	public void setTimeDistance(String timeDistance) {
		this.timeDistance = timeDistance;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	
}
