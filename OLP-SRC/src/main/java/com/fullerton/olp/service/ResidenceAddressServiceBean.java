package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.ResidenceAddress;
import com.fullerton.olp.repository.ResidenceAddressRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.service.GenericEntityServiceImpl;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ResidenceAddressServiceBean extends GenericEntityServiceImpl<ResidenceAddress, Long> implements ResidenceAddressService {

    /**
     * The Spring Data repository for ResidenceAddress entities.
     */
    @Autowired
    private ResidenceAddressRepository residenceAddressRepository;

    @Override
    public void save(ResidenceAddress model) {
    		if( model.getResidenceType() == null || model.getResidenceType().getId() == null) {
    			model.setResidenceType(null);
    		}
    		super.save(model);
    }
    /**
     * Find and return all residenceAddresss
     * @return collection of all residenceAddresss
     */
    @Override
    public Collection<ResidenceAddress> findAll() {
        Collection<ResidenceAddress> residenceAddresss = residenceAddressRepository.find(new ResidenceAddress(), new SearchTemplate());
        return residenceAddresss;
    }

    @Override
    public ResidenceAddress findById(Long id) {
		ResidenceAddress residenceAddress = residenceAddressRepository.findOne(id);
        return residenceAddress;
    }
	
	@Override
	public GenericDao<ResidenceAddress, Long> getDao() {
		// TODO Auto-generated method stub
		return residenceAddressRepository;
	}

	@Override
	public ResidenceAddress getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResidenceAddress getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}