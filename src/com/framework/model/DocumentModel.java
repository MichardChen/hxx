package com.framework.model;

import java.io.Serializable;

public class DocumentModel implements Serializable{
	
	private String code;
	private String documentName;
	private String documentUrl;
	
	public String getCode() {
		return code;
	}
	public String getDocumentName() {
		return documentName;
	}
	public String getDocumentUrl() {
		return documentUrl;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}
}
