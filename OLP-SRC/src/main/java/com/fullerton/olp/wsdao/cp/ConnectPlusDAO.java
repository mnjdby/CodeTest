package com.fullerton.olp.wsdao.cp;


import org.springframework.oxm.Marshaller;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import com.fullerton.olp.model.UserDetail;
import com.fullerton.olp.wsdao.cp.model.FetchOutputNewApplicationXML;


/**
 * Client to invoke CP WS
 * 
 * @author nitish
 *
 */
public interface ConnectPlusDAO {


	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 15000))
	public String createNewAccount(UserDetail userDetail);
	
	@Retryable(maxAttempts = 5, backoff = @Backoff(delay = 30000))
	public FetchOutputNewApplicationXML fetchNewApplicationXML(String ticketNumber );

	public Marshaller getCPMarshaller();
}