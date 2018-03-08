package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.Profession;
import com.fullerton.olp.repository.ProfessionRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.service.GenericEntityServiceImpl;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProfessionServiceBean extends GenericEntityServiceImpl<Profession, Long> implements ProfessionService {

    /**
     * The Spring Data repository for Profession entities.
     */
    @Autowired
    private ProfessionRepository professionRepository;

    /**
     * Find and return all professions
     * @return collection of all professions
     */
    @Override
    public Collection<Profession> findAll() {
        Collection<Profession> professions = professionRepository.find(new Profession(), new SearchTemplate());
        return professions;
    }

    @Override
    public Profession findById(Long id) {
		Profession profession = professionRepository.findOne(id);
        return profession;
    }
	
	@Override
	public GenericDao<Profession, Long> getDao() {
		// TODO Auto-generated method stub
		return professionRepository;
	}

	@Override
	public Profession getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profession getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}