package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.AuditTrail;

public interface AuditTrailService extends GenericEntityService<AuditTrail, Long>{

	AuditTrail findById(Long id);
	
    Collection<AuditTrail> findAll();

}
