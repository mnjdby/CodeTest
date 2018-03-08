package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.Pincode;
import com.fullerton.olp.service.GenericEntityService;

public interface PincodeService extends GenericEntityService<Pincode, Long>{

	Pincode findById(Long id);
	
    Collection<Pincode> findAll();

}
