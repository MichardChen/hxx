package com.framework.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class TCarSecondhandModel implements Serializable{

	private Integer id;
	//
	private String icon;
	//
	private String titleLabel;
	//
	private Integer brand;
	//
	private String carName;
	//
	private Integer provinceId;
	//
	private Integer cityId;
	//
	private BigDecimal kilomiters;
	//
	private String year;
	//
	private BigDecimal firstPayment;
	//
	private BigDecimal monthPayment;
	//
	private BigDecimal age;
	//
	private String carLevelCd;
	//
	private String carColor;
	//
	private BigDecimal carCost;
	//
	private Integer carSeriesId;
	//
	private BigDecimal carTaxCost;
	//
	private Integer periods;
	//
	private String labels;
	//
	private BigDecimal finalPayment;
	//
	private String content;
	
	private String pcIcon;
	
	private Integer flg;
	
	//48期mark3
		private String mark3;
		//48期
		private BigDecimal firstPayment1;
		//48期月供
		private BigDecimal monthPayment1;
		//48期期数
		private Integer periods1;
		//48期备注
		private String mark1;
		//1+3首年首付
		private BigDecimal tfirstYearFirstPay;
		//1+3首年月供
		private BigDecimal tfirstYearMonthPayment;
		//1+3，一年后分期数
		private Integer tperiods;
		//1+3，一年后分期月供
		private BigDecimal tmonthPayment;
		//
		private BigDecimal realFirstPayment;
		//
		private BigDecimal serviceFee;
		////1+3，一年后尾款finalPayment
		//列表显示那个方案
		private int showFlg;
		
		

	public String getMark3() {
			return mark3;
		}

		public void setMark3(String mark3) {
			this.mark3 = mark3;
		}

		public BigDecimal getFirstPayment1() {
			return firstPayment1;
		}

		public void setFirstPayment1(BigDecimal firstPayment1) {
			this.firstPayment1 = firstPayment1;
		}

		public BigDecimal getMonthPayment1() {
			return monthPayment1;
		}

		public void setMonthPayment1(BigDecimal monthPayment1) {
			this.monthPayment1 = monthPayment1;
		}

		public Integer getPeriods1() {
			return periods1;
		}

		public void setPeriods1(Integer periods1) {
			this.periods1 = periods1;
		}

		public String getMark1() {
			return mark1;
		}

		public void setMark1(String mark1) {
			this.mark1 = mark1;
		}

		public BigDecimal getTfirstYearFirstPay() {
			return tfirstYearFirstPay;
		}

		public void setTfirstYearFirstPay(BigDecimal tfirstYearFirstPay) {
			this.tfirstYearFirstPay = tfirstYearFirstPay;
		}

		public BigDecimal getTfirstYearMonthPayment() {
			return tfirstYearMonthPayment;
		}

		public void setTfirstYearMonthPayment(BigDecimal tfirstYearMonthPayment) {
			this.tfirstYearMonthPayment = tfirstYearMonthPayment;
		}

		public Integer getTperiods() {
			return tperiods;
		}

		public void setTperiods(Integer tperiods) {
			this.tperiods = tperiods;
		}

		public BigDecimal getTmonthPayment() {
			return tmonthPayment;
		}

		public void setTmonthPayment(BigDecimal tmonthPayment) {
			this.tmonthPayment = tmonthPayment;
		}

		public BigDecimal getRealFirstPayment() {
			return realFirstPayment;
		}

		public void setRealFirstPayment(BigDecimal realFirstPayment) {
			this.realFirstPayment = realFirstPayment;
		}

		public BigDecimal getServiceFee() {
			return serviceFee;
		}

		public void setServiceFee(BigDecimal serviceFee) {
			this.serviceFee = serviceFee;
		}

		public int getShowFlg() {
			return showFlg;
		}

		public void setShowFlg(int showFlg) {
			this.showFlg = showFlg;
		}

	public Integer getFlg() {
		return flg;
	}

	public void setFlg(Integer flg) {
		this.flg = flg;
	}

	public Integer getId() {
		return id;
	}

	public String getIcon() {
		return icon;
	}

	public String getTitleLabel() {
		return titleLabel;
	}

	public Integer getBrand() {
		return brand;
	}

	public String getCarName() {
		return carName;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public BigDecimal getKilomiters() {
		return kilomiters;
	}

	public String getYear() {
		return year;
	}

	public BigDecimal getFirstPayment() {
		return firstPayment;
	}

	public BigDecimal getMonthPayment() {
		return monthPayment;
	}

	public BigDecimal getAge() {
		return age;
	}

	public String getCarLevelCd() {
		return carLevelCd;
	}

	public String getCarColor() {
		return carColor;
	}

	public BigDecimal getCarCost() {
		return carCost;
	}

	public Integer getCarSeriesId() {
		return carSeriesId;
	}

	public BigDecimal getCarTaxCost() {
		return carTaxCost;
	}

	public Integer getPeriods() {
		return periods;
	}

	public String getLabels() {
		return labels;
	}

	public BigDecimal getFinalPayment() {
		return finalPayment;
	}

	public String getContent() {
		return content;
	}

	public String getPcIcon() {
		return pcIcon;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setTitleLabel(String titleLabel) {
		this.titleLabel = titleLabel;
	}

	public void setBrand(Integer brand) {
		this.brand = brand;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public void setKilomiters(BigDecimal kilomiters) {
		this.kilomiters = kilomiters;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setFirstPayment(BigDecimal firstPayment) {
		this.firstPayment = firstPayment;
	}

	public void setMonthPayment(BigDecimal monthPayment) {
		this.monthPayment = monthPayment;
	}

	public void setAge(BigDecimal age) {
		this.age = age;
	}

	public void setCarLevelCd(String carLevelCd) {
		this.carLevelCd = carLevelCd;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public void setCarCost(BigDecimal carCost) {
		this.carCost = carCost;
	}

	public void setCarSeriesId(Integer carSeriesId) {
		this.carSeriesId = carSeriesId;
	}

	public void setCarTaxCost(BigDecimal carTaxCost) {
		this.carTaxCost = carTaxCost;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public void setFinalPayment(BigDecimal finalPayment) {
		this.finalPayment = finalPayment;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setPcIcon(String pcIcon) {
		this.pcIcon = pcIcon;
	}
	
	
}
