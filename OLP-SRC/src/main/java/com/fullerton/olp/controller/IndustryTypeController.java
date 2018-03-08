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
import com.fullerton.olp.model.IndustryType;
import com.fullerton.olp.service.IndustryTypeService;

@Controller
@RequestMapping("industryType")
public class IndustryTypeController extends BaseController{
    
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private IndustryTypeService industryTypeService;
    
    @RequestMapping(value = "/{pk}")
    @ResponseBody
    public IndustryType get(@PathVariable("pk") IndustryType industryType) {
        return industryType;
    }
    
    @RequestMapping(value = "/{pk}", method = POST)
    @ResponseBody
    public ResponseEntity<IndustryType> update(@Valid IndustryType industryType, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<IndustryType>(new IndustryType(), HttpStatus.NOT_ACCEPTABLE);
    	}
        industryTypeService.save(industryType);
        return new ResponseEntity<IndustryType>(industryType.copy(), HttpStatus.OK);
    }

    //Create IndustryType
    @RequestMapping(value = "/", method = POST)
    @ResponseBody
    public ResponseEntity<IndustryType> create(@Valid IndustryType industryType, BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<IndustryType>(new IndustryType(), HttpStatus.NOT_ACCEPTABLE);
    	}
        industryTypeService.save(industryType);
        return new ResponseEntity<IndustryType>(industryType.copy(), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/", method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(IndustryType industryType, SearchParameters searchParameters) {
    	
        List<IndustryType> industryTypes = industryTypeService.find(industryType, searchParameters.toSearchTemplate());
        List<IndustryType> ret = new ArrayList<IndustryType>();
        for (IndustryType _industryType : industryTypes) {
            ret.add(_industryType.copy());
        }
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", industryTypes.size());
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") IndustryType industryType) {
        industryTypeService.delete(industryType);
        return true;
    }
}