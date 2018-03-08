package com.fullerton.olp.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="FullertonOtpResponse")
public class AadhaarOtpResponse extends BaseBean {
	
	    	
	private static final String SUCESS_RESPONSE_STATUS = "SUCCESS";//TODO check this code
	private String statusCode;
	private String resCode;
	private String error;
	private String cidrTs;
	private String transactionId;
	private String uidData;
	private String statusDescription;
	
	public String getStatusCode() {
		return statusCode;
	}



	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}



	public String getResCode() {
		return resCode;
	}



	public void setResCode(String resCode) {
		this.resCode = resCode;
	}



	public String getError() {
		return error;
	}



	public void setError(String error) {
		this.error = error;
	}



	public String getCidrTs() {
		return cidrTs;
	}



	public void setCidrTs(String cidrTs) {
		this.cidrTs = cidrTs;
	}



	public String getTransactionId() {
		return transactionId;
	}



	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}



	public String getUidData() {
		return uidData;
	}



	public void setUidData(String uidData) {
		this.uidData = uidData;
	}



	public String getStatusDescription() {
		return statusDescription;
	}



	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}



	public boolean isResponseOK() {
		return SUCESS_RESPONSE_STATUS.equalsIgnoreCase(this.statusCode);
	}
}
