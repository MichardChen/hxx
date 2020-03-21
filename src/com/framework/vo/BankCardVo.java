package com.framework.vo;

import java.io.Serializable;

public class BankCardVo implements Serializable {

    private String bankNo;
    //
    private String bankOpen;
    //
    private String bank;
    //
    private String bankOwner;
    private String mark;

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankOpen() {
        return bankOpen;
    }

    public void setBankOpen(String bankOpen) {
        this.bankOpen = bankOpen;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankOwner() {
        return bankOwner;
    }

    public void setBankOwner(String bankOwner) {
        this.bankOwner = bankOwner;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
