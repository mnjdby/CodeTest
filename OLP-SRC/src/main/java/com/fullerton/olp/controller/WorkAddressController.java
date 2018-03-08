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
import com.fullerton.olp.model.WorkAddress;
import com.fullerton.olp.service.WorkAddressService;

@Controller
@RequestMapping("workAddress")
public class WorkAddressController extends BaseController{
    
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private WorkAddressService workAddressService;
    
    @RequestMapping(value = "/{pk}")
    @ResponseBody
    public WorkAddress get(@PathVariable("pk") WorkAddress workAddress) {
        return workAddress;
    }
    
    @RequestMapping(value = "/{pk}", method = POST)
    @ResponseBody
    public ResponseEntity<WorkAddress> update(@Valid WorkAddress workAddress, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<WorkAddress>(new WorkAddress(), HttpStatus.NOT_ACCEPTABLE);
    	}
        workAddressService.save(workAddress);
        return new ResponseEntity<WorkAddress>(workAddress.copy(), HttpStatus.OK);
    }

    //Create WorkAddress
    @RequestMapping(value = "/", method = POST)
    @ResponseBody
    public ResponseEntity<WorkAddress> create(@Valid WorkAddress workAddress, BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<WorkAddress>(new WorkAddress(), HttpStatus.NOT_ACCEPTABLE);
    	}
        workAddressService.save(workAddress);
        return new ResponseEntity<WorkAddress>(workAddress.copy(), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/", method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(WorkAddress workAddress, SearchParameters searchParameters) {
    	
        List<WorkAddress> workAddresses = workAddressService.find(workAddress, searchParameters.toSearchTemplate());
        List<WorkAddress> ret = new ArrayList<WorkAddress>();
        for (WorkAddress _workAddress : workAddresses) {
            ret.add(_workAddress.copy());
        }
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", workAddresses.size());
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") WorkAddress workAddress) {
        workAddressService.delete(workAddress);
        return true;
    }
}