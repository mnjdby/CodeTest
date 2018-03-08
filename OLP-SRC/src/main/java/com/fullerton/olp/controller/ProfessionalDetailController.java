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
import com.fullerton.olp.model.ProfessionalDetail;
import com.fullerton.olp.service.ProfessionalDetailService;

@Controller
@RequestMapping("professionalDetail")
public class ProfessionalDetailController extends BaseController{
    
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private ProfessionalDetailService professionalDetailService;
    
    @RequestMapping(value = "/{pk}")
    @ResponseBody
    public ProfessionalDetail get(@PathVariable("pk") ProfessionalDetail professionalDetail) {
        return professionalDetail;
    }
    
    @RequestMapping(value = "/{pk}", method = POST)
    @ResponseBody
    public ResponseEntity<ProfessionalDetail> update(@Valid ProfessionalDetail professionalDetail, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<ProfessionalDetail>(new ProfessionalDetail(), HttpStatus.NOT_ACCEPTABLE);
    	}
        professionalDetailService.save(professionalDetail);
        return new ResponseEntity<ProfessionalDetail>(professionalDetail.copy(), HttpStatus.OK);
    }

    //Create ProfessionalDetail
    @RequestMapping(value = "/", method = POST)
    @ResponseBody
    public ResponseEntity<ProfessionalDetail> create(@Valid ProfessionalDetail professionalDetail, BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<ProfessionalDetail>(new ProfessionalDetail(), HttpStatus.NOT_ACCEPTABLE);
    	}
        professionalDetailService.save(professionalDetail);
        return new ResponseEntity<ProfessionalDetail>(professionalDetail.copy(), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/", method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(ProfessionalDetail professionalDetail, SearchParameters searchParameters) {
    	
        List<ProfessionalDetail> professionalDetails = professionalDetailService.find(professionalDetail, searchParameters.toSearchTemplate());
        List<ProfessionalDetail> ret = new ArrayList<ProfessionalDetail>();
        for (ProfessionalDetail _professionalDetail : professionalDetails) {
            ret.add(_professionalDetail.copy());
        }
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", professionalDetails.size());
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") ProfessionalDetail professionalDetail) {
        professionalDetailService.delete(professionalDetail);
        return true;
    }
}