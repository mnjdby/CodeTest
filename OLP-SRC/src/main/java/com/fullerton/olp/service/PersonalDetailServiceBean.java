package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.PersonalDetail;
import com.fullerton.olp.repository.PersonalDetailRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.service.GenericEntityServiceImpl;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PersonalDetailServiceBean extends GenericEntityServiceImpl<PersonalDetail, Long> implements PersonalDetailService {

    /**
     * The Spring Data repository for PersonalDetail entities.
     */
	@Autowired
    private PersonalDetailRepository personalDetailRepository;
	
	@Autowired
    private ResidenceAddressService residenceAddressService;
    
    
    @Override
    public void save(PersonalDetail model) {
	    	// TODO Auto-generated method stub
    		residenceAddressService.save(model.getResidenceAddress());
	    	super.save(model);
    }
    /**
     * Find and return all personalDetails
     * @return collection of all personalDetails
     */
    @Override
    public Collection<PersonalDetail> findAll() {
        Collection<PersonalDetail> personalDetails = personalDetailRepository.find(new PersonalDetail(), new SearchTemplate());
        return personalDetails;
    }

    @Override
    public PersonalDetail findById(Long id) {
		PersonalDetail personalDetail = personalDetailRepository.findOne(id);
        return personalDetail;
    }
	
	@Override
	public GenericDao<PersonalDetail, Long> getDao() {
		// TODO Auto-generated method stub
		return personalDetailRepository;
	}

	@Override
	public PersonalDetail getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonalDetail getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}