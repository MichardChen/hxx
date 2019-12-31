package com.framework.vo;

import java.io.Serializable;

/**
 * @author ChenDang
 * @date 2019/12/24 0024
 */
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

    public int getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(int exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogistic() {
        return logistic;
    }

    public void setLogistic(String logistic) {
        this.logistic = logistic;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getMoneys() {
        return moneys;
    }

    public void setMoneys(String moneys) {
        this.moneys = moneys;
    }

    public String getReceivePerson() {
        return receivePerson;
    }

    public void setReceivePerson(String receivePerson) {
        this.receivePerson = receivePerson;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public String getExpressRelateUrl() {
        return expressRelateUrl;
    }

    public void setExpressRelateUrl(String expressRelateUrl) {
        this.expressRelateUrl = expressRelateUrl;
    }

    public String getExchageTime() {
        return exchageTime;
    }

    public void setExchageTime(String exchageTime) {
        this.exchageTime = exchageTime;
    }
}
