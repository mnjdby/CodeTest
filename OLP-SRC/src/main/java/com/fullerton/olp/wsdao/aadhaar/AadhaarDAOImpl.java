package com.fullerton.olp.wsdao.aadhaar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fullerton.olp.bean.AadhaarOtpKeyRequest;
import com.fullerton.olp.bean.AadhaarOtpKeyResponse;
import com.fullerton.olp.bean.AadhaarOtpRequest;
import com.fullerton.olp.bean.AadhaarOtpResponse;
import com.fullerton.olp.model.AuditTrail;
import com.fullerton.olp.service.AuditTrailService;


/**
 * Client to invoke Aadhar related services
 * 
 * @author nitish
 *
 */
@Component
public class AadhaarDAOImpl implements AadhaarDAO {

	private static final Logger log = LoggerFactory.getLogger(AadhaarDAO.class);

	@Value("${aadhaar-ws.host}")
	private String AADHAAR_WS_HOST;
	
	@Value("${aadhaar-ws.username}")
	private String AADHAAR_USERNAME;
	
	
	@Value("${aadhaar-ws.password}")
	private String AADHAAR_PASSWORD;
	
	
	@Value("${aadhaar-ws.udc}")
	private String AADHAAR_UDC;
	
	@Autowired
    private AuditTrailService auditTrailService;
	
	
	private String GET_OTP_REQ = "";//TODO what is the request to submit
	private String VERIFY_OTP_REQ = "";//TODO what is the request to submit
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public AadhaarOtpResponse sendOTP(AadhaarOtpRequest otpRequest) {
		otpRequest.setUsername(AADHAAR_USERNAME);
		otpRequest.setPassword(AADHAAR_PASSWORD);
		otpRequest.setUdc(AADHAAR_UDC);
		
		HttpEntity<AadhaarOtpRequest> entity = new HttpEntity<AadhaarOtpRequest>(otpRequest);
		//entity.getHeaders().add("Content-Type", "text/xml");
		ResponseEntity<AadhaarOtpResponse> responseEntity = restTemplate.exchange(AADHAAR_WS_HOST + GET_OTP_REQ, HttpMethod.POST, entity, AadhaarOtpResponse.class);
		
		auditTrailService.save(new AuditTrail("REST-WS-RESPONSE", AADHAAR_WS_HOST + GET_OTP_REQ +":"+ HttpMethod.POST , "Headers:" +  entity.getHeaders() + " Body: "+ responseEntity.getBody() ));
		
		return responseEntity.getBody();
	}
	 
	public AadhaarOtpKeyResponse verifyOTP(AadhaarOtpKeyRequest otpRequest) {
		otpRequest.setUsername(AADHAAR_USERNAME);
		otpRequest.setPassword(AADHAAR_PASSWORD);
		otpRequest.setUdc(AADHAAR_UDC);
		
		HttpEntity<AadhaarOtpKeyRequest> entity = new HttpEntity<AadhaarOtpKeyRequest>(otpRequest);
		//entity.getHeaders().add("Content-Type", "text/xml");
		ResponseEntity<AadhaarOtpKeyResponse> responseEntity = restTemplate.exchange(AADHAAR_WS_HOST + VERIFY_OTP_REQ , HttpMethod.POST, entity, AadhaarOtpKeyResponse.class);
		
		auditTrailService.save(new AuditTrail("REST-WS-RESPONSE", AADHAAR_WS_HOST + GET_OTP_REQ +":"+ HttpMethod.POST , "Headers:" + entity.getHeaders() + " Body: "+ responseEntity.getBody() ));
		return responseEntity.getBody();
	}

}