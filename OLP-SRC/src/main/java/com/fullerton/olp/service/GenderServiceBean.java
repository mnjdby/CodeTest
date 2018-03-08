package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.Gender;
import com.fullerton.olp.repository.GenderRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.service.GenericEntityServiceImpl;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GenderServiceBean extends GenericEntityServiceImpl<Gender, Long> implements GenderService {

    /**
     * The Spring Data repository for Gender entities.
     */
    @Autowired
    private GenderRepository genderRepository;

    /**
     * Find and return all genders
     * @return collection of all genders
     */
    @Override
    public Collection<Gender> findAll() {
        Collection<Gender> genders = genderRepository.find(new Gender(), new SearchTemplate());
        return genders;
    }

    @Override
    public Gender findById(Long id) {
		Gender gender = genderRepository.findOne(id);
        return gender;
    }
	
	@Override
	public GenericDao<Gender, Long> getDao() {
		// TODO Auto-generated method stub
		return genderRepository;
	}

	@Override
	public Gender getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gender getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}