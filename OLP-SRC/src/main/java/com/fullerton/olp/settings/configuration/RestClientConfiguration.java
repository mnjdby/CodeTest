package com.fullerton.olp.settings.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fullerton.olp.service.AuditTrailService;
import com.fullerton.olp.wsdao.RestLoggingInterceptor;

/**
 *  REST client configuration
 * 
 * @author nitish
 *
 */
@Configuration
public class RestClientConfiguration {

	
	 
	
	@Bean
	public RestTemplate restTemplate(AuditTrailService auditTrailService) {
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
		restTemplate.setInterceptors(Collections.singletonList(new RestLoggingInterceptor(auditTrailService )));
        return restTemplate;
	}
	
	
	private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(10000);
        factory.setConnectTimeout(10000);
        return factory;
    }
	
	
}