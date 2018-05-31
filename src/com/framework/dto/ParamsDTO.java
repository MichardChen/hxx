package com.framework.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import com.framework.utils.StringUtil;

public class ParamsDTO implements Serializable{

	private static ParamsDTO dto;
	private int pageSize;
	private int pageNum;
	private String mark;
	private String mobile;
	private String name;
	private int cartId;
	private int employeeId;
	private String question;
	private String code;
	
	private int brandId;
	private BigDecimal fromFirstPayment;
	private BigDecimal toFirstPayment;
	private BigDecimal fromMonthPayment;
	private BigDecimal toMonthPayment;
	private BigDecimal fromCarCost;
	private BigDecimal toCarCost;
	private String carLevelCd;
	
	
	public static ParamsDTO getInstance(HttpServletRequest request){
		
		dto = new ParamsDTO();
		dto.setBrandId(StringUtil.toInteger(request.getParameter("brandId")));
		dto.setFromFirstPayment(StringUtil.toQueryCarBigDecimal(request.getParameter("fromFirstPayment")));
		dto.setToFirstPayment(StringUtil.toQueryCarBigDecimal(request.getParameter("toFirstPayment")));
		dto.setFromMonthPayment(StringUtil.toQueryCarBigDecimal(request.getParameter("fromMonthPayment")));
		dto.setToMonthPayment(StringUtil.toQueryCarBigDecimal(request.getParameter("toMonthPayment")));
		dto.setFromCarCost(StringUtil.toQueryCarBigDecimal(request.getParameter("fromCarCost")));
		dto.setToCarCost(StringUtil.toQueryCarBigDecimal(request.getParameter("toCarCost")));
		dto.setCarLevelCd(request.getParameter("carLevelCd"));
		
		dto.setPageNum(StringUtil.toInteger(request.getParameter("pageNum")));
		dto.setPageSize(StringUtil.toInteger(request.getParameter("pageSize")));
		dto.setMark(request.getParameter("mark"));
		dto.setMobile(request.getParameter("mobile"));
		dto.setName(request.getParameter("name"));
		dto.setCartId(StringUtil.toInteger(request.getParameter("cartId")));
		dto.setEmployeeId(StringUtil.toInteger(request.getParameter("employeeId")));
		dto.setQuestion(request.getParameter("question"));
		dto.setCode(request.getParameter("code"));
		return dto;
	}

	
	
	public int getBrandId() {
		return brandId;
	}



	public BigDecimal getFromFirstPayment() {
		return fromFirstPayment;
	}



	public BigDecimal getToFirstPayment() {
		return toFirstPayment;
	}



	public BigDecimal getFromMonthPayment() {
		return fromMonthPayment;
	}



	public BigDecimal getToMonthPayment() {
		return toMonthPayment;
	}



	public BigDecimal getFromCarCost() {
		return fromCarCost;
	}



	public BigDecimal getToCarCost() {
		return toCarCost;
	}



	public String getCarLevelCd() {
		return carLevelCd;
	}



	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}



	public void setFromFirstPayment(BigDecimal fromFirstPayment) {
		this.fromFirstPayment = fromFirstPayment;
	}



	public void setToFirstPayment(BigDecimal toFirstPayment) {
		this.toFirstPayment = toFirstPayment;
	}



	public void setFromMonthPayment(BigDecimal fromMonthPayment) {
		this.fromMonthPayment = fromMonthPayment;
	}



	public void setToMonthPayment(BigDecimal toMonthPayment) {
		this.toMonthPayment = toMonthPayment;
	}



	public void setFromCarCost(BigDecimal fromCarCost) {
		this.fromCarCost = fromCarCost;
	}



	public void setToCarCost(BigDecimal toCarCost) {
		this.toCarCost = toCarCost;
	}



	public void setCarLevelCd(String carLevelCd) {
		this.carLevelCd = carLevelCd;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getQuestion() {
		return question;
	}



	public void setQuestion(String question) {
		this.question = question;
	}



	public int getEmployeeId() {
		return employeeId;
	}



	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}



	public int getCartId() {
		return cartId;
	}


	public void setCartId(int cartId) {
		this.cartId = cartId;
	}


	public int getPageSize() {
		return pageSize;
	}
	
	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getPageNum() {
		return pageNum;
	}


	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
}
