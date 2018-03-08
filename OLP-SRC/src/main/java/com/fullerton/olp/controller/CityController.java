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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fullerton.olp.controller.util.SearchParameters;
import com.fullerton.olp.model.City;
import com.fullerton.olp.model.Pincode;
import com.fullerton.olp.service.CityService;

@Controller
@RequestMapping("city")
//@PermitAllConsumers
public class CityController extends BaseController{
	
	private static String STATUS_ACTIVE="ACTIVE";
	private static String STATUS_INACTIVE="INACTIVE";
    
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private CityService cityService;
    
    @RequestMapping(value = "/{pk}")
    @ResponseBody
    public City get(@PathVariable("pk") City city) {
        return city.copy();
    }
    
    @RequestMapping(value = "/{pk}/pincodes")
    @ResponseBody
    public List<Pincode> getCityPins(@PathVariable("pk") City city) {
    	
    		List<Pincode> list = new ArrayList<>();
    		if(city.getPincodes() != null) {
    			for (Pincode pincode : city.getPincodes()) {
    				list.add(pincode.copy());
			}
    		}
        return list;
    }
    
    @RequestMapping(value = "/{pk}", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<City> update(@RequestBody @Valid City city, BindingResult bindingResult) {
    	logger.info("City:->"+city);
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<City>(new City(), HttpStatus.NOT_ACCEPTABLE);
    	}
        cityService.save(city);
        city = cityService.find(city).get(0);
        return new ResponseEntity<City>(city.copy(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{pk}/{status}", method = POST)
    @ResponseBody
    public ResponseEntity<City> update(@PathVariable(value="pk") Long id,@PathVariable(value="status") String status) {
    	logger.info("ID:->"+id);
    	logger.info("Status:->"+status);
    	if(id<1 || (status==null || !(status.trim().equalsIgnoreCase(STATUS_ACTIVE) || status.trim().equalsIgnoreCase(STATUS_INACTIVE)))){
    		return new ResponseEntity<City>(new City(), HttpStatus.NOT_ACCEPTABLE);
    	}
    	
    	City city = cityService.findById(id);
    	city.setActive(status.trim().equalsIgnoreCase(STATUS_ACTIVE)?true:false);
    	cityService.save(city);
        //return new ResponseEntity<City>(city, HttpStatus.OK);
    	return new ResponseEntity<City>(city.copy(), HttpStatus.OK);
    }

    //Create City
    @RequestMapping(value = "/", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<City> create(@RequestBody @Valid City city, BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<City>(new City(), HttpStatus.NOT_ACCEPTABLE);
    	}
        cityService.save (city);
        city = cityService.find(city).get(0);
        logger.info("City:->"+city);
        
        return new ResponseEntity<City>(city.copy(), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = { "/", ""}, method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list( City city,  SearchParameters searchParameters) {
    	if(searchParameters.getPageSize()==0){
    		searchParameters.setPageSize(600);//TODO remove this hard limit, use autocomplete if possible
    	}
    		
    	logger.info("Search City :->"+city);
    	logger.info("Search Parameters :->"+searchParameters);
        List<City> cities = cityService.find(city, searchParameters.toSearchTemplate());
        List<City> ret = new ArrayList<City>();
        for (City _city : cities) {
            ret.add(_city.copy());
        }
        Map<String, Object> result = new HashMap<>();
        result.put("size", cityService.findCount(city, searchParameters.toSearchTemplate()));
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") City city) {
        cityService.delete(city);
        return true;
    }

}