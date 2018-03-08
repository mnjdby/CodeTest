package com.fullerton.olp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.ResidenceType;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.ResidenceTypeRepository;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ResidenceTypeServiceBean extends GenericEntityServiceImpl<ResidenceType, Long> implements ResidenceTypeService {

    /**
     * The Spring Data repository for ResidenceType entities.
     */
    @Autowired
    private ResidenceTypeRepository residenceTypeRepository;

	
	@Override
	public GenericDao<ResidenceType, Long> getDao() {
		// TODO Auto-generated method stub
		return residenceTypeRepository;
	}

	@Override
	public ResidenceType getNew() {
		// TODO Auto-generated method stub
		return new ResidenceType();
	}

	@Override
	public ResidenceType getNewWithDefaults() {
		// TODO Auto-generated method stub
		return getNew();
	}
}