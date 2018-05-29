package com.framework.model;

import java.io.Serializable;

public class FinanceListModel implements Serializable{
	
	private int id;
	private String financeName;
	private String rate;
	private String period;
	private String moneys;
	private String standard;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getMoneys() {
		return moneys;
	}
	public void setMoneys(String moneys) {
		this.moneys = moneys;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	
	

}
