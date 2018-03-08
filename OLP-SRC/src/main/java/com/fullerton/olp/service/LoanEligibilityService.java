package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.LoanEligibility;
import com.fullerton.olp.service.GenericEntityService;

public interface LoanEligibilityService extends GenericEntityService<LoanEligibility, Long>{

	LoanEligibility findByTierAndQualification(Long tierId,Long qualificationId);
	
	LoanEligibility findById(Long id);
	
    Collection<LoanEligibility> findAll();

}
