package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.LoanEligibility;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.LoanEligibilityRepository;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.service.GenericEntityServiceImpl;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoanEligibilityServiceBean extends GenericEntityServiceImpl<LoanEligibility, Long> implements LoanEligibilityService {

    /**
     * The Spring Data repository for LoanEligibility entities.
     */
    @Autowired
    private LoanEligibilityRepository loanEligibilityRepository;

    
    
    @Override
    public LoanEligibility findByTierAndQualification(Long tierId,Long qualificationId) {
		LoanEligibility loanEligibility = loanEligibilityRepository.findByTierAndQualification(tierId, qualificationId);
        return loanEligibility;
    }
    /**
     * Find and return all loanEligibilitys
     * @return collection of all loanEligibilitys
     */
    @Override
    public Collection<LoanEligibility> findAll() {
        Collection<LoanEligibility> loanEligibilitys = loanEligibilityRepository.find(new LoanEligibility(), new SearchTemplate());
        return loanEligibilitys;
    }

    @Override
    public LoanEligibility findById(Long id) {
		LoanEligibility loanEligibility = loanEligibilityRepository.findOne(id);
        return loanEligibility;
    }
	
	@Override
	public GenericDao<LoanEligibility, Long> getDao() {
		// TODO Auto-generated method stub
		return loanEligibilityRepository;
	}

	@Override
	public LoanEligibility getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoanEligibility getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}