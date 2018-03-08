package com.fullerton.olp.wsdao.bre;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.XmlMappingException;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

import com.fullerton.olp.model.AuditTrail;
import com.fullerton.olp.model.UserDetail;
import com.fullerton.olp.service.AuditTrailService;
import com.fullerton.olp.settings.email.TemplateService;
import com.fullerton.olp.settings.util.DateUtil;
import com.fullerton.olp.wsdao.bre.model.InvokeBRE;
import com.fullerton.olp.wsdao.bre.model.InvokeBREResponse;
import com.fullerton.olp.wsdao.bre.model.ObjectFactory;
import com.fullerton.olp.wsdao.cp.ConnectPlusDAO;
import com.fullerton.olp.wsdao.cp.model.FetchOutputNewApplicationXML;
import com.fullerton.olp.wsdao.cp.model.FetchOutputNewApplicationXML.RawData;
import com.fullerton.olp.wsdao.cp.model.FetchOutputNewApplicationXML.TransactData;


/**
 * Client to invoke BRE WS
 * 
 * @author nitish
 *
 */
public class BreDAOImpl extends WebServiceGatewaySupport implements BreDAO {

	private static final Logger log = LoggerFactory.getLogger(BreDAOImpl.class);

	@Autowired
	private TemplateService templateService;
	
	@Autowired
	private ConnectPlusDAO connectPlusDAO;
	
	@Resource(name="restTemplate")
	private RestTemplate restTemplate;
	
	@Value("${bre-ws.host}")
	private String BRE_WS_HOST;
	
	@Autowired
	private AuditTrailService auditTrailService;
	
