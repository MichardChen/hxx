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


}
