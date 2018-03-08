package com.fullerton.olp.wsdao.aadhaar;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import com.fullerton.olp.bean.AadhaarOtpKeyRequest;
import com.fullerton.olp.bean.AadhaarOtpKeyResponse;
import com.fullerton.olp.bean.AadhaarOtpRequest;
import com.fullerton.olp.bean.AadhaarOtpResponse;


/**
 * Client to invoke Aadhar related services
 * 
 * @author nitish
 *
 */
//@Component
public interface AadhaarDAO {

	 
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 15000))
	public AadhaarOtpResponse sendOTP(AadhaarOtpRequest otpRequest)  ;
	 
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 15000))
	public AadhaarOtpKeyResponse verifyOTP(AadhaarOtpKeyRequest otpRequest) ;

}