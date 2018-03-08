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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fullerton.olp.controller.util.SearchParameters;
import com.fullerton.olp.model.State;
import com.fullerton.olp.model.Territory;
import com.fullerton.olp.service.TerritoryService;

@Controller
@RequestMapping("territory")
public class TerritoryController extends BaseController{
    
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private TerritoryService territoryService;
    
    @RequestMapping(value = "/{pk}")
    @ResponseBody
    public Territory get(@PathVariable("pk") Territory territory) {
        return territory;
    }
    @RequestMapping(value = "/{pk}/state")
    @ResponseBody
    public ResponseEntity<List<State>> getStates(@PathVariable("pk") Territory territory) {
    		List<State> states = new ArrayList<>();
    		
    		for (State state : territory.getStates()) {
    			states.add(state.copy());
		}
        return new ResponseEntity<List<State>>(states, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{pk}", method = POST)
    @ResponseBody
    public ResponseEntity<Territory> update(@Valid Territory territory, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<Territory>(new Territory(), HttpStatus.NOT_ACCEPTABLE);
    	}
        territoryService.save(territory);
        return new ResponseEntity<Territory>(territory.copy(), HttpStatus.OK);
    }

    //Create Territory
    @RequestMapping(value = "/", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Territory> create(@RequestBody @Valid Territory territory, BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<Territory>(new Territory(), HttpStatus.NOT_ACCEPTABLE);
    	}
        territoryService.save(territory);
        territory=territoryService.find(territory).get(0);
        return new ResponseEntity<Territory>(territory.copy(), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/", method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(Territory territory, SearchParameters searchParameters) {
    	
        List<Territory> cities = territoryService.find(territory, searchParameters.toSearchTemplate());
        List<Territory> ret = new ArrayList<Territory>();
        for (Territory _city : cities) {
            ret.add(_city.copy());
        }
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", cities.size());
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") Territory territory) {
        territoryService.delete(territory);
        return true;
    }

}