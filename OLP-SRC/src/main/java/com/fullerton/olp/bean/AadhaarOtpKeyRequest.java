package com.fullerton.olp.bean;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="FullertonOtpKycRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class AadhaarOtpKeyRequest extends BaseBean {
	
	@XmlAttribute
	private String username;
	@XmlAttribute
	private String password;
	@XmlAttribute
	private String consent;
	@XmlAttribute
	private String customerType;
	@XmlAttribute
	private String leadId;
	@XmlAttribute
	private String pfr = "N";
	@XmlAttribute
	private String lr = "N";
	@XmlAttribute
	private String otpTxn;
	@XmlAttribute
	private String uid;
	@XmlAttribute
	private String udc;
	@NotNull
	@XmlAttribute
	private String otp;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConsent() {
		return consent;
	}
	public void setConsent(String consent) {
		this.consent = consent;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getLeadId() {
		return leadId;
	}
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}
	public String getPfr() {
		return pfr;
	}
	public void setPfr(String pfr) {
		this.pfr = pfr;
	}
	public String getLr() {
		return lr;
	}
	public void setLr(String lr) {
		this.lr = lr;
	}
	public String getOtpTxn() {
		return otpTxn;
	}
	public void setOtpTxn(String otpTxn) {
		this.otpTxn = otpTxn;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUdc() {
		return udc;
	}
	public void setUdc(String udc) {
		this.udc = udc;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
 
	
	 
	
}
