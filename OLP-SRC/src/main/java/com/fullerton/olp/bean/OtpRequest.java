package com.fullerton.olp.bean;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OtpRequest extends BaseBean {
	private String mappingId = "1";
	private String mobileNumber;
	private String emailId;
	private String userId = "RE123";
	private String deviceCreatedOn;
	private String uniqueRefernceNumber;
	
	@NotNull
	private String otp;
	
	
	public String getMappingId() {
		return mappingId;
	}
	public void setMappingId(String mappingId) {
		this.mappingId = mappingId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDeviceCreatedOn() {
		return deviceCreatedOn;
	}
	public void setDeviceCreatedOn(String deviceCreatedOn) {
		this.deviceCreatedOn = deviceCreatedOn;
	}
	public String getUniqueRefernceNumber() {
		return uniqueRefernceNumber;
	}
	public void setUniqueRefernceNumber(String uniqueRefernceNumber) {
		this.uniqueRefernceNumber = uniqueRefernceNumber;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	
}
