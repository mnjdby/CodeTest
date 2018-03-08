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
import com.fullerton.olp.model.Pincode;
import com.fullerton.olp.service.PincodeService;

@Controller
@RequestMapping("pincode")
public class PincodeController extends BaseController{
    
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private PincodeService pincodeService;
    
    @RequestMapping(value = "/{pk}")
    @ResponseBody
    public Pincode get(@PathVariable("pk") Pincode pincode) {
        return pincode;
    }
    
    @RequestMapping(value = "/{pk}", method = POST)
    @ResponseBody
    public ResponseEntity<Pincode> update(@Valid Pincode pincode, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<Pincode>(new Pincode(), HttpStatus.NOT_ACCEPTABLE);
    	}
        pincodeService.save(pincode);
        return new ResponseEntity<Pincode>(pincode.copy(), HttpStatus.OK);
    }

    //Create Pincode
    @RequestMapping(value = "/", method = POST)
    @ResponseBody
    public ResponseEntity<Pincode> create(@Valid Pincode pincode, BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<Pincode>(new Pincode(), HttpStatus.NOT_ACCEPTABLE);
    	}
        pincodeService.save(pincode);
        return new ResponseEntity<Pincode>(pincode.copy(), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/", method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(Pincode pincode, SearchParameters searchParameters) {
    	
        List<Pincode> pincodes = pincodeService.find(pincode, searchParameters.toSearchTemplate());
        List<Pincode> ret = new ArrayList<Pincode>();
        for (Pincode _pincode : pincodes) {
            ret.add(_pincode.copy());
        }
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", pincodes.size());
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") Pincode pincode) {
        pincodeService.delete(pincode);
        return true;
    }

}