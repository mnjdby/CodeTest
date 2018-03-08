package com.fullerton.olp.wsdao.otp;

import com.fullerton.olp.bean.OtpRequest;
import com.fullerton.olp.bean.OtpResponse;


/**
 * Client to invoke OTP related services
 * 
 * @author nitish
 *
 */
public interface OtpDAO {

	 
	
	public OtpResponse sendOTP(OtpRequest otpRequest);
	 
	public OtpResponse verifyOTP(OtpRequest otpRequest);

}