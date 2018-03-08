package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.Qualification;
import com.fullerton.olp.service.GenericEntityService;

public interface QualificationService extends GenericEntityService<Qualification, Long>{

	Qualification findById(Long id);
	
    Collection<Qualification> findAll();

}
