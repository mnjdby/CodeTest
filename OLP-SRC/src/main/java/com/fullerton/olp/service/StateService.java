package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.State;
import com.fullerton.olp.service.GenericEntityService;

public interface StateService extends GenericEntityService<State, Long>{

	State findById(Long id);
	
    Collection<State> findAll();

}
