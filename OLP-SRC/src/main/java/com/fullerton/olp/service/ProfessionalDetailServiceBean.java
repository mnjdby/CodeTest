package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.ProfessionalDetail;
import com.fullerton.olp.repository.ProfessionalDetailRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.service.GenericEntityServiceImpl;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProfessionalDetailServiceBean extends GenericEntityServiceImpl<ProfessionalDetail, Long> implements ProfessionalDetailService {

    /**
     * The Spring Data repository for ProfessionalDetail entities.
     */
    @Autowired
    private ProfessionalDetailRepository professionalDetailRepository;

    /**
     * Find and return all professionalDetails
     * @return collection of all professionalDetails
     */
    @Override
    public Collection<ProfessionalDetail> findAll() {
        Collection<ProfessionalDetail> professionalDetails = professionalDetailRepository.find(new ProfessionalDetail(), new SearchTemplate());
        return professionalDetails;
    }

    @Override
    public ProfessionalDetail findById(Long id) {
		ProfessionalDetail professionalDetail = professionalDetailRepository.findOne(id);
        return professionalDetail;
    }
	
	@Override
	public GenericDao<ProfessionalDetail, Long> getDao() {
		// TODO Auto-generated method stub
		return professionalDetailRepository;
	}

	@Override
	public ProfessionalDetail getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfessionalDetail getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}