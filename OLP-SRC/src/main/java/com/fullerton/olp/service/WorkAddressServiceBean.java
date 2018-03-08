package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.WorkAddress;
import com.fullerton.olp.repository.WorkAddressRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.service.GenericEntityServiceImpl;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class WorkAddressServiceBean extends GenericEntityServiceImpl<WorkAddress, Long> implements WorkAddressService {

    /**
     * The Spring Data repository for WorkAddress entities.
     */
    @Autowired
    private WorkAddressRepository workAddressRepository;

    /**
     * Find and return all workAddresss
     * @return collection of all workAddresss
     */
    @Override
    public Collection<WorkAddress> findAll() {
        Collection<WorkAddress> workAddresss = workAddressRepository.find(new WorkAddress(), new SearchTemplate());
        return workAddresss;
    }

    @Override
    public WorkAddress findById(Long id) {
		WorkAddress workAddress = workAddressRepository.findOne(id);
        return workAddress;
    }
	
	@Override
	public GenericDao<WorkAddress, Long> getDao() {
		// TODO Auto-generated method stub
		return workAddressRepository;
	}

	@Override
	public WorkAddress getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorkAddress getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}