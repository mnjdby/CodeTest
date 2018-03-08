package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.Company;
import com.fullerton.olp.service.GenericEntityService;

public interface CompanyService extends GenericEntityService<Company, Long>{

	Company findById(Long id);
	
    Collection<Company> findAll();

}
