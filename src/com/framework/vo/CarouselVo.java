package com.framework.vo;

import java.io.Serializable;

/**
 * @author ChenDang
 * @date 2019/12/17 0017
 */
public class CarouselVo implements Serializable {

    private String imgUrl;
    private String linkUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
