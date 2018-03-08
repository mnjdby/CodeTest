package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.Profession;
import com.fullerton.olp.service.GenericEntityService;

public interface ProfessionService extends GenericEntityService<Profession, Long>{

	Profession findById(Long id);
	
    Collection<Profession> findAll();

}
