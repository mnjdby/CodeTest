package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.City;
import com.fullerton.olp.service.GenericEntityService;

public interface CityService extends GenericEntityService<City, Long>{

	City findById(Long id);
	
    Collection<City> findAll();

}
