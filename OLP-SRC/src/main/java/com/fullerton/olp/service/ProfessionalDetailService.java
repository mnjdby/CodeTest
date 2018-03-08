package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.ProfessionalDetail;
import com.fullerton.olp.service.GenericEntityService;

public interface ProfessionalDetailService extends GenericEntityService<ProfessionalDetail, Long>{

	ProfessionalDetail findById(Long id);
	
    Collection<ProfessionalDetail> findAll();

}
