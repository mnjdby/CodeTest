package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.ApplicationStatus;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.ApplicationStatusRepository;
import com.fullerton.olp.repository.support.SearchTemplate;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ApplicationStatusServiceBean extends GenericEntityServiceImpl<ApplicationStatus, Long> implements ApplicationStatusService {

	/**
	 * The Spring Data repository for ApplicationStatus entities.
	 */
	@Autowired
	private ApplicationStatusRepository applicationStatusRepository;

	/**
	 * Find and return all templates
	 * 
	 * @return collection of all templates
	 */
	@Override
	public Collection<ApplicationStatus> findAll() {
		Collection<ApplicationStatus> templates = applicationStatusRepository.find(new ApplicationStatus(), new SearchTemplate());
		return templates;
	}

	@Override
	public ApplicationStatus findById(Long id) {
		ApplicationStatus template = applicationStatusRepository.findOne(id);
		return template;
	}

	@Override
	public GenericDao<ApplicationStatus, Long> getDao() {
		// TODO Auto-generated method stub
		return applicationStatusRepository;
	}

	@Override
	public ApplicationStatus getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicationStatus getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}