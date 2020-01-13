package com.framework.vo;

import java.io.Serializable;

/**
 * @author ChenDang
 * @date 2020/1/8 0008
 */
public class ProvinceCityVo implements Serializable {

    private int provinceId;
    private int cityId;
    private String provinceName;
    private String cityName;

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
