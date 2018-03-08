package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.WorkAddress;
import com.fullerton.olp.service.GenericEntityService;

public interface WorkAddressService extends GenericEntityService<WorkAddress, Long>{

	WorkAddress findById(Long id);
	
    Collection<WorkAddress> findAll();

}
