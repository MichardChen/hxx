package com.framework.vo;

import java.io.Serializable;

/**
 * @author ChenDang
 * @date 2019/12/30 0030
 */
public class FinanceVo implements Serializable {

    private int id;
    private String logo;
    private String financeName;
    private String rate;
    private String refund;
    private String timeDistance;

    public String getTimeDistance() {
        return timeDistance;
    }

    public void setTimeDistance(String timeDistance) {
        this.timeDistance = timeDistance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFinanceName() {
        return financeName;
    }

    public void setFinanceName(String financeName) {
        this.financeName = financeName;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRefund() {
        return refund;
    }

    public void setRefund(String refund) {
        this.refund = refund;
    }
}
