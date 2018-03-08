package com.fullerton.olp.wsdao.bre;

import java.io.IOException;

import org.springframework.oxm.XmlMappingException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import com.fullerton.olp.model.UserDetail;
import com.fullerton.olp.wsdao.bre.model.InvokeBREResponse;
import com.fullerton.olp.wsdao.cp.model.FetchOutputNewApplicationXML;


/**
 * Client to invoke BRE WS
 * 
 * @author nitish
 *
 */
public interface BreDAO  {


	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 15000))
	public InvokeBREResponse invokeBRE(FetchOutputNewApplicationXML fetchOutputNewApplicationXML,UserDetail userDetail) throws XmlMappingException, IOException ;

}