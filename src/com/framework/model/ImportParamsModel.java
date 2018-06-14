package com.framework.model;

import java.io.Serializable;

import com.framework.entity.TCartParam2Entity;
import com.framework.entity.TCartParamsEntity;

public class ImportParamsModel implements Serializable{

	private TCartParamsEntity params;
	private TCartParam2Entity params2;
	public TCartParamsEntity getParams() {
		return params;
	}
	public TCartParam2Entity getParams2() {
		return params2;
	}
	public void setParams(TCartParamsEntity params) {
		this.params = params;
	}
	public void setParams2(TCartParam2Entity params2) {
		this.params2 = params2;
	}
}
