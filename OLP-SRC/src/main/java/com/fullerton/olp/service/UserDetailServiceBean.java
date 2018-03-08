package com.fullerton.olp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fullerton.olp.bean.AadhaarOtpKeyRequest;
import com.fullerton.olp.bean.AadhaarOtpKeyResponse;
import com.fullerton.olp.bean.AadhaarOtpRequest;
import com.fullerton.olp.bean.AadhaarOtpResponse;
import com.fullerton.olp.bean.OtpRequest;
import com.fullerton.olp.bean.PanRequest;
import com.fullerton.olp.bean.PanResponse;
import com.fullerton.olp.bean.StateWiseCount;
import com.fullerton.olp.model.ApplicationStatus;
import com.fullerton.olp.model.EmailTemplate;
import com.fullerton.olp.model.UserDetail;
import com.fullerton.olp.model.UserDocument;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.UserDetailRepository;
import com.fullerton.olp.repository.support.DateRange;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.settings.email.SmtpMailSender;
import com.fullerton.olp.settings.email.TemplateService;
import com.fullerton.olp.settings.sms.SmsService;
import com.fullerton.olp.settings.util.DateUtil;
import com.fullerton.olp.wsdao.aadhaar.AadhaarDAO;
import com.fullerton.olp.wsdao.bre.BreDAO;
import com.fullerton.olp.wsdao.bre.model.InvokeBREResponse;
import com.fullerton.olp.wsdao.cp.ConnectPlusDAO;
import com.fullerton.olp.wsdao.cp.model.FetchOutputNewApplicationXML;
import com.fullerton.olp.wsdao.crmnext.CrmNextDAO;
import com.fullerton.olp.wsdao.omnidocs.OmnidocsDAO;
import com.fullerton.olp.wsdao.otp.OtpDAO;
import com.fullerton.olp.wsdao.pan.PanDAO;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserDetailServiceBean extends GenericEntityServiceImpl<UserDetail, Long> implements UserDetailService {

	private static final Logger log = LoggerFactory.getLogger(UserDetailServiceBean.class);


	@Value("${dupe.check.days.count}")
	private int DUPE_CHECK_DAYS;
	
	private final static String BRE_STATUS_GREEN = "GREEN";
	private final static String BRE_STATUS_AMBER = "AMBER";
	private final static String BRE_STATUS_RED = "RED";
    /**
     * The Spring Data repository for UserDetail entities.
     */
    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private ConnectPlusDAO connectPlusDAO;
    
    @Autowired
    private BreDAO breDAO;
    
    @Autowired
    private CrmNextDAO crmNextDAO;
   
    @Autowired
    private PanDAO panDAO;
    
    @Autowired
    private OtpDAO otpDAO;
    
    @Autowired
    private AadhaarDAO aadhaarDAO;

    @Autowired
    private WorkAddressService workAddressService;
    
    @Autowired
    private ProfessionalDetailService professionalDetailService;
    
    @Autowired
    private PersonalDetailService personalDetailService;

    @Autowired
    private UserDocumentService userDocumentService;
    
    @Autowired
    private DocumentService documentService;
    
    @Autowired
    private DocumentTypeService documentTypeService;
    
    @Autowired
    private  OmnidocsDAO omnidocsDAO;
    
    @Autowired
    private SmsService smsService;

    @Autowired
    private SMSTemplateService smsTemplateService;
    
    @Autowired
    private SmtpMailSender smtpMailSender;
    
    @Autowired
    private EmailTemplateService emailTemplateService;
    
    
    
    @Override
    public Boolean attemptedInThePast(UserDetail userDetail) {
	    	SearchTemplate searchTemplate = new SearchTemplate();
	    	DateRange<Date> createdDateCriteria = new DateRange<>("createdOn");
	    	Calendar calendar = Calendar.getInstance();
	    	calendar.add(Calendar.DATE, -DUPE_CHECK_DAYS);
	    	createdDateCriteria.setFrom(calendar.getTime());
	    	searchTemplate.add(createdDateCriteria);
	    	
	    	UserDetail example = new UserDetail();
	    	example.setEmail(userDetail.getEmail());
	    	example.setMobile(userDetail.getMobile());
	    	example.setIpAddress(userDetail.getIpAddress());
	    	example.setAadhaarVerified(null);
	    	example.setUserVerified(null);
	    	
    		return findCount(example, searchTemplate) > 0;
    }
    
    @Override
    public Boolean attemptedPanInThePast(UserDetail userDetail) {
    	
    		UserDetail userDetailDB = getById(userDetail.getId());
    	
	    	Calendar calendar = Calendar.getInstance();
	    	calendar.add(Calendar.DATE, -DUPE_CHECK_DAYS);
	    Long panCount = userDetailRepository.getPanCount(userDetailDB.getMobile(), userDetail.getProfessionalDetail().getPanNumber(), calendar.getTime());
	    	
    		return panCount > 0;
    }
    
	@Override
	@Transactional
	public Boolean saveFile(Long userDetailId, Long documentTypeId, Long documentId, MultipartFile file) {
		UserDetail userDetail = getById(userDetailId);
		
		try {
			userDocumentService.markInactive(userDetailId, documentId, documentTypeId);
			
			
			
			UserDocument userDocument = new UserDocument();
			userDocument.setActive(true);
			userDocument.setDocumentType(documentTypeService.getById(documentTypeId));
			userDocument.setDocument(documentService.getById(documentId));
			userDocument.setUserDetail(userDetail);
			String location = omnidocsDAO.upload(userDetail.getCpReferanceNumber()+"/"+documentId + "/" + file.getOriginalFilename(), file);
			userDocument.setFileLocation(location);
			userDocumentService.save(userDocument);
			
			super.save(userDetail);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
        
        return false;
	}
    
    @Override
    @Transactional
    public UserDetail saveAadhaar(Long userDetailId, UserDetail entity) {
	    	UserDetail userDetail = findById(userDetailId);
	    	userDetail.setAadhaarNumber(entity.getAadhaarNumber());
	    	userDetail.setAadhaarVerified(false);
	    	userDetail.setAadhaarTxId("");
	    	
	    	save(userDetail);
	    	return userDetail;
    }
    
    @Override
    @Transactional
    public UserDetail saveLoanAmount(Long userDetailId, UserDetail entity) {
	    	UserDetail userDetail = findById(userDetailId);
	    	userDetail.setLoanAmount(entity.getLoanAmount());
	    	userDetail.setTenure(entity.getTenure());
	    	
	    	save(userDetail);
	    	return userDetail;
    }
    
    @Override
    @Transactional
    public UserDetail saveDetailData(UserDetail userDetail) {
    		UserDetail entity = findById(userDetail.getId());
    		entity.setApplicationStatus(new ApplicationStatus(1238l));//DDE CRM status
    		workAddressService.save(userDetail.getProfessionalDetail().getWorkAddress());
    		
    		professionalDetailService.save(userDetail.getProfessionalDetail());
    		
    		if(userDetail.getPersonalDetail() != null) {
    			personalDetailService.save(userDetail.getPersonalDetail());
    		}
    		
    		entity.setProfessionalDetail(userDetail.getProfessionalDetail());
    		entity.setPersonalDetail(userDetail.getPersonalDetail());
    		
	    	save(entity);
	    	
	    	if(StringUtils.isNotBlank(entity.getProfessionalDetail().getPanNumber())) {
    			
    			
    			Thread t = new Thread() {
    				public void run() {
    					try {
    						interactWithTPSystems(entity.getId());
    					} catch (Exception e) {
    						e.printStackTrace();
    					}
    				}
    			};
    			t.start();
    		}
	    	
	    	return entity;
    }
    
    public void interactWithTPSystems(Long userDetailId) throws IOException {
    		//breDAO.invokeBRE(null,null);
		UserDetail entity = findById(userDetailId);
		
		if(!entity.getProfessionalDetail().getPanVerified()) {
			PanRequest panRequest = new PanRequest();
			panRequest.setValue(entity.getProfessionalDetail().getPanNumber());
			PanResponse panResponse = panDAO.verifyPAN(panRequest);
			if(panResponse.isResponseOK()) {
				entity.getProfessionalDetail().setPanVerified(true);
				save(entity);
			}
		}
		
		if(entity.getProfessionalDetail().getPanVerified()) {
			//Skip first CP CALL if CP ref. number already exists.
			if(StringUtils.isBlank( entity.getCpReferanceNumber())) {
				createCPAccount(entity);
				save(entity);
				//timeout for waiting 30 secs for second CP call
				try {
					Thread.sleep(31000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//verify CIBIL
			FetchOutputNewApplicationXML fetchOutputNewApplicationXML = connectPlusDAO.fetchNewApplicationXML(entity.getCpReferanceNumber());
			//invoke BRE
			InvokeBREResponse invokeBREResponse = breDAO.invokeBRE(fetchOutputNewApplicationXML,entity);
			String breStatus = extractStatus(invokeBREResponse.getInvokeBREReturn());
			entity.setBreStatus(breStatus);
			save(entity);
			
			String leadKey = createCrmEntity(entity.getId());
			
			entity.setCrmLeadId(leadKey);
			save(entity);
			
			applicaitonNotification(entity, breStatus, leadKey);
			
		}
		
	}

	public String createCrmEntity(Long userDetailId) {
		UserDetail entity = findById(userDetailId);
		//TODO update status to user detail
		String leadKey = crmNextDAO.createSave(entity);
		//TODO update lead id in user detail
		return leadKey;
	}

	private void applicaitonNotification(UserDetail entity, String breStatus, String leadKey) {
		Map<String,String> data = new HashMap<>();
		data.put("refno", leadKey);//TODO confirm if ref number to be sent is lead id or not. 
		data.put("amt", String.valueOf(entity.getLoanAmount().intValue()));
		data.put("name", entity.getName());
		data.put("interestrate", String.valueOf(entity.getCity().getTier().getMaxFees()));
		data.put("tenure", String.valueOf(entity.getProfession().getLoanDuration().getMax()));
		data.put("pan", entity.getProfessionalDetail().getPanNumber());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		
		data.put("statementend", DateUtil.getMonthYearString(cal.getTime()));
		cal.add(Calendar.MONTH, -3);
		data.put("statementstart", DateUtil.getMonthYearString(cal.getTime()));
		
		data.put("month3", DateUtil.getMonthYearString(cal.getTime()));
		cal.add(Calendar.MONTH, 1);
		data.put("month2", DateUtil.getMonthYearString(cal.getTime()));
		cal.add(Calendar.MONTH, 1);
		data.put("month1", DateUtil.getMonthYearString(cal.getTime()));
			
		// depending on the status of call, appropriate SMS template to be selected.
		if(BRE_STATUS_GREEN.equalsIgnoreCase(breStatus)) {
			smsService.send(entity.getMobile(), smsTemplateService.getFormattedText(2l,data ));
			EmailTemplate emailTemplate = emailTemplateService.getById(2l);
			smtpMailSender.send(entity.getEmail(),emailTemplate.getSubject(), emailTemplateService.getFormattedText(emailTemplate ,data ));
		} else if (BRE_STATUS_AMBER.equalsIgnoreCase(breStatus)) {
			smsService.send(entity.getMobile(), smsTemplateService.getFormattedText(3l, data));
			EmailTemplate emailTemplate = emailTemplateService.getById(2l);
			smtpMailSender.send(entity.getEmail(),emailTemplate.getSubject(), emailTemplateService.getFormattedText(emailTemplate ,data ));
		} else if (BRE_STATUS_RED.equalsIgnoreCase(breStatus)) {
			smsService.send(entity.getMobile(), smsTemplateService.getFormattedText(4l, data));
		}
	}
    
    private String extractStatus(String invokeBREResponse){
    	
    		int indexOfOutputVariables = invokeBREResponse.indexOf("<outputVariables>");
    		if(indexOfOutputVariables > 0) {
    			String statusBeginStr = invokeBREResponse.substring(indexOfOutputVariables);
    			int indexOfRiskStatus = statusBeginStr.indexOf("<riskStatus>");
    			if(indexOfRiskStatus > 0) {
    				return statusBeginStr.substring(indexOfRiskStatus + "<riskStatus>".length(), statusBeginStr.indexOf("</riskStatus>"));
    			}
    		}
    		
    	
    		return null;
    }
    
    @Override
    @Transactional
    public Boolean verifyOtp(Long userDetailId, OtpRequest otp) {
    	 	UserDetail userDetail = findById(userDetailId);
    	 	
    	 	OtpRequest otpRequestToBeSent = createOtpRequest(userDetail);
    	 	otpRequestToBeSent.setOtp(otp.getOtp());
    	 	Boolean otpStatus = false;
    	 	try {
    	 			otpStatus = otpDAO.verifyOTP(otpRequestToBeSent).isResponseOK();
			} catch (Exception e) {
				log.error("Error while requesting OTP", e);
			}
    	 	
    	 	if(otpStatus) {
    	 		userDetail.setUserVerified(otpStatus);//update db with status
    	 		save(userDetail);
    	 	}
    	 	return otpStatus;
    	 	
    }

    @Transactional
    public UserDetail createCPAccount(Long userDetailId) {
    		UserDetail userDetail = findById(userDetailId);
    		createCPAccount(userDetail);
		return userDetail;
	}
    
    public FetchOutputNewApplicationXML fetchNewApplicationXML(Long userDetailId) {
		UserDetail userDetail = findById(userDetailId);
		FetchOutputNewApplicationXML fetchOutputNewApplicationXML = connectPlusDAO.fetchNewApplicationXML(userDetail.getCpReferanceNumber());
		return fetchOutputNewApplicationXML;
    }

    
	private void createCPAccount(UserDetail userDetail) {
		try {
			String ticketNumber = connectPlusDAO.createNewAccount(userDetail);
			userDetail.setCpReferanceNumber(ticketNumber);
			save(userDetail);
		} catch (Exception e) {
			log.error("Error while requesting OTP", e);
		}
		
	}
    
    @Override
    public Boolean resendOtp(Long userDetailId) {
    	 	UserDetail userDetail = getById(userDetailId);
    		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					otpDAO.sendOTP(createOtpRequest(userDetail));
				} catch (Exception e) {
					log.error("Error while requesting OTP", e);
				}
			}
		};
		t.start();
    	 	return true;
    }
    
    @Override
    public Boolean generateAadharOtp(Long userDetailId) {
    	 	UserDetail userDetail = getById(userDetailId);
    		 
		try {
			AadhaarOtpResponse otpResponse = aadhaarDAO.sendOTP(createAadharOtpRequest(userDetail));
			userDetail.setAadhaarTxId(otpResponse.getTransactionId());
			save(userDetail);
			//if aadhaar otp set successfully, response we get is with error = false
			return ("false".equalsIgnoreCase(otpResponse.getError()));
			
		} catch (Exception e) {
			log.error("Error while requesting OTP", e);
		}
	  
    	 	return false;
    }
    
    @Override
    public Boolean verifyAadhaarOtp(Long userDetailId, AadhaarOtpKeyRequest otpRequestToBeSent) {
    	 	UserDetail userDetail = findById(userDetailId);
    	 	
    	 	updateAadharOtpKeyRequest(userDetail, otpRequestToBeSent);
    	 	
    	 	Boolean otpStatus = false;
		try {
			AadhaarOtpKeyResponse aadhaarOtpKeyResponse = aadhaarDAO.verifyOTP(otpRequestToBeSent);
			otpStatus = "false".equalsIgnoreCase(aadhaarOtpKeyResponse.getError());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	
    	 	userDetail.setAadhaarVerified(otpStatus);
    	 	save(userDetail);
    	 	return otpStatus;
    }
    
    
    public void save(UserDetail userDetail) {
    		workAddressService.save(userDetail.getProfessionalDetail().getWorkAddress());
    		
    		professionalDetailService.save(userDetail.getProfessionalDetail());
    		
    		if(userDetail.getPersonalDetail() != null) {
    			personalDetailService.save(userDetail.getPersonalDetail());
    		}
    		super.save(userDetail);
    }
    
    private OtpRequest createOtpRequest(UserDetail userDetail) {
	    	OtpRequest otpRequest = new OtpRequest();
	    	
	    	otpRequest.setMobileNumber(userDetail.getMobile());
	    	otpRequest.setEmailId(userDetail.getEmail());
	    	otpRequest.setUniqueRefernceNumber(""+userDetail.getId() + DateUtil.getDateCPString(userDetail.getCreatedOn()));
	    	otpRequest.setUserId("OLP"+userDetail.getId());
	    	otpRequest.setDeviceCreatedOn(""+userDetail.getId() + DateUtil.getDateCPString(userDetail.getCreatedOn()));

    		return otpRequest;
    }
    
    private AadhaarOtpRequest createAadharOtpRequest(UserDetail userDetail) {
    		AadhaarOtpRequest otpRequest = new AadhaarOtpRequest();
    		otpRequest.setUid(userDetail.getAadhaarNumber());
		return otpRequest;
    }
    
    private AadhaarOtpKeyRequest updateAadharOtpKeyRequest(UserDetail userDetail, final AadhaarOtpKeyRequest otpKeyRequest) {
	    	otpKeyRequest.setOtpTxn(userDetail.getAadhaarTxId());
	    	otpKeyRequest.setUid(userDetail.getAadhaarNumber());
		
		return otpKeyRequest;
    }
    
    public Long getTotalApplicationsCount(){
		return userDetailRepository.getTotalApplicationsCount();
	}

	public List<StateWiseCount> getStatewiseTotalApplicationsCount() {
		List<StateWiseCount> listToReturn = new ArrayList<>();
		List<Map<String, Object>> statewiseTotalApplicationsCount = userDetailRepository.getStatewiseTotalApplicationsCount();
		for (Map<String, Object> map : statewiseTotalApplicationsCount) {
			listToReturn.add(new StateWiseCount(map.get("key").toString(), map.get("value").toString()));
		}
		return listToReturn;
	}

    
	public Long getTotalDisbursementCount() {
		return userDetailRepository.getTotalDisbursementCount();
	}

	public Long getTotalInprogressCount() {
		return userDetailRepository.getTotalInprogressCount();
	}

	public Long getTotalLeadsCount() {
		return userDetailRepository.getTotalLeadsCount();
	}
    
	public Long getTotalApplicationsCount(Long professionId) {
		return userDetailRepository.getTotalApplicationsCount(professionId);
	}
	
	public List<StateWiseCount> getStatewiseTotalApplicationsCount(Long professionId) {
		List<StateWiseCount> listToReturn = new ArrayList<>();
		List<Map<String, Object>> statewiseTotalApplicationsCount = userDetailRepository.getStatewiseTotalApplicationsCount(professionId);
		for (Map<String, Object> map : statewiseTotalApplicationsCount) {
			listToReturn.add(new StateWiseCount(map.get("key").toString(), map.get("value").toString()));
		}
		return listToReturn;
	}
	
	public Long getTotalDisbursementCount(Long professionId) {
		return userDetailRepository.getTotalDisbursementCount(professionId);
	}
	public Long getTotalInprogressCount(Long professionId) {
		return userDetailRepository.getTotalInprogressCount(professionId);
	}
	public Long getTotalLeadsCount(Long professionId) {
		return userDetailRepository.getTotalLeadsCount(professionId);
	}
    
    /**
     * Find and return all userDetails
     * @return collection of all userDetails
     */
    @Override
    public Collection<UserDetail> findAll() {
        Collection<UserDetail> userDetails = userDetailRepository.find(new UserDetail(), new SearchTemplate());
        return userDetails;
    }

    @Override
    public UserDetail findById(Long id) {
		UserDetail userDetail = userDetailRepository.findOne(id);
        return userDetail;
    }
	
	@Override
	public GenericDao<UserDetail, Long> getDao() {
		// TODO Auto-generated method stub
		return userDetailRepository;
	}

	@Override
	public UserDetail getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetail getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}