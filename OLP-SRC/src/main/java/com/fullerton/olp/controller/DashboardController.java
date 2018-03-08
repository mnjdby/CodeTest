package com.fullerton.olp.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fullerton.olp.bean.StateWiseCount;
import com.fullerton.olp.bean.UserWiseCount;
import com.fullerton.olp.service.UserDetailService;
import com.fullerton.olp.service.account.AccountService;

@Controller
@RequestMapping("dashboard")
public class DashboardController extends BaseController{
    
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private AccountService accountService;
    
    @RequestMapping(value = {"/",""}, method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> dashboard() {
    	
        Map<String, Object> result = new HashMap<>();
        
        result.put("totalApplications", userDetailService.getTotalApplicationsCount());
        result.put("totalDisbursement", userDetailService.getTotalDisbursementCount());
        result.put("totalInprogress", userDetailService.getTotalInprogressCount());
        result.put("totalLeads", userDetailService.getTotalLeadsCount());
        
        result.put("callCenterAgents", accountService.findCallCenterAgentCount());
        result.put("productExperts", accountService.findProductAdminCount());
        
        result.put("statewiseusers", userDetailService.getStatewiseTotalApplicationsCount());
        
        List<UserWiseCount> userList = new ArrayList<>();
        userList.add(new UserWiseCount("Call Centre Agent", result.get("callCenterAgents").toString()));
        userList.add(new UserWiseCount("Product Experts", result.get("productExperts").toString()));
        
        result.put("userwisecount", userList);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }
    
    @RequestMapping(value = {"/statewise-users","/statewise-users/"}, method = GET)
    @ResponseBody
    public ResponseEntity<List<StateWiseCount>> statewiseUsers() {
    	
         
        ResponseEntity<List<StateWiseCount>> response = new ResponseEntity<>(userDetailService.getStatewiseTotalApplicationsCount(), HttpStatus.OK);
        return response;
    }
    
    @RequestMapping(value = {"/{professionType}","/{professionType}/"}, method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> dashboard(@PathVariable("professionType") Long professionTypeId) {
    	
        Map<String, Object> result = new HashMap<>();
        
        result.put("totalApplications", userDetailService.getTotalApplicationsCount(professionTypeId));
        result.put("totalDisbursement", userDetailService.getTotalDisbursementCount(professionTypeId));
        result.put("totalInprogress", userDetailService.getTotalInprogressCount(professionTypeId));
        result.put("totalLeads", userDetailService.getTotalLeadsCount(professionTypeId));
        
        result.put("callCenterAgents", accountService.findCallCenterAgentCount());
        result.put("productExperts", accountService.findProductAdminCount());
        
        result.put("statewiseusers", userDetailService.getStatewiseTotalApplicationsCount());
        
        List<UserWiseCount> userList = new ArrayList<>();
        userList.add(new UserWiseCount("Call Centre Agent", result.get("callCenterAgents").toString()));
        userList.add(new UserWiseCount("Product Experts", result.get("productExperts").toString()));
        
        
        result.put("userwisecount", userList);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }
    
    @RequestMapping(value = {"/statewise-users/{professionType}","/statewise-users/{professionType}/"}, method = GET)
    @ResponseBody
    public ResponseEntity<List<StateWiseCount>> statewiseUsers(@PathVariable("professionType") Long professionTypeId) {
    	
         
        ResponseEntity<List<StateWiseCount>> response = new ResponseEntity<>(userDetailService.getStatewiseTotalApplicationsCount(professionTypeId), HttpStatus.OK);
        return response;
    }
     
}