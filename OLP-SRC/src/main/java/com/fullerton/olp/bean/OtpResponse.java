package com.fullerton.olp.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OtpResponse extends BaseBean {
	
	private static final String SUCESS_RESPONSE_STATUS = "SUCCESS";
	private String status;
	private String code;
	private String message;
	private List<Object> data;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	
	public boolean isResponseOK() {
		return SUCESS_RESPONSE_STATUS.equalsIgnoreCase(this.status);
	}
}
