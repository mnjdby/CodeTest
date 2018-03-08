package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.AuditTrail;
import com.fullerton.olp.repository.AuditTrailRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AuditTrailServiceBean extends GenericEntityServiceImpl<AuditTrail, Long> implements AuditTrailService {

	/**
	 * The Spring Data repository for City entities.
	 */
	@Autowired
	private AuditTrailRepository auditTrailRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void save(AuditTrail model) {
		// TODO Auto-generated method stub
		super.save(model);
	}
	/**
	 * Find and return all citys
	 * 
	 * @return collection of all citys
	 */
	@Override
	public Collection<AuditTrail> findAll() {
		Collection<AuditTrail> auditTrails = auditTrailRepository.find(new AuditTrail(), new SearchTemplate());
		return auditTrails;
	}

	@Override
	public AuditTrail findById(Long id) {
		AuditTrail auditTrail = auditTrailRepository.findOne(id);
		return auditTrail;
	}

	@Override
	public GenericDao<AuditTrail, Long> getDao() {
		// TODO Auto-generated method stub
		return auditTrailRepository;
	}

	@Override
	public AuditTrail getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuditTrail getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}