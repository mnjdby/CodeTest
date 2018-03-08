package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.Territory;
import com.fullerton.olp.service.GenericEntityService;

public interface TerritoryService extends GenericEntityService<Territory, Long>{

	Territory findById(Long id);
	
    Collection<Territory> findAll();

}
