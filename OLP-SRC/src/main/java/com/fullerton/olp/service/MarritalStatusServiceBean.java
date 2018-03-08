package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.MarritalStatus;
import com.fullerton.olp.repository.MarritalStatusRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.service.GenericEntityServiceImpl;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MarritalStatusServiceBean extends GenericEntityServiceImpl<MarritalStatus, Long> implements MarritalStatusService {

    /**
     * The Spring Data repository for MarritalStatus entities.
     */
    @Autowired
    private MarritalStatusRepository marritalStatusRepository;

    /**
     * Find and return all marritalStatuss
     * @return collection of all marritalStatuss
     */
    @Override
    public Collection<MarritalStatus> findAll() {
        Collection<MarritalStatus> marritalStatuss = marritalStatusRepository.find(new MarritalStatus(), new SearchTemplate());
        return marritalStatuss;
    }

    @Override
    public MarritalStatus findById(Long id) {
		MarritalStatus marritalStatus = marritalStatusRepository.findOne(id);
        return marritalStatus;
    }
	
	@Override
	public GenericDao<MarritalStatus, Long> getDao() {
		// TODO Auto-generated method stub
		return marritalStatusRepository;
	}

	@Override
	public MarritalStatus getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MarritalStatus getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}