package com.fullerton.olp.wsdao.pan;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullerton.olp.bean.PanRequest;
import com.fullerton.olp.bean.PanResponse;
import com.fullerton.olp.model.AuditTrail;
import com.fullerton.olp.service.AuditTrailService;


/**
 * Client to invoke PAN related services
 * 
 * @author nitish
 *
 */
@Component
public class PanDAOImpl implements PanDAO {

	private static final Logger log = LoggerFactory.getLogger(PanDAO.class);

	@Value("${pan-ws.host}")
	private String PAN_WS_HOST;
	
	@Value("${pan-ws.userid}")
	private String PAN_WS_USERID;
	
	@Value("${pan-ws.key}")
	private String PAN_WS_KEY;
	
		
	private String VERIFY_OTP_REQ = "?appname={appname}&prgname={prgname}&arguments={arguments}&UID={UID}&KeyVal={KeyVal}";
	
	@Resource(name="restTemplate")
	private RestTemplate restTemplate;
	
	@Autowired
	private AuditTrailService auditTrailService;
	
	public PanResponse verifyPAN(PanRequest panRequest) {
		//auditTrailService.save(new AuditTrail(panRequest.getClass().getSimpleName(), "REQUEST", panRequest.toString()));//request getting logged from interceptor
		List<PanRequest> list = new ArrayList<PanRequest>();
		list.add(panRequest);
		Map<String, String> params = new HashMap<String, String>();
		params.put("appname", "IFSPANVerify");
		params.put("prgname", "HTTP");
		params.put("arguments", "-ANSDL#PAN");
		params.put("UID", "Ilab");
		params.put("KeyVal", "1ePodLa0V7BMvxfsGlVm7Q==");
		
		
		HttpEntity<List<PanRequest>> entity = new HttpEntity<List<PanRequest>>(list);
		//TODO find cleaner way to extract response with the help of message converter
		//ResponseEntity<PanResponse> responseEntity = restTemplate.exchange(PAN_WS_HOST + VERIFY_OTP_REQ , HttpMethod.POST, entity, PanResponse.class, params);
		ResponseEntity<String> responseEntity = restTemplate.exchange(PAN_WS_HOST + VERIFY_OTP_REQ , HttpMethod.POST, entity, String.class, params);
		//auditTrailService.save(new AuditTrail(panRequest.getClass().getSimpleName(), "RESPONSE", responseEntity.getBody()));
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		try {
			List results = (List)objectMapper.readValue(responseEntity.getBody(), HashMap.class).get("data");
			
			return parseResponse(results.get(0).toString().replaceAll("=",":"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			auditTrailService.save(new AuditTrail("REST-WS-RESPONSE", "PAN Verification", responseEntity.getBody()));
		}
		
		
		return new PanResponse();//responseEntity.getBody();
				//return responseEntity.getBody();
	}
	private PanResponse parseResponse(String jsonString)  {
		String[] strings = jsonString.replace("{", "").replace("}", "").split(", ");
		PanResponse panResponse = new PanResponse();
		for (String string : strings) {
			try {
				BeanUtils.copyProperty(panResponse, string.split(":")[0], string.split(":")[1]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return panResponse;
	}

}