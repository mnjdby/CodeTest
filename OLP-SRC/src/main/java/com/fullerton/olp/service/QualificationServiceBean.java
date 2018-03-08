package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.Qualification;
import com.fullerton.olp.repository.QualificationRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.service.GenericEntityServiceImpl;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class QualificationServiceBean extends GenericEntityServiceImpl<Qualification, Long> implements QualificationService {

    /**
     * The Spring Data repository for Qualification entities.
     */
    @Autowired
    private QualificationRepository qualificationRepository;

    /**
     * Find and return all qualifications
     * @return collection of all qualifications
     */
    @Override
    public Collection<Qualification> findAll() {
        Collection<Qualification> qualifications = qualificationRepository.find(new Qualification(), new SearchTemplate());
        return qualifications;
    }

    @Override
    public Qualification findById(Long id) {
		Qualification qualification = qualificationRepository.findOne(id);
        return qualification;
    }
	
	@Override
	public GenericDao<Qualification, Long> getDao() {
		// TODO Auto-generated method stub
		return qualificationRepository;
	}

	@Override
	public Qualification getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Qualification getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}