package com.framework.vo;

import java.io.Serializable;

/**
 * @author ChenDang
 * @date 2020/1/19 0019
 */
public class MarketPriceVo implements Serializable {

    private String brandSeries;
    //
    private String size;
    //
    private String price;

    private String whendate;

    private String city;

    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBrandSeries() {
        return brandSeries;
    }

    public void setBrandSeries(String brandSeries) {
        this.brandSeries = brandSeries;
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

    public String getWhendate() {
        return whendate;
    }

    public void setWhendate(String whendate) {
        this.whendate = whendate;
    }
}
