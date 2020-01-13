package com.framework.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 *
 *
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-05 12:40:44
 */
public class TFishSupplyVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //
    private String orderNo;
    //
    private String productTypeCd;
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
    private String member;

    private String status;
    private String updateMark;
    private String labels;
    private List<String> imgs;

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdateMark() {
        return updateMark;
    }

    public void setUpdateMark(String updateMark) {
        this.updateMark = updateMark;
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

    public String getProductTypeCd() {
        return productTypeCd;
    }

    public void setProductTypeCd(String productTypeCd) {
        this.productTypeCd = productTypeCd;
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

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
}
