package com.framework.utils;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

public class ReturnData implements Serializable{

	private String code;
	private String message;
	private JSONObject data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public JSONObject getData() {
		return data;
	}
	public void setData(JSONObject data) {
		this.data = data;
	}
	@Override
	public String toString() {
		if(this.data == null) {
			return "{\"code\":"+this.code+",\"message\":\""+this.message+"\",\"data\":"+"null}";
		}
		return "{\"code\":"+this.code+",\"message\":\""+this.message+"\",\"data\":"+this.data.toJSONString()+"}";
	}
	
	
}
