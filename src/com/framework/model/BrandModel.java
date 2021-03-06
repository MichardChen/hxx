package com.framework.model;

import java.io.Serializable;

public class BrandModel implements Serializable{
	
	private Integer id;
	//
	private String brand;
	//
	private String brandIcon;
	//
	private String word;
	
	private int showflg;
	
	private int importflg;
	
	private int flg;
	
	public int getFlg() {
		return flg;
	}
	public void setFlg(int flg) {
		this.flg = flg;
	}
	public int getImportflg() {
		return importflg;
	}
	public void setImportflg(int importflg) {
		this.importflg = importflg;
	}
	public int getShowflg() {
		return showflg;
	}
	public void setShowflg(int showflg) {
		this.showflg = showflg;
	}
	public Integer getId() {
		return id;
	}
	public String getBrand() {
		return brand;
	}
	public String getBrandIcon() {
		return brandIcon;
	}
	public String getWord() {
		return word;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setBrandIcon(String brandIcon) {
		this.brandIcon = brandIcon;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	

}
