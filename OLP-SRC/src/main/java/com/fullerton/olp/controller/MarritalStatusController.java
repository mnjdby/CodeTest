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
import com.fullerton.olp.model.MarritalStatus;
import com.fullerton.olp.service.MarritalStatusService;

@Controller
@RequestMapping("marritalStatus")
public class MarritalStatusController extends BaseController{
    
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private MarritalStatusService marritalStatusService;
    
    @RequestMapping(value = "/{pk}")
    @ResponseBody
    public MarritalStatus get(@PathVariable("pk") MarritalStatus marritalStatus) {
        return marritalStatus;
    }
    
    @RequestMapping(value = "/{pk}", method = POST)
    @ResponseBody
    public ResponseEntity<MarritalStatus> update(@Valid MarritalStatus marritalStatus, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<MarritalStatus>(new MarritalStatus(), HttpStatus.NOT_ACCEPTABLE);
    	}
        marritalStatusService.save(marritalStatus);
        return new ResponseEntity<MarritalStatus>(marritalStatus.copy(), HttpStatus.OK);
    }

    //Create MarritalStatus
    @RequestMapping(value = "/", method = POST)
    @ResponseBody
    public ResponseEntity<MarritalStatus> create(@Valid MarritalStatus marritalStatus, BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<MarritalStatus>(new MarritalStatus(), HttpStatus.NOT_ACCEPTABLE);
    	}
        marritalStatusService.save(marritalStatus);
        return new ResponseEntity<MarritalStatus>(marritalStatus.copy(), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = {"/",""}, method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(MarritalStatus marritalStatus, SearchParameters searchParameters) {
    	
        List<MarritalStatus> marritalStatusList = marritalStatusService.find(marritalStatus, searchParameters.toSearchTemplate());
        List<MarritalStatus> ret = new ArrayList<MarritalStatus>();
        for (MarritalStatus _marritalStatus : marritalStatusList) {
            ret.add(_marritalStatus.copy());
        }
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", marritalStatusList.size());
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") MarritalStatus marritalStatus) {
        marritalStatusService.delete(marritalStatus);
        return true;
    }

}