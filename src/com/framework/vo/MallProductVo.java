package com.framework.vo;

import java.io.Serializable;

/**
 * @author ChenDang
 * @date 2019/12/23 0023
 */
public class MallProductVo implements Serializable{

    private int productId;
    private String productLogo;
    private int points;
    private String productName;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductLogo() {
        return productLogo;
    }

    public void setProductLogo(String productLogo) {
        this.productLogo = productLogo;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
