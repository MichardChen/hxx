package com.framework.restful;

import java.io.Serializable;

/**
 * @author ChenDang
 * @date 2020/1/8 0008
 */
public class BrandSeriesVo implements Serializable {

    private int brandId;
    private int seriesId;
    private String brandName;
    private String seriesName;

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }
}
