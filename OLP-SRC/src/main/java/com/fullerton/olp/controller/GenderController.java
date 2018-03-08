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
import com.fullerton.olp.model.Gender;
import com.fullerton.olp.service.GenderService;

@Controller
@RequestMapping("gender")
public class GenderController extends BaseController{
    
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private GenderService genderService;
    
    @RequestMapping(value = "/{pk}")
    @ResponseBody
    public Gender get(@PathVariable("pk") Gender gender) {
        return gender;
    }
    
    @RequestMapping(value = "/{pk}", method = POST)
    @ResponseBody
    public ResponseEntity<Gender> update(@Valid Gender gender, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<Gender>(new Gender(), HttpStatus.NOT_ACCEPTABLE);
    	}
        genderService.save(gender);
        return new ResponseEntity<Gender>(gender.copy(), HttpStatus.OK);
    }

    //Create Gender
    @RequestMapping(value = "/", method = POST)
    @ResponseBody
    public ResponseEntity<Gender> create(@Valid Gender gender, BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<Gender>(new Gender(), HttpStatus.NOT_ACCEPTABLE);
    	}
        genderService.save(gender);
        return new ResponseEntity<Gender>(gender.copy(), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = {"/",""}, method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(Gender gender, SearchParameters searchParameters) {
    	
        List<Gender> genders = genderService.find(gender, searchParameters.toSearchTemplate());
        List<Gender> ret = new ArrayList<Gender>();
        for (Gender _gender : genders) {
            ret.add(_gender.copy());
        }
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", genders.size());
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") Gender gender) {
        genderService.delete(gender);
        return true;
    }
}