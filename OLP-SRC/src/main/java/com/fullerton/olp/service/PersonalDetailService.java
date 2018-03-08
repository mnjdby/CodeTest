package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.PersonalDetail;

public interface PersonalDetailService extends GenericEntityService<PersonalDetail, Long>{

	PersonalDetail findById(Long id);
	
    Collection<PersonalDetail> findAll();

}
