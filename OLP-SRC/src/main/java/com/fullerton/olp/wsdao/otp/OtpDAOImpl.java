package com.fullerton.olp.wsdao.otp;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fullerton.olp.bean.OtpRequest;
import com.fullerton.olp.bean.OtpResponse;
import com.fullerton.olp.model.AuditTrail;
import com.fullerton.olp.service.AuditTrailService;


/**
 * Client to invoke OTP related services
 * 
 * @author nitish
 *
 */
@Component
public class OtpDAOImpl implements OtpDAO{

	private static final Logger log = LoggerFactory.getLogger(OtpDAO.class);

	@Value("${otp-ws.host}")
	private String OTP_WS_HOST;
	
	private String GET_OTP_REQ = "generateOTP";
	private String VERIFY_OTP_REQ = "verifyOTP";
	
	@Resource(name="restTemplate")
	private RestTemplate restTemplate;
	
	
	@Autowired
	private AuditTrailService auditTrailService;
	
	public OtpResponse sendOTP(OtpRequest otpRequest) {
		HttpEntity<OtpRequest> entity = new HttpEntity<OtpRequest>(otpRequest);
		ResponseEntity<OtpResponse> responseEntity = restTemplate.exchange(OTP_WS_HOST + GET_OTP_REQ, HttpMethod.POST, entity, OtpResponse.class);
		
		auditTrailService.save(new AuditTrail("REST-WS-RESPONSE", OTP_WS_HOST + GET_OTP_REQ +":"+ HttpMethod.POST , "Headers:" +  entity.getHeaders() + " Body: "+ responseEntity.getBody() ));

		return responseEntity.getBody();
	}
	 
	public OtpResponse verifyOTP(OtpRequest otpRequest) {
		HttpEntity<OtpRequest> entity = new HttpEntity<OtpRequest>(otpRequest);
		ResponseEntity<OtpResponse> responseEntity = restTemplate.exchange(OTP_WS_HOST + VERIFY_OTP_REQ , HttpMethod.POST, entity, OtpResponse.class);
		
		auditTrailService.save(new AuditTrail("REST-WS-RESPONSE", OTP_WS_HOST + GET_OTP_REQ +":"+ HttpMethod.POST , "Headers:" +  entity.getHeaders() + " Body: "+ responseEntity.getBody() ));

		return responseEntity.getBody();
	}

}