	public InvokeBREResponse invokeBRE(FetchOutputNewApplicationXML fetchOutputNewApplicationXML,UserDetail userDetail) throws XmlMappingException, IOException {
		InvokeBRE invokeBRE = new ObjectFactory().createInvokeBRE();
		
	    
		String formattedObjectText = "<![CDATA[" + getObjectDataText(fetchOutputNewApplicationXML,userDetail) + "]]>";
 		invokeBRE.setObjModel(formattedObjectText);
			
 		String formattedRawText = "<![CDATA[" + getRawDataText(fetchOutputNewApplicationXML) + "]]>";
		    
		invokeBRE.setRawData(formattedRawText);
		Result result = new StringResult();
		getWebServiceTemplate().getMarshaller().marshal(invokeBRE, result);
		log.info("Creating new account with request: " + result);
		
		
		/*
		InvokeBREResponse response = (InvokeBREResponse) getWebServiceTemplate()
				.marshalSendAndReceive(getWebServiceTemplate().getDefaultUri(),
						invokeBRE,
						new SoapActionCallback("invokeBRE"));*/
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.set("SOAPAction", "invokeBRE");
		
		String preRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://serviceimpl.alobr.valuemomentum.com\"><soapenv:Header/><soapenv:Body>";
		
		String postRequest = " </soapenv:Body> </soapenv:Envelope>";
		
		String soapRequest = preRequest + result + postRequest;
		soapRequest = soapRequest.replace("<?xml version=\"1.0\" encoding=\"Unicode\" standalone=\"yes\"?>", "");
		System.out.println(soapRequest);
		HttpEntity<String> entity = new HttpEntity<String>(soapRequest,headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(BRE_WS_HOST, HttpMethod.POST, entity, String.class);
		String responseBody = responseEntity.getBody();
		responseBody = responseBody.replace("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">", "");
		responseBody = responseBody.replace("<soapenv:Body>", "");
		responseBody = responseBody.replace("</soapenv:Body>", "");
		responseBody = responseBody.replace("</soapenv:Envelope>", "");
		responseBody = responseBody.trim();
		auditTrailService.save(new AuditTrail("REST-WS-RESPONSE", BRE_WS_HOST +":"+ HttpMethod.POST , "Headers:" + headers + " Body: "+ responseBody ));
		
		StringSource source = new StringSource(responseBody);
		InvokeBREResponse response = (InvokeBREResponse) getWebServiceTemplate().getUnmarshaller().unmarshal(source);
		return response;
	}


	private String getObjectDataText(FetchOutputNewApplicationXML fetchOutputNewApplicationXML,UserDetail userDetail) {
		Map<String,String> objectDataMap = new HashMap<>();
		
		objectDataMap.put("companyName","OTHERS");
		objectDataMap.put("companyCategory","OTHERS");
		objectDataMap.put("qualification","1");//TODO check for this. we got degree not ids
		objectDataMap.put("dateOfBirth",DateUtil.getDbDateString(userDetail.getPersonalDetail().getDob()));
		objectDataMap.put("declaredGrossMonthlyIncome",String.valueOf(userDetail.getProfessionalDetail().getMonthlyIncome()));
		objectDataMap.put("totalMonthlyEMI","0");
		objectDataMap.put("totalMonthlyEMIPersonalLoans","0");
		objectDataMap.put("additionalIncome",String.valueOf((userDetail.getProfessionalDetail().getOtherIncome())));
		objectDataMap.put("netMonthlyTakeHomeSalary",String.valueOf(userDetail.getProfessionalDetail().getMonthlyIncome()));
		objectDataMap.put("salesTurnover","0");
		objectDataMap.put("gender",userDetail.getPersonalDetail().getGender().getName().equalsIgnoreCase("male")?"M":"F");
		objectDataMap.put("maritalStatus",userDetail.getPersonalDetail().getMarritalStatus().getId().toString());
		objectDataMap.put("noOfMonthsInCurrentJob","60");
		objectDataMap.put("noOfYearsInCurrentJob","5");
		objectDataMap.put("noOfYearsInPreviousJob","0");
		objectDataMap.put("accomodationType","OWND"/*userDetail.getPersonalDetail().getResidenceAddress().getResidenceType().getName()*/);
		objectDataMap.put("noOfYearsAtCurrentAddress",String.valueOf(DateUtil.getDiffYears(userDetail.getPersonalDetail().getResidenceAddress().getLivingSince())));
		objectDataMap.put("noOfMonthsAtCurrentAddress",String.valueOf(DateUtil.getDiffMonths(userDetail.getPersonalDetail().getResidenceAddress().getLivingSince())));
		objectDataMap.put("AddressType","1");
		objectDataMap.put("isPhoneNoValid","1");
		objectDataMap.put("currentResidenceAddressOwnershipStatus","OWN");
		objectDataMap.put("officeAddressCity",userDetail.getProfessionalDetail().getWorkAddress().getCity().getName());
		objectDataMap.put("officeAddressState",userDetail.getProfessionalDetail().getWorkAddress().getCity().getState().getName());
		objectDataMap.put("currentResidenceAddressMobile",userDetail.getMobile());
		objectDataMap.put("noOfMonthsCurrentResidenceAddress",String.valueOf(DateUtil.getDiffMonths(userDetail.getPersonalDetail().getResidenceAddress().getLivingSince())));
		objectDataMap.put("noOfYearsCurrentResidenceAddress",String.valueOf(DateUtil.getDiffYears(userDetail.getPersonalDetail().getResidenceAddress().getLivingSince())));
		objectDataMap.put("State",userDetail.getPersonalDetail().getResidenceAddress().getCity().getState().getName());
		objectDataMap.put("City",userDetail.getPersonalDetail().getResidenceAddress().getCity().getName());
		objectDataMap.put("currentResidenceAddressPhone",userDetail.getMobile());
		objectDataMap.put("officeLandlineNumber",userDetail.getProfessionalDetail().getWorkAddress().getLandline());
		objectDataMap.put("appDate",DateUtil.getDbDateString(new Date()));
		
		objectDataMap.put("residenceType","OWND");
		
		objectDataMap.put("officeLandlineNumber",DateUtil.getDbDateString(new Date()));
		objectDataMap.put("loginDate",DateUtil.getDbDateString(new Date()));
		objectDataMap.put("appRefNo",fetchOutputNewApplicationXML.getTransactData().getApplicationBureauEnquiryReferenceNumber());
		objectDataMap.put("appliedTenor",String.valueOf(userDetail.getTenure()));
		objectDataMap.put("tier",userDetail.getCity().getTier().getName());
		objectDataMap.put("loanAmtApplied",String.valueOf(userDetail.getLoanAmount().intValue()));
		objectDataMap.put("requiredLoanAmount",String.valueOf(userDetail.getLoanAmount().intValue()));
		objectDataMap.put("finalTenureRequired",String.valueOf(userDetail.getTenure()));
		
	    String formattedObjectText = templateService.getFormattedTextFromClassPathFile("template/bre/object_data.xml", objectDataMap);
		return formattedObjectText.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
	}
	

	private String getRawDataText(FetchOutputNewApplicationXML fetchOutputNewApplicationXML) throws IOException {
		Map<String,String> rawDataMap = new HashMap<>();
 		
		QName qNameR = new QName("com.fullerton.olp.wsdao.cp.model", "RawData");
	    JAXBElement<RawData> rawDataElement = new JAXBElement<>(qNameR, RawData.class, fetchOutputNewApplicationXML.getRawData());
	    StringResult rawData = new StringResult();
	    connectPlusDAO.getCPMarshaller().marshal(rawDataElement, rawData);
		
		rawDataMap.put("RAWDATA",rawData.toString());
		
		TransactData transactData = fetchOutputNewApplicationXML.getTransactData();
		rawDataMap.put("UniqueCPlusApplicationNumber",transactData.getApplicationUniqueCPlusApplicationNumber());
		rawDataMap.put("NumericSL15Var5",transactData.getClientSpecificNumericSL15Var5());
		rawDataMap.put("NumericSL15Var29",transactData.getClientSpecificNumericSL15Var29());
		rawDataMap.put("NumericSL15Var37",transactData.getClientSpecificNumericSL15Var37());
		rawDataMap.put("NumericSL3Var54",transactData.getClientSpecificNumericSL3Var54());
		rawDataMap.put("NumericSL3Var59",transactData.getClientSpecificNumericSL3Var59());
		rawDataMap.put("NumericSL3Var62",transactData.getClientSpecificNumericSL3Var62());
		rawDataMap.put("NumericSL15Var40",transactData.getClientSpecificNumericSL15Var40());
		rawDataMap.put("NumericSL15Var41",transactData.getClientSpecificNumericSL15Var41());
		rawDataMap.put("NumericSL15Var42",transactData.getClientSpecificNumericSL15Var42());
		rawDataMap.put("NumericSL15Var43",transactData.getClientSpecificNumericSL15Var43());
		rawDataMap.put("NumericSL15Var44",transactData.getClientSpecificNumericSL15Var44());
		rawDataMap.put("NumericSL15Var2",transactData.getClientSpecificNumericSL15Var2());
		rawDataMap.put("NumericSL15Var3",transactData.getClientSpecificNumericSL15Var3());
		rawDataMap.put("NumericSL3Var61",transactData.getClientSpecificNumericSL3Var61());
		rawDataMap.put("NumericSL3Var19",transactData.getClientSpecificNumericSL3Var19());
		rawDataMap.put("NumericSL15Var33",transactData.getClientSpecificNumericSL15Var33());
		rawDataMap.put("NumericSL3Var31",transactData.getClientSpecificNumericSL3Var31());
		rawDataMap.put("NumericSL3Var5",transactData.getClientSpecificNumericSL3Var5());
		rawDataMap.put("NumericSL15Var11",transactData.getClientSpecificNumericSL15Var11());
		rawDataMap.put("BureauErrorCode",transactData.getErrorBlockBureauErrorCode());
		
		
		String formattedRawText = templateService.getFormattedTextFromClassPathFile("template/bre/raw_data.xml", rawDataMap);
		return formattedRawText.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
	}
	public static void main(String[] args) {
		String s = "&lt;loanApplication&gt;\r\n" + 
		"\r\n" + 
		"&lt;applicant&gt;\r\n" + 
		"\r\n" + 
		"&lt;partyReference&gt;&lt;/partyReference&gt;\r\n" + 
		"\r\n" + 
		"&lt;companyName&gt;$dataMap.get('companyName')&lt;/companyName&gt;";
		
		System.out.println(s.replaceAll("&lt;", "<").replaceAll("&gt;", ">"));
	}

}