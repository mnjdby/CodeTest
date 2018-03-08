package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.Pincode;
import com.fullerton.olp.repository.PincodeRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.service.GenericEntityServiceImpl;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PincodeServiceBean extends GenericEntityServiceImpl<Pincode, Long> implements PincodeService {

    /**
     * The Spring Data repository for Pincode entities.
     */
    @Autowired
    private PincodeRepository pincodeRepository;

    /**
     * Find and return all pincodes
     * @return collection of all pincodes
     */
    @Override
    public Collection<Pincode> findAll() {
        Collection<Pincode> pincodes = pincodeRepository.find(new Pincode(), new SearchTemplate());
        return pincodes;
    }

    @Override
    public Pincode findById(Long id) {
		Pincode pincode = pincodeRepository.findOne(id);
        return pincode;
    }
	
	@Override
	public GenericDao<Pincode, Long> getDao() {
		// TODO Auto-generated method stub
		return pincodeRepository;
	}

	@Override
	public Pincode getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pincode getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}