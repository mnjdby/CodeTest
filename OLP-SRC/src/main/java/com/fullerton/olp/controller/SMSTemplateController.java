package com.fullerton.olp.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fullerton.olp.controller.util.SearchParameters;
import com.fullerton.olp.model.SMSTemplate;
import com.fullerton.olp.service.SMSTemplateService;

@Controller
@RequestMapping("sms")
//@PermitAllConsumers
public class SMSTemplateController extends BaseController{
	
	private static String STATUS_ACTIVE="ACTIVE";
	private static String STATUS_INACTIVE="INACTIVE";
    
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private SMSTemplateService smsTemplateService;
    
    @RequestMapping(value = "/{pk}")
    @ResponseBody
    public SMSTemplate get(@PathVariable("pk") SMSTemplate smsTemplate) {
        return smsTemplate.copy();
    }
    
    @RequestMapping(value = "/{pk}", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SMSTemplate> update(@RequestBody @Valid SMSTemplate smsTemplate, BindingResult bindingResult) {
    	logger.info("SMSTemplate:->"+smsTemplate);
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<SMSTemplate>(new SMSTemplate(), HttpStatus.NOT_ACCEPTABLE);
    	}
    	smsTemplateService.save(smsTemplate);
    	smsTemplate = smsTemplateService.find(smsTemplate).get(0);
        return new ResponseEntity<SMSTemplate>(smsTemplate.copy(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{pk}/{status}", method = POST)
    @ResponseBody
    public ResponseEntity<SMSTemplate> update(@PathVariable(value="pk") Long id,@PathVariable(value="status") String status) {
    	logger.info("ID:->"+id);
    	logger.info("Status:->"+status);
    	if(id<1 || (status==null || !(status.trim().equalsIgnoreCase(STATUS_ACTIVE) || status.trim().equalsIgnoreCase(STATUS_INACTIVE)))){
    		return new ResponseEntity<SMSTemplate>(new SMSTemplate(), HttpStatus.NOT_ACCEPTABLE);
    	}
    	
    	SMSTemplate smsTemplate = smsTemplateService.findById(id);
    	smsTemplate.setActive(status.trim().equalsIgnoreCase(STATUS_ACTIVE)?true:false);
    	smsTemplateService.save(smsTemplate);
        return new ResponseEntity<SMSTemplate>(smsTemplate.copy(), HttpStatus.OK);
    }

    //Create SMSTemplate
    @RequestMapping(value = "/", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SMSTemplate> create(@RequestBody @Valid SMSTemplate smsTemplate, BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<SMSTemplate>(new SMSTemplate(), HttpStatus.NOT_ACCEPTABLE);
    	}
    	smsTemplateService.save(smsTemplate);
        logger.info(" SMSTemplate:->"+smsTemplate);
    	//smsTemplate = smsTemplateService.find(smsTemplate).get(0);
        //logger.info("POST SMSTemplate:->"+smsTemplate);
        
        return new ResponseEntity<SMSTemplate>(smsTemplate.copy(), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = { "/", ""}, method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list( SMSTemplate smsTemplate,  SearchParameters searchParameters) {
    	if(searchParameters.getPageSize()==0){
    		searchParameters.setPageSize(600);//TODO remove this hard limit, use autocomplete if possible
    	}
    		
    	logger.info("Search SMSTemplate :->"+smsTemplate);
    	logger.info("Search Parameters :->"+searchParameters);
        List<SMSTemplate> smsTemplates = smsTemplateService.find(smsTemplate, searchParameters.toSearchTemplate());
        List<SMSTemplate> ret = new ArrayList<SMSTemplate>();
        for (SMSTemplate _smsTemplate : smsTemplates) {
            ret.add(_smsTemplate.copy());
        }
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", smsTemplateService.findCount(smsTemplate, searchParameters.toSearchTemplate()));
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") SMSTemplate smsTemplate) {
    	smsTemplateService.delete(smsTemplate);
        return true;
    }

}