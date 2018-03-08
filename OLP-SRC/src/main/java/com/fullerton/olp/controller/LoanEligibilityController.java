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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fullerton.olp.controller.util.SearchParameters;
import com.fullerton.olp.model.City;
import com.fullerton.olp.model.LoanEligibility;
import com.fullerton.olp.service.LoanEligibilityService;

@Controller
@RequestMapping("loanEligibility")
public class LoanEligibilityController extends BaseController{
	private static String STATUS_ACTIVE="ACTIVE";
	private static String STATUS_INACTIVE="INACTIVE";
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private LoanEligibilityService loanEligibilityService;
    
    @RequestMapping(value = "/{pk}")
    @ResponseBody
    public LoanEligibility get(@PathVariable("pk") LoanEligibility loanEligibility) {
        return loanEligibility;
    }
    
    @RequestMapping(value = "/{pk}", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<LoanEligibility> update(@Valid LoanEligibility loanEligibility, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<LoanEligibility>(new LoanEligibility(), HttpStatus.NOT_ACCEPTABLE);
    	}
        loanEligibilityService.save(loanEligibility);
        return new ResponseEntity<LoanEligibility>(loanEligibility.copy(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{pk}/{status}", method = POST)
    @ResponseBody
    public ResponseEntity<LoanEligibility> update(@PathVariable(value="pk") Long id,@PathVariable(value="status") String status) {
    	logger.info("ID:->"+id);
    	logger.info("Status:->"+status);
    	if(id<1 || (status==null || !(status.trim().equalsIgnoreCase(STATUS_ACTIVE) || status.trim().equalsIgnoreCase(STATUS_INACTIVE)))){
    		return new ResponseEntity<LoanEligibility>(new LoanEligibility(), HttpStatus.NOT_ACCEPTABLE);
    	}
    	
    	LoanEligibility loanEligibility = loanEligibilityService.findById(id);
    	loanEligibility.setActive(status.trim().equalsIgnoreCase(STATUS_ACTIVE)?true:false);
    	loanEligibilityService.save(loanEligibility);
        //return new ResponseEntity<City>(city, HttpStatus.OK);
    	return new ResponseEntity<LoanEligibility>(loanEligibility.copy(), HttpStatus.OK);
    }

    //Create LoanEligibility
    @RequestMapping(value = "/", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<LoanEligibility> create(@Valid LoanEligibility loanEligibility, BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<LoanEligibility>(new LoanEligibility(), HttpStatus.NOT_ACCEPTABLE);
    	}
        loanEligibilityService.save(loanEligibility);
        return new ResponseEntity<LoanEligibility>(loanEligibility.copy(), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = { "/", ""}, method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(LoanEligibility loanEligibility, SearchParameters searchParameters) {
    	if(searchParameters.getPageSize()==0){
    		searchParameters.setPageSize(600);//TODO remove this hard limit, use autocomplete if possible
    	}
    		
    	logger.info("Search LoanEligibility :->"+loanEligibility);
    	logger.info("Search Parameters :->"+searchParameters);
        List<LoanEligibility> loanEligibilties = loanEligibilityService.find(loanEligibility, searchParameters.toSearchTemplate());
        List<LoanEligibility> ret = new ArrayList<LoanEligibility>();
        for (LoanEligibility _loanEligibility : loanEligibilties) {
            ret.add(_loanEligibility.copy());
        }
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", loanEligibilityService.findCount(loanEligibility, searchParameters.toSearchTemplate()));
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = {"/{tierId}/{qualificationId}", "/{tierId}/{qualificationId}/"}, method = GET)
    @ResponseBody
    public ResponseEntity<LoanEligibility> list(@PathVariable Long tierId, @PathVariable Long qualificationId) {
    	
        LoanEligibility loanEligibility = loanEligibilityService.findByTierAndQualification(tierId, qualificationId);
        return new ResponseEntity<LoanEligibility>(loanEligibility.copy(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") LoanEligibility loanEligibility) {
        loanEligibilityService.delete(loanEligibility);
        return true;
    }

}