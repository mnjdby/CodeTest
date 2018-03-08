package com.fullerton.olp.wsdao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import com.fullerton.olp.model.AuditTrail;
import com.fullerton.olp.service.AuditTrailService;

public class RestLoggingInterceptor implements ClientHttpRequestInterceptor {

    final static Logger log = LoggerFactory.getLogger(RestLoggingInterceptor.class);

    private AuditTrailService auditTrailService;
    
    public RestLoggingInterceptor(AuditTrailService auditTrailService) {
		super();
		this.auditTrailService = auditTrailService;
	}

	@Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        //traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        log.debug("===========================request begin================================================");
        log.debug("URI         : {}", request.getURI());
        log.debug("Method      : {}", request.getMethod());
        log.debug("Headers     : {}", request.getHeaders() );
        log.debug("Request body: {}", new String(body, "UTF-8"));
        log.debug("==========================request end================================================");
        
		auditTrailService.save(new AuditTrail("REST-WS-REQUEST", request.getURI() +":"+ request.getMethod() , "Headers:" + request.getHeaders() + " Body: "+  new String(body, "UTF-8")));

    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        log.error("============================response begin==========================================");
        log.error("Status code  : {}", response.getStatusCode());
        log.error("Status text  : {}", response.getStatusText());
        log.error("Headers      : {}", response.getHeaders());
        log.error("Response body: {}", inputStringBuilder.toString());
        log.error("=======================response end=================================================");
    }

}