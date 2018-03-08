package com.fullerton.olp.wsdao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptorAdapter;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import com.fullerton.olp.model.AuditTrail;
import com.fullerton.olp.service.AuditTrailService;

public class SoapLoggingInterceptor extends ClientInterceptorAdapter {

    final static Logger log = LoggerFactory.getLogger(SoapLoggingInterceptor.class);
    
    private AuditTrailService auditTrailService;
    
    
    

	public SoapLoggingInterceptor(AuditTrailService auditTrailService) {
		super();
		this.auditTrailService = auditTrailService;
	}

	@Override
	public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
    		log.debug("===========================request begin================================================");
//        log.error("URI         : {}", request.getURI());
//        
//        log.error("Headers     : {}", request.getHeaders() );
//        log.error("Request body: {}", new String(body, "UTF-8"));
        
    		try {
    				log.debug("Method      : {}", ((SaajSoapMessage) messageContext.getRequest()).getSaajMessage().getMimeHeaders().getHeader("SOAPAction")[0]);
    				ByteArrayOutputStream os = new ByteArrayOutputStream();
    				messageContext.getRequest().writeTo(os);
    				log.debug("Request body: {}", os.toString());
    				auditTrailService.save(new AuditTrail("WS-REQUEST", ((SaajSoapMessage) messageContext.getRequest()).getSaajMessage().getMimeHeaders().getHeader("SOAPAction")[0], os.toString()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		log.debug("==========================request end================================================");
		return true;
	}

	@Override
	public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
		
		log.debug("============================response begin==========================================");
//        log.error("Status code  : {}", response.getStatusCode());
//        log.error("Status text  : {}", response.getStatusText());
//        log.error("Headers      : {}", response.getHeaders());
//        log.error("Response body: {}", inputStringBuilder.toString());
        
        
        try {
        		ByteArrayOutputStream os = new ByteArrayOutputStream();
			messageContext.getResponse().writeTo(os);
			log.debug("Response body: {}", os.toString());
			auditTrailService.save(new AuditTrail("WS-RESPONSE", ((SaajSoapMessage) messageContext.getRequest()).getSaajMessage().getMimeHeaders().getHeader("SOAPAction")[0], os.toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        log.debug("=======================response end=================================================");
		return true;
	}

	@Override
	public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
		try {
			messageContext.getResponse().writeTo(System.out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
    @Override
    public void afterCompletion(MessageContext messageContext, Exception ex) throws WebServiceClientException {
    		log.info("=========================API CALL COMPLETED=========================");
    }
}