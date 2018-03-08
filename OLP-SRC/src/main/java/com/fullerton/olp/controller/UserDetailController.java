package com.fullerton.olp.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullerton.olp.bean.AadhaarOtpKeyRequest;
import com.fullerton.olp.bean.OtpRequest;
import com.fullerton.olp.controller.util.SearchParameters;
import com.fullerton.olp.model.ApplicationStatus;
import com.fullerton.olp.model.DocumentType;
import com.fullerton.olp.model.UserDetail;
import com.fullerton.olp.model.UserDocument;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.service.DocumentTypeService;
import com.fullerton.olp.service.UserDetailService;
import com.fullerton.olp.wsdao.cp.model.FetchOutputNewApplicationXML;

@Controller
@RequestMapping("userDetail")
public class UserDetailController extends BaseController{
    
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private UserDetailService userDetailService;
    
    @Autowired
    private DocumentTypeService documentTypeService;
    
    @RequestMapping(value = { "/{pk}", "/{pk}/"})
    @ResponseBody
    public ResponseEntity<UserDetail> get(@PathVariable("pk") UserDetail userDetail) {
        return new ResponseEntity<UserDetail>(userDetail.copy(), HttpStatus.OK);
    }
    
    @RequestMapping(value = {"/{pk}","/{pk}/"}, method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserDetail> update(@RequestBody @Valid UserDetail userDetail, BindingResult bindingResult) {
	    	if(bindingResult.hasErrors()){
	    		return new ResponseEntity<UserDetail>(userDetail, HttpStatus.NOT_ACCEPTABLE);
	    	}
        userDetailService.save(userDetail);
       
        return new ResponseEntity<UserDetail>(userDetailService.findById(userDetail.getId()).copy(), HttpStatus.OK);
    }
    
    @RequestMapping(value = {"/{pk}/updateaadhaar","/{pk}/updateaadhaar/"}, method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserDetail> updateAadhaar(@PathVariable(value="pk") Long userDetailId, @RequestBody @Valid UserDetail userDetail, BindingResult bindingResult) {
	    	if(StringUtils.isEmpty(userDetail.getAadhaarNumber())){
	    		return new ResponseEntity<UserDetail>(new UserDetail(), HttpStatus.NOT_ACCEPTABLE);
	    	}
	    	UserDetail updatedUserDetail = userDetailService.saveAadhaar(userDetailId, userDetail);
        return new ResponseEntity<UserDetail>(updatedUserDetail.copy(), HttpStatus.OK);
    }
    
    @RequestMapping(value = {"/{pk}/updateloanamount","/{pk}/updateloanamount/"}, method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserDetail> updateLoanAmount(@PathVariable(value="pk") Long userDetailId, @RequestBody @Valid UserDetail userDetail, BindingResult bindingResult) {
	    	if(userDetail.getLoanAmount() == null || userDetail.getLoanAmount() == 0){
	    		return new ResponseEntity<UserDetail>(new UserDetail(), HttpStatus.NOT_ACCEPTABLE);
	    	}
	    	UserDetail updatedUserDetail = userDetailService.saveLoanAmount(userDetailId, userDetail);
        return new ResponseEntity<UserDetail>(updatedUserDetail.copy(), HttpStatus.OK);
    }
    
    @RequestMapping(value = {"/{pk}/dde","/{pk}/dde/"}, method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserDetail> updateDetailData(@RequestBody UserDetail userDetail, BindingResult bindingResult) {
	    	if(bindingResult.hasErrors()){
	    		return new ResponseEntity<UserDetail>(userDetail, HttpStatus.NOT_ACCEPTABLE);
	    	}
	    	

	    	if(!validatePan(userDetail)){
	    		return new ResponseEntity<UserDetail>(new UserDetail(), HttpStatus.ALREADY_REPORTED);
	    	}
	    
	    	userDetailService.saveDetailData(userDetail);
	    	return new ResponseEntity<UserDetail>(userDetailService.findById(userDetail.getId()).copy(), HttpStatus.OK);
    }
    
    //Create UserDetail
    @RequestMapping(value = {"/",""}, method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserDetail> create(@RequestBody @Valid UserDetail userDetail, BindingResult bindingResult, HttpServletRequest request) {
    	
	    	if(bindingResult.hasErrors()){
	    		return new ResponseEntity<UserDetail>(new UserDetail(), HttpStatus.NOT_ACCEPTABLE);
	    	}
	    	if(!validate(userDetail, request)){
	    		return new ResponseEntity<UserDetail>(new UserDetail(), HttpStatus.ALREADY_REPORTED);
	    	}
	    	userDetail.setApplicationStatus(new ApplicationStatus(1231l));//QDE CRM status
        userDetailService.save(userDetail);
        userDetailService.resendOtp(userDetail.getId());
        return new ResponseEntity<UserDetail>(userDetail.copy(), HttpStatus.CREATED);
    }
    
    private boolean validate(UserDetail userDetail, HttpServletRequest request){
		userDetail.setIpAddress(request.getRemoteHost());
		return !userDetailService.attemptedInThePast(userDetail);
    }
    private boolean validatePan(UserDetail userDetail){
		return !userDetailService.attemptedPanInThePast(userDetail);
    }

    
    
    @RequestMapping(value = {"/{pk}/verify","/{pk}/verify/"}, method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Boolean> verifyOtp(@PathVariable(value="pk") Long userDetailId, @RequestBody @Valid OtpRequest otpRequest, BindingResult bindingResult) {
	    
	    	if(bindingResult.hasErrors()){
	    		return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.NOT_ACCEPTABLE);
	    	}
        return new ResponseEntity<Boolean>(userDetailService.verifyOtp(userDetailId, otpRequest), HttpStatus.OK);
    }
    
    @RequestMapping(value = {"/{pk}/resendotp","/{pk}/resendotp/"}, method = GET)
    @ResponseBody
    public ResponseEntity<Boolean> resendOtp(@PathVariable(value="pk") Long userDetailId) {
	    
        return new ResponseEntity<Boolean>(userDetailService.resendOtp(userDetailId), HttpStatus.OK);
    }
    
    @RequestMapping(value = {"/{pk}/aadhaarotp","/{pk}/aadhaarotp/"}, method = GET)
    @ResponseBody
    public ResponseEntity<Boolean> aadharOtp(@PathVariable(value="pk") Long userDetailId) {
	    
        return new ResponseEntity<Boolean>(userDetailService.generateAadharOtp(userDetailId), HttpStatus.OK);
    }
    
    @RequestMapping(value = {"/{pk}/verifyaadhaar","/{pk}/verifyaadhaar/"}, method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Boolean> verifyAadhaarOtp(@PathVariable(value="pk") Long userDetailId, @RequestBody @Valid AadhaarOtpKeyRequest otpRequest, BindingResult bindingResult) {
	    
	    	if(bindingResult.hasErrors()){
	    		return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.NOT_ACCEPTABLE);
	    	}
        return new ResponseEntity<Boolean>(userDetailService.verifyAadhaarOtp(userDetailId, otpRequest), HttpStatus.OK);
    }
    
    @PostMapping("/{pk}/upload/{documentTypeId}/{documentId}")
    public ResponseEntity<Boolean> handleFileUpload(@PathVariable("pk") Long pk, @PathVariable("documentTypeId") Long documentTypeId, 
    		@PathVariable("documentId") Long documentId, MultipartFile file,
            RedirectAttributes redirectAttributes) {
        return new ResponseEntity<Boolean>(userDetailService.saveFile(pk, documentTypeId, documentId, file), HttpStatus.OK);
    }
    
    @RequestMapping(value = { "/{pk}/upload/", "/{pk}/upload"})
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getUploads(@PathVariable("pk") UserDetail userDetail) {
    	
    		List<DocumentType> documentTypes = documentTypeService.find(new DocumentType(), new SearchTemplate());
        List<DocumentType> ret = new ArrayList<DocumentType>();
        for (DocumentType _documentType : documentTypes) {
            ret.add(_documentType.copy());
        }	
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", documentTypes.size());
        result.put("value", ret);
       
        if(userDetail.getUserDocuments() != null){
        		for (UserDocument userDocument : userDetail.getUserDocuments()) {
        			if(userDocument.getActive()) {
        				for (DocumentType documentType : ret) {
        					if(documentType.getId().equals(userDocument.getDocumentType().getId())) {
        						documentType.setUploaded(true);
        					}
        				}
        			}
			}
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{pk}/favorite/{status}", method = POST)
    @ResponseBody
    public ResponseEntity<UserDetail> update(@PathVariable(value="pk") Long id,@PathVariable(value="status") Boolean status) {
	    	logger.info("ID:->"+id);
	    	logger.info("Status:->"+status);
	    	 
	    	
	    	UserDetail userDetail = userDetailService.findById(id);
	    	userDetail.setFaviorate(status);
	    	userDetailService.save(userDetail);
        //return new ResponseEntity<City>(city, HttpStatus.OK);
	    	return new ResponseEntity<UserDetail>(userDetail.copy(), HttpStatus.OK);
    }
    
    @RequestMapping(value = {"/",""}, method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(UserDetail userDetail, SearchParameters searchParameters) {
    	
        List<UserDetail> userDetails = userDetailService.find(userDetail, searchParameters.toSearchTemplate());
        List<UserDetail> ret = new ArrayList<UserDetail>();
        for (UserDetail _userDetail : userDetails) {
            ret.add(_userDetail.shortCopy());
        }
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", userDetailService.findCount(userDetail, searchParameters.toSearchTemplate()));
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") UserDetail userDetail) {
        userDetailService.delete(userDetail);
        return true;
    }
    
    
    //TESTING PURPOSE
    @RequestMapping(value = {"/{pk}/createCPAccount", "/{pk}/createCPAccount/"}, method = GET)
    @ResponseBody
    public UserDetail createCPAccount(@PathVariable("pk") Long userDetailId) {
    		return userDetailService.createCPAccount(userDetailId).copy();
    }
    
    @RequestMapping(value = {"/{pk}/fetchNewApplicationXML", "/{pk}/fetchNewApplicationXML/"}, method = GET)
    @ResponseBody
    public FetchOutputNewApplicationXML fetchNewApplicationXML(@PathVariable("pk") Long userDetailId) {
    		return userDetailService.fetchNewApplicationXML(userDetailId);
    }
    
    @RequestMapping(value = {"/{pk}/ddesync", "/{pk}/ddesync/"}, method = GET)
    @ResponseBody
    public ResponseEntity<Boolean> ddeSync(@PathVariable("pk") Long userDetailId) {
    	
    		try {
				userDetailService.interactWithTPSystems(userDetailId);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
			}
    		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
    
    @RequestMapping(value = {"/{pk}/crm", "/{pk}/crm/"}, method = GET)
    @ResponseBody
    public ResponseEntity<String> crmEntity(@PathVariable("pk") Long userDetailId) {
    		return new ResponseEntity<>(userDetailService.createCrmEntity(userDetailId), HttpStatus.OK);
    }
}