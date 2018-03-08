package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.IndustryType;
import com.fullerton.olp.repository.IndustryTypeRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.service.GenericEntityServiceImpl;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class IndustryTypeServiceBean extends GenericEntityServiceImpl<IndustryType, Long> implements IndustryTypeService {

    /**
     * The Spring Data repository for IndustryType entities.
     */
    @Autowired
    private IndustryTypeRepository industryTypeRepository;

    /**
     * Find and return all industryTypes
     * @return collection of all industryTypes
     */
    @Override
    public Collection<IndustryType> findAll() {
        Collection<IndustryType> industryTypes = industryTypeRepository.find(new IndustryType(), new SearchTemplate());
        return industryTypes;
    }

    @Override
    public IndustryType findById(Long id) {
		IndustryType industryType = industryTypeRepository.findOne(id);
        return industryType;
    }
	
	@Override
	public GenericDao<IndustryType, Long> getDao() {
		// TODO Auto-generated method stub
		return industryTypeRepository;
	}

	@Override
	public IndustryType getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IndustryType getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}