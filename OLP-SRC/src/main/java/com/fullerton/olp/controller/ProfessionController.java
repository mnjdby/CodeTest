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
import com.fullerton.olp.model.Profession;
import com.fullerton.olp.model.Qualification;
import com.fullerton.olp.service.ProfessionService;

@Controller
@RequestMapping("profession")
public class ProfessionController extends BaseController{
    
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private ProfessionService professionService;
    
    @RequestMapping(value = {"/{pk}","/{pk}"})
    @ResponseBody
    public ResponseEntity<Profession>  get(@PathVariable("pk") Profession profession) {
        return new ResponseEntity<Profession>(profession.copy(),HttpStatus.OK);
    }
    
    @RequestMapping(value = {"/{pk}/qualification","/{pk}/qualification/"})
    @ResponseBody
    public ResponseEntity<List<Qualification>> getQualifications(@PathVariable("pk") Profession profession) {
    		List<Qualification> qualifications = profession.getQualifications();
    		List<Qualification> listToReturn = new ArrayList<>();
    		
    		for (Qualification qualification : qualifications) {
    			listToReturn.add(qualification.copy());
			}
    		return new ResponseEntity<List<Qualification>>(listToReturn,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{pk}", method = POST)
    @ResponseBody
    public ResponseEntity<Profession> update(@Valid Profession profession, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<Profession>(new Profession(), HttpStatus.NOT_ACCEPTABLE);
    	}
        professionService.save(profession);
        return new ResponseEntity<Profession>(profession.copy(), HttpStatus.CREATED);
    }

    //Create Profession
    @RequestMapping(value = {"/",""}, method = POST)
    @ResponseBody
    public ResponseEntity<Profession> create(@Valid Profession profession, BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<Profession>(new Profession(), HttpStatus.NOT_ACCEPTABLE);
    	}
        professionService.save(profession);
        return new ResponseEntity<Profession>(profession.copy(), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = {"/",""}, method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(Profession profession, SearchParameters searchParameters) {
    	
        List<Profession> professions = professionService.find(profession, searchParameters.toSearchTemplate());
        List<Profession> ret = new ArrayList<Profession>();
        for (Profession _profession : professions) {
            ret.add(_profession.copy());
        }
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", professions.size());
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") Profession profession) {
        professionService.delete(profession);
        return true;
    }

}