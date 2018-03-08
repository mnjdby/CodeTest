package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.Territory;
import com.fullerton.olp.repository.TerritoryRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.service.GenericEntityServiceImpl;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TerritoryServiceBean extends GenericEntityServiceImpl<Territory, Long> implements TerritoryService {

    /**
     * The Spring Data repository for Territory entities.
     */
    @Autowired
    private TerritoryRepository territoryRepository;

    /**
     * Find and return all territorys
     * @return collection of all territorys
     */
    @Override
    public Collection<Territory> findAll() {
        Collection<Territory> territorys = territoryRepository.find(new Territory(), new SearchTemplate());
        return territorys;
    }

    @Override
    public Territory findById(Long id) {
		Territory territory = territoryRepository.findOne(id);
        return territory;
    }
	
	@Override
	public GenericDao<Territory, Long> getDao() {
		// TODO Auto-generated method stub
		return territoryRepository;
	}

	@Override
	public Territory getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Territory getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}