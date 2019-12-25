package com.framework.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ChenDang
 * @date 2019/12/24 0024
 */
@Data
@Setter
@Getter
public class MallPointsExchangeRecordVo implements Serializable {

    private int exchangeId;
    private String title;
    private int points;
    private String createTime;
    private String mark;
    private String address;
    private String status;
    private String logistic;
    private int quality;
    private String moneys;
    private String receivePerson;
    private String receiveMobile;
    private String expressRelateUrl;
    private String exchageTime;

}
