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
import com.fullerton.olp.model.ResidenceType;
import com.fullerton.olp.service.ResidenceTypeService;

@Controller
@RequestMapping("residenceType")
public class ResidenceTypeController extends BaseController{
    
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private ResidenceTypeService residenceTypeService;
    
    @RequestMapping(value = "/{pk}")
    @ResponseBody
    public ResidenceType get(@PathVariable("pk") ResidenceType residenceType) {
        return residenceType;
    }
    
    @RequestMapping(value = "/{pk}", method = POST)
    @ResponseBody
    public ResponseEntity<ResidenceType> update(@Valid ResidenceType residenceType, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<ResidenceType>(new ResidenceType(), HttpStatus.NOT_ACCEPTABLE);
    	}
        residenceTypeService.save(residenceType);
        return new ResponseEntity<ResidenceType>(residenceType.copy(), HttpStatus.OK);
    }

    //Create ResidenceType
    @RequestMapping(value = "/", method = POST)
    @ResponseBody
    public ResponseEntity<ResidenceType> create(@Valid ResidenceType residenceType, BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<ResidenceType>(new ResidenceType(), HttpStatus.NOT_ACCEPTABLE);
    	}
        residenceTypeService.save(residenceType);
        return new ResponseEntity<ResidenceType>(residenceType.copy(), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = {"/",""}, method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(ResidenceType residenceType, SearchParameters searchParameters) {
    	
        List<ResidenceType> residenceTypes = residenceTypeService.find(residenceType, searchParameters.toSearchTemplate());
        List<ResidenceType> ret = new ArrayList<ResidenceType>();
        for (ResidenceType _residenceType : residenceTypes) {
            ret.add(_residenceType.copy());
        }
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", residenceTypes.size());
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") ResidenceType residenceType) {
        residenceTypeService.delete(residenceType);
        return true;
    }
}