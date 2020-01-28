package com.framework.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author ChenDang
 * @date 2020/1/16 0016
 */
public class BuyDetailVo implements Serializable {

    private int id;
    private String title;
    private String img;
    private List<String> labels;
    private String size;
    private String price;
    private String city;
    private String createDate;
    private String mark;
    private String kfQQ;
    private String kfMobile;
    private List<CarouselVo> imgs;
    private String latestTime;
    private String weight;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getKfQQ() {
        return kfQQ;
    }

    public void setKfQQ(String kfQQ) {
        this.kfQQ = kfQQ;
    }

    public String getKfMobile() {
        return kfMobile;
    }

    public void setKfMobile(String kfMobile) {
        this.kfMobile = kfMobile;
    }

    public List<CarouselVo> getImgs() {
        return imgs;
    }

    public void setImgs(List<CarouselVo> imgs) {
        this.imgs = imgs;
    }

    public String getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
