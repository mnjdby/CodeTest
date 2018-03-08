package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.ResidenceAddress;
import com.fullerton.olp.service.GenericEntityService;

public interface ResidenceAddressService extends GenericEntityService<ResidenceAddress, Long>{

	ResidenceAddress findById(Long id);
	
    Collection<ResidenceAddress> findAll();

}
