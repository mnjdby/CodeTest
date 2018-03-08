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
import com.fullerton.olp.model.Qualification;
import com.fullerton.olp.service.QualificationService;

@Controller
@RequestMapping("qualification")
public class QualificationController extends BaseController{
    
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private QualificationService qualificationService;
    
    @RequestMapping(value = {"/{pk}","/{pk}/"})
    @ResponseBody
    public ResponseEntity<Qualification>   get(@PathVariable("pk") Qualification qualification) {
        return new ResponseEntity<Qualification>(qualification.copy(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{pk}", method = POST)
    @ResponseBody
    public ResponseEntity<Qualification> update(@Valid Qualification qualification, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<Qualification>(new Qualification(), HttpStatus.NOT_ACCEPTABLE);
    	}
        qualificationService.save(qualification);
        return new ResponseEntity<Qualification>(qualification.copy(), HttpStatus.OK);
    }

    //Create Qualification
    @RequestMapping(value = "/", method = POST)
    @ResponseBody
    public ResponseEntity<Qualification> create(@Valid Qualification qualification, BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<Qualification>(new Qualification(), HttpStatus.NOT_ACCEPTABLE);
    	}
        qualificationService.save(qualification);
        return new ResponseEntity<Qualification>(qualification.copy(), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = {"/",""}, method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(Qualification qualification, SearchParameters searchParameters) {
    	
        List<Qualification> qualifications = qualificationService.find(qualification, searchParameters.toSearchTemplate());
        List<Qualification> ret = new ArrayList<Qualification>();
        for (Qualification _qualification : qualifications) {
            ret.add(_qualification.copy());
        }
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", qualifications.size());
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") Qualification qualification) {
        qualificationService.delete(qualification);
        return true;
    }
}