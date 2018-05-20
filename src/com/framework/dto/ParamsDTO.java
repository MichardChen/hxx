package com.framework.dto;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.framework.utils.StringUtil;

public class ParamsDTO implements Serializable{

	private static ParamsDTO dto;
	private int pageSize;
	private int pageNum;
	
	
	public static ParamsDTO getInstance(HttpServletRequest request){
		dto = new ParamsDTO();
		dto.setPageNum(StringUtil.toInteger(request.getParameter("pageSize")));
		dto.setPageSize(StringUtil.toInteger(request.getParameter("pageNum")));
		return dto;
	}

	public int getPageSize() {
		return pageSize;
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
