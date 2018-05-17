package com.framework.entity;

import java.io.Serializable;
import java.sql.Timestamp;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 22:46:34
 */
public class TCarouselEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String imgUrl;
	//
	private String realUrl;
	//
	private String mark;
	//
	private Timestamp createTime;
	//
	private Timestamp updateTime;
	//
	private Integer flg;
	//
	private Integer createBy;
	//
	private Integer updateBy;
	//
	private String typeCd;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public String getRealUrl() {
		return realUrl;
	}
	public String getMark() {
		return mark;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public Integer getFlg() {
		return flg;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public String getTypeCd() {
		return typeCd;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public void setRealUrl(String realUrl) {
		this.realUrl = realUrl;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public void setFlg(Integer flg) {
		this.flg = flg;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}

	
}
