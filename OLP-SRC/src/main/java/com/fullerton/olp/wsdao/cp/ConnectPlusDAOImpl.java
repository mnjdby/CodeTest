package com.fullerton.olp.wsdao.cp;

import java.math.BigInteger;
import java.util.Date;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.fullerton.olp.model.UserDetail;
import com.fullerton.olp.settings.exceptions.StatusPendingException;
import com.fullerton.olp.settings.util.DateUtil;
import com.fullerton.olp.wsdao.cp.model.FetchOutputNewApplicationXML;
import com.fullerton.olp.wsdao.cp.model.InputNewApplicationXML;
import com.fullerton.olp.wsdao.cp.model.ObjectFactory;
import com.fullerton.olp.wsdao.cp.model.Ticket;


/**
 * Client to invoke CP WS
 * 
 * @author nitish
 *
 */
public class ConnectPlusDAOImpl extends WebServiceGatewaySupport implements ConnectPlusDAO {

	private static final Logger log = LoggerFactory.getLogger(ConnectPlusDAO.class);

	@Override
	public  Marshaller getCPMarshaller() {
		// TODO Auto-generated method stub
		return getWebServiceTemplate().getMarshaller();
	}
	public String createNewAccount(UserDetail userDetail) {
		
		InputNewApplicationXML request = new ObjectFactory().createInputNewApplicationXML();
		request.setExtended(true);
		request.setApplicantBlockConsumerName1(userDetail.getName().split(" ")[0]);
		request.setApplicantBlockConsumerName2(userDetail.getName().split(" ").length > 1? userDetail.getName().split(" ")[1]:"");//TODO check if second param will be ok after name splitting
		
		request.setControlBlockReferenceNumber(new Date().getTime() + "" +userDetail.getId());
		request.setControlBlockCallType("01");//01 - Consumer CIBIL
		request.setControlBlockSearchType("2");//2 - Full Search
		request.setControlBlockScoreType("1");//1 - CIBIL TU Score
		request.setControlBlockStrategyCode("01");//01 - CIBIL
		request.setControlBlockSearchPreviousApplications("1");//default 1
		
		request.setApplicantBlockApplicantType("01");//01 - Main Applicant
		request.setApplicantBlockGender(userDetail.getPersonalDetail().getGender().getName().equalsIgnoreCase("male")?"M":"F");//we wont get this on QDE, has to be on DDE
		request.setApplicantBlockPrefix(userDetail.getPersonalDetail().getGender().getName().equalsIgnoreCase("male")?"Mr":"Mrs");//TODO how to get this? not captured anywhere
		
		request.setApplicantBlockIDNumber2(userDetail.getProfessionalDetail().getPanNumber());
		request.setApplicantBlockNationality("356");//356 - Default
		request.setApplicantBlockDateOfBirth(DateUtil.getDateCPString(userDetail.getPersonalDetail().getDob()));
		request.setApplicantBlockPlaceOfBirth("356");//356 - Default
		
		request.setApplicantBlockOtherMilitaryServiceStatus("N");//N - Default
		request.setApplicantBlockOtherExistingCustomer("0");//0 or 1
		
		request.setApplicantBlockContactDetailsMobileTelephoneNumber(userDetail.getMobile());
		request.setApplicantBlockContactDetailsEmailAddress(userDetail.getEmail());
		
		request.setApplicantBlockEmploymentDetailsAddressLine1(userDetail.getProfessionalDetail().getWorkAddress().getAddress1());
		request.setApplicantBlockEmploymentDetailsAddressLine2(userDetail.getProfessionalDetail().getWorkAddress().getAddress2());
		request.setApplicantBlockEmploymentDetailsAddressLine3(userDetail.getProfessionalDetail().getWorkAddress().getAddress3());
		request.setApplicantBlockEmploymentDetailsAddressLine6(userDetail.getProfessionalDetail().getWorkAddress().getCity().getName());
		request.setApplicantBlockEmploymentDetailsAddressLine7(userDetail.getProfessionalDetail().getWorkAddress().getCity().getState().getTerritory().getName());
		request.setApplicantBlockEmploymentDetailsAddressLine11(userDetail.getProfessionalDetail().getWorkAddress().getPincode().getName());
		
		request.setCreditBlockFinanceEnquiryType("5");//Finance / Enquiry type - Attributes section in Generic Buffer
		request.setCreditBlockPurposeOfFinance("5");//Purpose Of Finance - Attributes section in Generic Buffer
		request.setCreditBlockAmountOfFinance(String.valueOf(userDetail.getLoanAmount().intValue()));//Required Loan Amount 
		
        
		request.setControlBlockApplicationDate(DateUtil.getDateCPString(new Date()));//Case Login Date
		request.setControlBlockCountryCode("365");//356 - Default
		request.setControlBlockNumberOfApplicants("1");//Number of applicants on application
		
		request.setApplicantBlockIDDocumentType1("P");//Passport
		request.setApplicantBlockIDIssuingCountry1("356");//356 - Default
		request.setApplicantBlockIDDocumentType2("T");//PAN / TAN
		request.setApplicantBlockIDIssuingCountry2("356");//356 - Default
		request.setApplicantBlockIDDocumentType3("V");//Voter ID
		request.setApplicantBlockIDIssuingCountry3("356");//356 - Default
		request.setApplicantBlockIDDocumentType4("D");//Driving License
		request.setApplicantBlockIDIssuingCountry4("356");//356 - Default
		
		request.setApplicantBlockCitizenship("356");//356 - Default
		
		request.setApplicantBlockEmploymentDetailsTimeInEmploymentYYMM("0200");//Time in Employment YYMM //TODO dynamically get this
		request.setApplicantBlockEmploymentDetailsAddressCountryCode("356");//356 - Default
		request.setApplicantBlockOtherBureauSearchConsent("1");//1- Yes or 0 - No Default - 1
		request.setApplicantBlockDecisionFinalDecision("ACCPT");//ACCPT default
		request.setApplicantBlockContactDetailsWorkTelephoneNumber(userDetail.getProfessionalDetail().getWorkAddress().getLandline());//Office Telephone Number 
		 
		request.setApplicantBlockAddressDetailsResidentialCountryCode("356");//356 - Default
		request.setApplicantBlockAddressDetailsResidentialAddressLine11(userDetail.getPersonalDetail().getResidenceAddress().getPincode().getName());//Pin Code
		request.setApplicantBlockAddressDetailsResidentialAddressLine7(userDetail.getPersonalDetail().getResidenceAddress().getCity().getState().getTerritory().getName());
		request.setApplicantBlockAddressDetailsResidentialAddressLine1(userDetail.getPersonalDetail().getResidenceAddress().getAddress1());
		request.setApplicantBlockAddressDetailsResidentialAddressLine6(userDetail.getPersonalDetail().getResidenceAddress().getCity().getName());
		request.setApplicantBlockAddressDetailsResidentialAddressLine2(userDetail.getPersonalDetail().getResidenceAddress().getAddress2());
		request.setApplicantBlockAddressDetailsResidentialAddressLine3(userDetail.getPersonalDetail().getResidenceAddress().getAddress3());
		
        
		request.setControlBlockCurrencyCode("INR");//default
		request.setControlBlockLanguageCode("E");//default
        
		request.setApplicantBlockContactDetailsHomeInternationalCode("91");//91 - Default
		request.setApplicantBlockContactDetailsMobileInternationalDialCode("91");//91 - Default
		request.setApplicantBlockContactDetailsWorkInternationalDialCode("91");//91 - Default
		
		request.setApplicantBlockEmploymentDetailsEmploymentStatus("D");//Employment Status - Attributes section in Generic Buffer
		request.setApplicantBlockAddressDetailsResidentialTimeAtCurrentAddressYYMM("0400");//TODO get this dynamically
        
		QName qName = new QName("com.fullerton.olp.wsdao.cp.model", "InputNewApplicationXML");
	    JAXBElement<InputNewApplicationXML> root = new JAXBElement<>(qName, InputNewApplicationXML.class, request);
	    
		log.info("Creating new account with request: " + root);
		 
		JAXBElement<Ticket> response =  (JAXBElement)getWebServiceTemplate()
				.marshalSendAndReceive(getWebServiceTemplate().getDefaultUri(),
						root,
						new SoapActionCallback("asynchronousNewApplicationXML"));

		return response.getValue().getTicketNumber().toString();
	}
	
	public FetchOutputNewApplicationXML fetchNewApplicationXML(String ticketNumber ) {
		
		Ticket ticket =  new ObjectFactory().createTicket();
		ticket.setTicketNumber(BigInteger.valueOf(Long.parseLong(ticketNumber)));
		
		//JAXBElement<Ticket> request = new ObjectFactory().createFetchInputNewApplicationXML(ticket);
		
		QName qName = new QName("com.fullerton.olp.wsdao.cp.model", "fetchInputNewApplicationXML");
	    JAXBElement<Ticket> root = new JAXBElement<Ticket>(qName, Ticket.class, ticket);
	  
		log.info("Creating new account with request: " + root);
		
		FetchOutputNewApplicationXML response =  (FetchOutputNewApplicationXML) getWebServiceTemplate()
				.marshalSendAndReceive(getWebServiceTemplate().getDefaultUri(),
						root,
						new SoapActionCallback("fetchNewApplicationXML"));
		if(response.isPending()) {
			throw new StatusPendingException();
		}
		return response;
	}

}