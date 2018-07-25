package com.framework.pcmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class SecondhandCarPCDetailModel implements Serializable{

	private int cartId;
	private List<String> icons;
	private String carName;
	private String carSeriesName;
	private String containTaxPrice;
	private String year;
	private String titleLabel;
	private String kilomiter;
	private String city;
	private String firstPayment;
	private String monthPayment;
	private String labels;
	private String cheshenjiegou;
	private String fadongjixinghao;
	private String qudongtype;
	private String height;
	private String length;
	private String width;
	private String biansuxiangtype;
	private String ranliaotype;
	private String zhuchezhidongtype;
	private String buyMarkUrl;
	private String buyKnowUrl;
	private String descUrl;
	private String companyMobile;
	private String periods;
	private String gongyoutype;
	private String pailiang;
	
	private int flg1;
	private int flg2;
	private int flg3;
	
	//新增字段
	//36期mark3
	private String mark3;
	//48期
	private String firstPayment1;
	//48期月供
	private String monthPayment1;
	//48期期数
	private String periods1;
	//48期备注
	private String mark1;
	//1+3首年首付
	private String tfirstYearFirstPay;
	//1+3首年月供
	private String tfirstYearMonthPayment;
	//1+3，一年后分期数
	private String tperiods;
	//1+3，一年后分期月供
	private String tmonthPayment;
	//
	private String realFirstPayment;
	
	private String finalPayment;
	//
	private String serviceFee;
	
	private String buyPay;
	
	
	
	
	public String getBuyPay() {
		return buyPay;
	}
	public void setBuyPay(String buyPay) {
		this.buyPay = buyPay;
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
	public String getContainTaxPrice() {
		return containTaxPrice;
	}
	public String getYear() {
		return year;
	}
	public String getTitleLabel() {
		return titleLabel;
	}
	public String getKilomiter() {
		return kilomiter;
	}
	public String getCity() {
		return city;
	}
	public String getFirstPayment() {
		return firstPayment;
	}
	public String getMonthPayment() {
		return monthPayment;
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
	public String getPeriods() {
		return periods;
	}
	public String getGongyoutype() {
		return gongyoutype;
	}
	public String getPailiang() {
		return pailiang;
	}
	public int getFlg1() {
		return flg1;
	}
	public int getFlg2() {
		return flg2;
	}
	public int getFlg3() {
		return flg3;
	}
	public String getMark3() {
		return mark3;
	}
	public String getFirstPayment1() {
		return firstPayment1;
	}
	public String getMonthPayment1() {
		return monthPayment1;
	}
	public String getPeriods1() {
		return periods1;
	}
	public String getMark1() {
		return mark1;
	}
	public String getTfirstYearFirstPay() {
		return tfirstYearFirstPay;
	}
	public String getTfirstYearMonthPayment() {
		return tfirstYearMonthPayment;
	}
	public String getTperiods() {
		return tperiods;
	}
	public String getTmonthPayment() {
		return tmonthPayment;
	}
	public String getRealFirstPayment() {
		return realFirstPayment;
	}
	public String getServiceFee() {
		return serviceFee;
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
	public void setContainTaxPrice(String containTaxPrice) {
		this.containTaxPrice = containTaxPrice;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setTitleLabel(String titleLabel) {
		this.titleLabel = titleLabel;
	}
	public void setKilomiter(String kilomiter) {
		this.kilomiter = kilomiter;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setFirstPayment(String firstPayment) {
		this.firstPayment = firstPayment;
	}
	public void setMonthPayment(String monthPayment) {
		this.monthPayment = monthPayment;
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
	public void setPeriods(String periods) {
		this.periods = periods;
	}
	public void setGongyoutype(String gongyoutype) {
		this.gongyoutype = gongyoutype;
	}
	public void setPailiang(String pailiang) {
		this.pailiang = pailiang;
	}
	public void setFlg1(int flg1) {
		this.flg1 = flg1;
	}
	public void setFlg2(int flg2) {
		this.flg2 = flg2;
	}
	public void setFlg3(int flg3) {
		this.flg3 = flg3;
	}
	public void setMark3(String mark3) {
		this.mark3 = mark3;
	}
	public void setFirstPayment1(String firstPayment1) {
		this.firstPayment1 = firstPayment1;
	}
	public void setMonthPayment1(String monthPayment1) {
		this.monthPayment1 = monthPayment1;
	}
	public void setPeriods1(String periods1) {
		this.periods1 = periods1;
	}
	public void setMark1(String mark1) {
		this.mark1 = mark1;
	}
	public void setTfirstYearFirstPay(String tfirstYearFirstPay) {
		this.tfirstYearFirstPay = tfirstYearFirstPay;
	}
	public void setTfirstYearMonthPayment(String tfirstYearMonthPayment) {
		this.tfirstYearMonthPayment = tfirstYearMonthPayment;
	}
	public void setTperiods(String tperiods) {
		this.tperiods = tperiods;
	}
	public void setTmonthPayment(String tmonthPayment) {
		this.tmonthPayment = tmonthPayment;
	}
	public void setRealFirstPayment(String realFirstPayment) {
		this.realFirstPayment = realFirstPayment;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	
	
	
	
	
}

