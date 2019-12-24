package com.framework.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ChenDang
 * @date 2019/12/17 0017
 */
@Data
@Setter
@Getter
public class NewsListVo implements Serializable {

    private String newsLogo;
    private String newsTitle;
    private String createTime;
    private String url;

    public String getNewsLogo() {
        return newsLogo;
    }

    public void setNewsLogo(String newsLogo) {
        this.newsLogo = newsLogo;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
