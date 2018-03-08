package com.fullerton.olp.bean;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="FullertonOtpRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class AadhaarOtpRequest extends BaseBean {
	
	@XmlAttribute
	private String username;
	
	@XmlAttribute
	private String password;
	
	@XmlAttribute
	private String uid;
	
	@XmlAttribute
	private String udc;
	
	@XmlAttribute
	private String channel = "00";
	
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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	 
	
}
