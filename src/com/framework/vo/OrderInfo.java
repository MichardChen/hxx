package com.framework.vo;

import java.io.Serializable;

/**
 * @author ChenDang
 * @date 2020/1/29 0029
 */
public class OrderInfo implements Serializable {

    private String status;
    private String statusCd;
    private String failReason;
    private String firstPay;
    private String secondPay;
    private String allPay;
    private String payTime;

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstPay() {
        return firstPay;
    }

    public void setFirstPay(String firstPay) {
        this.firstPay = firstPay;
    }

    public String getSecondPay() {
        return secondPay;
    }

    public void setSecondPay(String secondPay) {
        this.secondPay = secondPay;
    }

    public String getAllPay() {
        return allPay;
    }

    public void setAllPay(String allPay) {
        this.allPay = allPay;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
}
