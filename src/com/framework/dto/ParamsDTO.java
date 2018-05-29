package com.framework.dto;

import java.io.Serializable;

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
	
	
	public static ParamsDTO getInstance(HttpServletRequest request){
		dto = new ParamsDTO();
		dto.setPageNum(StringUtil.toInteger(request.getParameter("pageSize")));
		dto.setPageSize(StringUtil.toInteger(request.getParameter("pageNum")));
		dto.setMark(request.getParameter("mark"));
		dto.setMobile(request.getParameter("mobile"));
		dto.setName(request.getParameter("name"));
		dto.setCartId(StringUtil.toInteger(request.getParameter("cartId")));
		dto.setEmployeeId(StringUtil.toInteger(request.getParameter("employeeId")));
		dto.setQuestion(request.getParameter("question"));
		dto.setCode(request.getParameter("code"));
		return dto;
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
