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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fullerton.olp.controller.util.SearchParameters;
import com.fullerton.olp.model.ApplicationStatus;
import com.fullerton.olp.service.ApplicationStatusService;

@Controller
@RequestMapping("applicationStatus")
public class ApplicationStatusController extends BaseController{
    
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private ApplicationStatusService applicationStatusService;
    
    @RequestMapping(value = "/{pk}")
    @ResponseBody
    public ApplicationStatus get(@PathVariable("pk") ApplicationStatus applicationStatus) {
        return applicationStatus;
    }
    
    //@RequestMapping(value = "/{pk}", method = POST)
    //@ResponseBody
    public ResponseEntity<ApplicationStatus> update(@Valid ApplicationStatus applicationStatus, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<ApplicationStatus>(new ApplicationStatus(), HttpStatus.NOT_ACCEPTABLE);
    	}
        applicationStatusService.save(applicationStatus);
        return new ResponseEntity<ApplicationStatus>(applicationStatus.copy(), HttpStatus.OK);
    }

    //Create ApplicationStatus
   // @RequestMapping(value = "/", method = POST)
    //@ResponseBody
    public ResponseEntity<ApplicationStatus> create(@Valid ApplicationStatus applicationStatus, BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<ApplicationStatus>(new ApplicationStatus(), HttpStatus.NOT_ACCEPTABLE);
    	}
        applicationStatusService.save(applicationStatus);
        return new ResponseEntity<ApplicationStatus>(applicationStatus.copy(), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = {"/",""}, method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(ApplicationStatus applicationStatus, SearchParameters searchParameters) {
    	
        List<ApplicationStatus> applicationStatusList = applicationStatusService.find(applicationStatus, searchParameters.toSearchTemplate());
        List<ApplicationStatus> ret = new ArrayList<ApplicationStatus>();
        for (ApplicationStatus _applicationStatus : applicationStatusList) {
            ret.add(_applicationStatus.copy());
        }
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", applicationStatusService.findCount(applicationStatus, searchParameters.toSearchTemplate()));
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") ApplicationStatus applicationStatus) {
        applicationStatusService.delete(applicationStatus);
        return true;
    }

}