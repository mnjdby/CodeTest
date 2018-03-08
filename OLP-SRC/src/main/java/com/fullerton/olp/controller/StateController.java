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
import com.fullerton.olp.model.City;
import com.fullerton.olp.model.State;
import com.fullerton.olp.service.StateService;

@Controller
@RequestMapping("state")
public class StateController extends BaseController{
    
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private StateService stateService;
    
    @RequestMapping(value = "/{pk}")
    @ResponseBody
    public State get(@PathVariable("pk") State state) {
        return state;
    }
    
    @RequestMapping(value = "/{pk}/city")
    @ResponseBody
    public ResponseEntity<List<City>> getCities(@PathVariable("pk") State state) {
    		List<City> cities = new ArrayList<>();
    		for (City city : state.getCities()) {
    			cities.add(city.copy());
		}
        return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{pk}", method = POST)
    @ResponseBody
    public ResponseEntity<State> update(@Valid State state, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<State>(new State(), HttpStatus.NOT_ACCEPTABLE);
    	}
        stateService.save(state);
        return new ResponseEntity<State>(state.copy(), HttpStatus.OK);
    }

    //Create State
    @RequestMapping(value = "/", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<State> create(@RequestBody @Valid State state, BindingResult bindingResult) {
    	logger.info("State :->"+state);
    	if(bindingResult.hasErrors()){
    		return new ResponseEntity<State>(new State(), HttpStatus.NOT_ACCEPTABLE);
    	}
        stateService.save(state);
        state = stateService.find(state).get(0);
        return new ResponseEntity<State>(state.copy(), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/", method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(State state, SearchParameters searchParameters) {
    	
        List<State> states = stateService.find(state, searchParameters.toSearchTemplate());
        List<State> ret = new ArrayList<State>();
        for (State _state : states) {
            ret.add(_state.copy());
        }
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", states.size());
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") State state) {
        stateService.delete(state);
        return true;
    }

}