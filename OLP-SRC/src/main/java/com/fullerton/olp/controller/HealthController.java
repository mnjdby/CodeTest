package com.fullerton.olp.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fullerton.olp.model.account.Role;
import com.fullerton.olp.service.account.RoleService;

@Controller
@RequestMapping("health/")
public class HealthController {


    @Autowired
    private RoleService roleService;
    
    //get student/patient goal profile
    @RequestMapping(value = "check", method = GET)
    @ResponseBody
    public String checkHealth() {
    	
    	List<Role> list = roleService.find();
    	if(list == null || list.isEmpty()) {
    		throw new IllegalStateException();
    	} else {
    		return "sucess";
    	}
    	
    }
}
