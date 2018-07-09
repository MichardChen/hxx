package com.framework.restful;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class LeaseCarDetailModel implements Serializable{
	
	private int cartId;
	private List<String> icons;
	private String carName;
	private String carSeriesName;
	private String firmPrice;
	private String carInfo;
	//48期首付和月供、备注、分期数
	private String firstPayment;
	private String monthPayment;
	private String mark;
	private Integer periods;
	//36期首付、月供、备注、分期数
	private String firstPayment1;
	private String monthPayment1;
	private String mark1;
	private Integer periods1;
	//1+3
	//1+3首年首付
	private String tfirstYearFirstPay;
	//1+3首年月供
	private String tfirstYearMonthPayment;
	//1+3，一年后分期数
	private Integer tperiods;
	//1+3，一年后分期月供
	private String tmonthPayment;
	//1+3尾款
	private String finalPayment;
	
	private String buyPay;
	private String serviceFee;
	
	private String labels;
	private String cheshenjiegou;
	private String fadongjixinghao;
	private String qudongtype;
	private String height;
	private String length;
	private String width;
	private String pailiang;
	private String biansuxiangtype;
	private String ranliaotype;
	private String zhuchezhidongtype;
	private String buyMarkUrl;
	private String buyKnowUrl;
	private String descUrl;
	private String companyMobile;
	private String salecount;
	private String gongyoutype;
	
	
	
	public String getGongyoutype() {
		return gongyoutype;
	}
	public void setGongyoutype(String gongyoutype) {
		this.gongyoutype = gongyoutype;
	}
	public String getPailiang() {
		return pailiang;
	}
	public void setPailiang(String pailiang) {
		this.pailiang = pailiang;
	}
	public String getFinalPayment() {
		return finalPayment;
	}
	public void setFinalPayment(String finalPayment) {
		this.finalPayment = finalPayment;
	}
	public int getCartId() {
		return cartId;
	}
	public List<String> getIcons() {
		return icons;
	}
	public String getCarName() {
		return carName;
	}
	public String getCarSeriesName() {
		return carSeriesName;
	}
	public String getFirmPrice() {
		return firmPrice;
	}
	public String getCarInfo() {
		return carInfo;
	}
	public String getFirstPayment() {
		return firstPayment;
	}
	public String getMonthPayment() {
		return monthPayment;
	}
	public String getMark() {
		return mark;
	}
	public Integer getPeriods() {
		return periods;
	}
	public String getFirstPayment1() {
		return firstPayment1;
	}
	public String getMonthPayment1() {
		return monthPayment1;
	}
	public String getMark1() {
		return mark1;
	}
	public Integer getPeriods1() {
		return periods1;
	}
	public String getTfirstYearFirstPay() {
		return tfirstYearFirstPay;
	}
	public String getTfirstYearMonthPayment() {
		return tfirstYearMonthPayment;
	}
	public Integer getTperiods() {
		return tperiods;
	}
	public String getTmonthPayment() {
		return tmonthPayment;
	}
	public String getBuyPay() {
		return buyPay;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public String getLabels() {
		return labels;
	}
	public String getCheshenjiegou() {
		return cheshenjiegou;
	}
	public String getFadongjixinghao() {
		return fadongjixinghao;
	}
	public String getQudongtype() {
		return qudongtype;
	}
	public String getHeight() {
		return height;
	}
	public String getLength() {
		return length;
	}
	public String getWidth() {
		return width;
	}
	public String getBiansuxiangtype() {
		return biansuxiangtype;
	}
	public String getRanliaotype() {
		return ranliaotype;
	}
	public String getZhuchezhidongtype() {
		return zhuchezhidongtype;
	}
	public String getBuyMarkUrl() {
		return buyMarkUrl;
	}
	public String getBuyKnowUrl() {
		return buyKnowUrl;
	}
	public String getDescUrl() {
		return descUrl;
	}
	public String getCompanyMobile() {
		return companyMobile;
	}
	public String getSalecount() {
		return salecount;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public void setIcons(List<String> icons) {
		this.icons = icons;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public void setCarSeriesName(String carSeriesName) {
		this.carSeriesName = carSeriesName;
	}
	public void setFirmPrice(String firmPrice) {
		this.firmPrice = firmPrice;
	}
	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
	}
	public void setFirstPayment(String firstPayment) {
		this.firstPayment = firstPayment;
	}
	public void setMonthPayment(String monthPayment) {
		this.monthPayment = monthPayment;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public void setPeriods(Integer periods) {
		this.periods = periods;
	}
	public void setFirstPayment1(String firstPayment1) {
		this.firstPayment1 = firstPayment1;
	}
	public void setMonthPayment1(String monthPayment1) {
		this.monthPayment1 = monthPayment1;
	}
	public void setMark1(String mark1) {
		this.mark1 = mark1;
	}
	public void setPeriods1(Integer periods1) {
		this.periods1 = periods1;
	}
	public void setTfirstYearFirstPay(String tfirstYearFirstPay) {
		this.tfirstYearFirstPay = tfirstYearFirstPay;
	}
	public void setTfirstYearMonthPayment(String tfirstYearMonthPayment) {
		this.tfirstYearMonthPayment = tfirstYearMonthPayment;
	}
	public void setTperiods(Integer tperiods) {
		this.tperiods = tperiods;
	}
	public void setTmonthPayment(String tmonthPayment) {
		this.tmonthPayment = tmonthPayment;
	}
	public void setBuyPay(String buyPay) {
		this.buyPay = buyPay;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	public void setLabels(String labels) {
		this.labels = labels;
	}
	public void setCheshenjiegou(String cheshenjiegou) {
		this.cheshenjiegou = cheshenjiegou;
	}
	public void setFadongjixinghao(String fadongjixinghao) {
		this.fadongjixinghao = fadongjixinghao;
	}
	public void setQudongtype(String qudongtype) {
		this.qudongtype = qudongtype;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public void setBiansuxiangtype(String biansuxiangtype) {
		this.biansuxiangtype = biansuxiangtype;
	}
	public void setRanliaotype(String ranliaotype) {
		this.ranliaotype = ranliaotype;
	}
	public void setZhuchezhidongtype(String zhuchezhidongtype) {
		this.zhuchezhidongtype = zhuchezhidongtype;
	}
	public void setBuyMarkUrl(String buyMarkUrl) {
		this.buyMarkUrl = buyMarkUrl;
	}
	public void setBuyKnowUrl(String buyKnowUrl) {
		this.buyKnowUrl = buyKnowUrl;
	}
	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}
	public void setCompanyMobile(String companyMobile) {
		this.companyMobile = companyMobile;
	}
	public void setSalecount(String salecount) {
		this.salecount = salecount;
	}
	
	
}
