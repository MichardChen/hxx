package com.framework.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ChenDang
 * @date 2019/12/23 0023
 */
@Data
@Setter
@Getter
public class MallProductVo implements Serializable{

    private int productId;
    private String productLogo;
    private int points;
    private String productName;
}
