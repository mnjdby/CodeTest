package com.fullerton.olp.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PanResponse extends BaseBean {
	
	private static final String SUCESS_RESPONSE_STATUS = "Success";
	private static final String PAN_VALID_STATUS = "Valid";
	
	private String responseCode; 
	private String errorMsg;
	private String panNo; 
	private String panStatus; 
	private String lastName; 
	private String firstName; 
	private String middleName; 
	private String panTitle; 
	private String panLastUpdate ;
	private String filler1; 
	private String filler2; 
	private String filler3; 
	
 
	
	public String getResponseCode() {
		return responseCode;
	}



	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}



	public String getErrorMsg() {
		return errorMsg;
	}



	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}



	public String getPanNo() {
		return panNo;
	}



	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}



	public String getPanStatus() {
		return panStatus;
	}



	public void setPanStatus(String panStatus) {
		this.panStatus = panStatus;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getMiddleName() {
		return middleName;
	}



	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}



	public String getPanTitle() {
		return panTitle;
	}



	public void setPanTitle(String panTitle) {
		this.panTitle = panTitle;
	}



	public String getPanLastUpdate() {
		return panLastUpdate;
	}



	public void setPanLastUpdate(String panLastUpdate) {
		this.panLastUpdate = panLastUpdate;
	}



	public String getFiller1() {
		return filler1;
	}



	public void setFiller1(String filler1) {
		this.filler1 = filler1;
	}



	public String getFiller2() {
		return filler2;
	}



	public void setFiller2(String filler2) {
		this.filler2 = filler2;
	}



	public String getFiller3() {
		return filler3;
	}



	public void setFiller3(String filler3) {
		this.filler3 = filler3;
	}



	public boolean isResponseOK() {
		return SUCESS_RESPONSE_STATUS.equalsIgnoreCase(this.errorMsg) && PAN_VALID_STATUS.equalsIgnoreCase(panStatus);
	}
}