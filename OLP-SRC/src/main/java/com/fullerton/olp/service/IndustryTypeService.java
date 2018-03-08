package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.IndustryType;
import com.fullerton.olp.service.GenericEntityService;

public interface IndustryTypeService extends GenericEntityService<IndustryType, Long>{

	IndustryType findById(Long id);
	
    Collection<IndustryType> findAll();

}
