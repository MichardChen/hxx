package com.framework.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-05 12:40:44
 */
public class TFishBuyEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String orderNo;
	//
	private String productType;
	//
	private String mainType;
	//
	private String size;
	//
	private String unit;
	//
	private String price;
	//
	private String weight;
	//
	private Date createTime;
	//
	private Date updateTime;
	//
	private String provice;
	//
	private String city;
	//
	private String latestTime;
	//
	private String mark;
	//
	private String img;
	//
	private Integer memberId;

	private String status;

	private String updateMark;

	private int updateBy;

	private String labels;

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public String getUpdateMark() {
		return updateMark;
	}

	public void setUpdateMark(String updateMark) {
		this.updateMark = updateMark;
	}

	public int getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getMainType() {
		return mainType;
	}

	public void setMainType(String mainType) {
		this.mainType = mainType;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getProvice() {
		return provice;
	}

	public void setProvice(String provice) {
		this.provice = provice;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLatestTime() {
		return latestTime;
	}

	public void setLatestTime(String latestTime) {
		this.latestTime = latestTime;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "TFishBuyEntity{" +
				"id=" + id +
				", orderNo='" + orderNo + '\'' +
				", productType='" + productType + '\'' +
				", mainType='" + mainType + '\'' +
				", size='" + size + '\'' +
				", unit='" + unit + '\'' +
				", price='" + price + '\'' +
				", weight='" + weight + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", provice='" + provice + '\'' +
				", city='" + city + '\'' +
				", latestTime='" + latestTime + '\'' +
				", mark='" + mark + '\'' +
				", img='" + img + '\'' +
				", memberId=" + memberId +
				", status='" + status + '\'' +
				", updateMark='" + updateMark + '\'' +
				", updateBy=" + updateBy +
				", labels='" + labels + '\'' +
				'}';
	}
}
