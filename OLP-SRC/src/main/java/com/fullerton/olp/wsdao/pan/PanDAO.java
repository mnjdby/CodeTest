package com.fullerton.olp.wsdao.pan;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import com.fullerton.olp.bean.PanRequest;
import com.fullerton.olp.bean.PanResponse;


/**
 * Client to invoke PAN related services
 * 
 * @author nitish
 *
 */
public interface PanDAO {
 
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 15000))
	public PanResponse verifyPAN(PanRequest panRequest) ;
}