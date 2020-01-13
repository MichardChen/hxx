package com.framework.model;

import java.io.Serializable;

public class TInsuranceModel implements Serializable{

    private Integer id;
    //
    private String icon;
    //
    private String name;
    //
    private String lowRate;
    //
    private String lowRefund;
    //
    private String timeDistance;
    //
    private String standard;

    private String status;

    private String content;

    private String typeCd;

    private String labels;

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getTypeCd() {
        return typeCd;
    }

    public void setTypeCd(String typeCd) {
        this.typeCd = typeCd;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    //
    public Integer getId() {
        return id;
    }
    public String getIcon() {
        return icon;
    }
    public String getName() {
        return name;
    }
    public String getLowRate() {
        return lowRate;
    }
    public String getLowRefund() {
        return lowRefund;
    }
    public String getTimeDistance() {
        return timeDistance;
    }
    public String getStandard() {
        return standard;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLowRate(String lowRate) {
        this.lowRate = lowRate;
    }
    public void setLowRefund(String lowRefund) {
        this.lowRefund = lowRefund;
    }
    public void setTimeDistance(String timeDistance) {
        this.timeDistance = timeDistance;
    }
    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
