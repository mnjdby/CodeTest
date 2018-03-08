package com.fullerton.olp.wsdao.crmnext;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import com.fullerton.olp.model.UserDetail;


/**
 * Sample client to invoice WS
 * 
 * @author nitish
 *
 */
public interface CrmNextDAO  {

	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 15000))
	public String createLead(UserDetail userDetail) ;
	
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 15000))
	public String createSave(UserDetail userDetail  );

}