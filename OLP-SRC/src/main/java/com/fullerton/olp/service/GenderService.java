package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.Gender;
import com.fullerton.olp.service.GenericEntityService;

public interface GenderService extends GenericEntityService<Gender, Long>{

	Gender findById(Long id);
	
    Collection<Gender> findAll();

}
