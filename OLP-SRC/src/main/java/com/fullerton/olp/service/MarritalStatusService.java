package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.MarritalStatus;
import com.fullerton.olp.service.GenericEntityService;

public interface MarritalStatusService extends GenericEntityService<MarritalStatus, Long>{

	MarritalStatus findById(Long id);
	
    Collection<MarritalStatus> findAll();

}
