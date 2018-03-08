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
import com.fullerton.olp.model.Company;
import com.fullerton.olp.model.LoanEligibility;
import com.fullerton.olp.service.CompanyService;

@Controller
@RequestMapping("company")
public class CompanyController extends BaseController{
    
	private static String STATUS_ACTIVE="ACTIVE";
	private static String STATUS_INACTIVE="INACTIVE";
	
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private CompanyService companyService;
    
    @RequestMapping(value = "/{pk}")
    @ResponseBody
    public Company get(@PathVariable("pk") Company company) {
        return company;
    }
    
    @RequestMapping(value = "/{pk}", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Company> update(@Valid Company company, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<Company>(new Company(), HttpStatus.NOT_ACCEPTABLE);
    	}
        companyService.save(company);
        return new ResponseEntity<Company>(company.copy(), HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/{pk}/{status}", method = POST)
    @ResponseBody
    public ResponseEntity<Company> update(@PathVariable(value="pk") Long id,@PathVariable(value="status") String status) {
    	logger.info("ID:->"+id);
    	logger.info("Status:->"+status);
    	if(id<1 || (status==null || !(status.trim().equalsIgnoreCase(STATUS_ACTIVE) || status.trim().equalsIgnoreCase(STATUS_INACTIVE)))){
    		return new ResponseEntity<Company>(new Company(), HttpStatus.NOT_ACCEPTABLE);
    	}
    	
    	Company company = companyService.findById(id);
    	company.setActive(status.trim().equalsIgnoreCase(STATUS_ACTIVE)?true:false);
    	companyService.save(company);
        //return new ResponseEntity<City>(city, HttpStatus.OK);
    	return new ResponseEntity<Company>(company.copy(), HttpStatus.OK);
    }

    //Create Company
    @RequestMapping(value = "/", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Company> create(@Valid Company company, BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<Company>(new Company(), HttpStatus.NOT_ACCEPTABLE);
    	}
        companyService.save(company);
        return new ResponseEntity<Company>(company.copy(), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = { "/", ""}, method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(Company company, SearchParameters searchParameters) {
    	if(searchParameters.getPageSize()==0){
    		searchParameters.setPageSize(600);//TODO remove this hard limit, use autocomplete if possible
    	}
    		
    	logger.info("Search Company :->"+company);
    	logger.info("Search Parameters :->"+searchParameters);
    	
        List<Company> companies = companyService.find(company, searchParameters.toSearchTemplate());
        List<Company> ret = new ArrayList<Company>();
        for (Company _company : companies) {
            ret.add(_company.copy());
        }
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", companyService.findCount(company, searchParameters.toSearchTemplate()));
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") Company company) {
        companyService.delete(company);
        return true;
    }
}