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
import com.fullerton.olp.model.ResidenceAddress;
import com.fullerton.olp.service.ResidenceAddressService;

@Controller
@RequestMapping("residenceAddress")
public class ResidenceAddressController extends BaseController{
    
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private ResidenceAddressService residenceAddressService;
    
    @RequestMapping(value = "/{pk}")
    @ResponseBody
    public ResidenceAddress get(@PathVariable("pk") ResidenceAddress residenceAddress) {
        return residenceAddress;
    }
    
    @RequestMapping(value = "/{pk}", method = POST)
    @ResponseBody
    public ResponseEntity<ResidenceAddress> update(@Valid ResidenceAddress residenceAddress, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<ResidenceAddress>(new ResidenceAddress(), HttpStatus.NOT_ACCEPTABLE);
    	}
        residenceAddressService.save(residenceAddress);
        return new ResponseEntity<ResidenceAddress>(residenceAddress.copy(), HttpStatus.OK);
    }

    //Create ResidenceAddress
    @RequestMapping(value = "/", method = POST)
    @ResponseBody
    public ResponseEntity<ResidenceAddress> create(@Valid ResidenceAddress residenceAddress, BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<ResidenceAddress>(new ResidenceAddress(), HttpStatus.NOT_ACCEPTABLE);
    	}
        residenceAddressService.save(residenceAddress);
        return new ResponseEntity<ResidenceAddress>(residenceAddress.copy(), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/", method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(ResidenceAddress residenceAddress, SearchParameters searchParameters) {
    	
        List<ResidenceAddress> residenceAddresses = residenceAddressService.find(residenceAddress, searchParameters.toSearchTemplate());
        List<ResidenceAddress> ret = new ArrayList<ResidenceAddress>();
        for (ResidenceAddress _residenceAddress : residenceAddresses) {
            ret.add(_residenceAddress.copy());
        }
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", residenceAddresses.size());
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") ResidenceAddress residenceAddress) {
        residenceAddressService.delete(residenceAddress);
        return true;
    }
}