package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.State;
import com.fullerton.olp.repository.StateRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.service.GenericEntityServiceImpl;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StateServiceBean extends GenericEntityServiceImpl<State, Long> implements StateService {

    /**
     * The Spring Data repository for State entities.
     */
    @Autowired
    private StateRepository stateRepository;

    /**
     * Find and return all states
     * @return collection of all states
     */
    @Override
    public Collection<State> findAll() {
        Collection<State> states = stateRepository.find(new State(), new SearchTemplate());
        return states;
    }

    @Override
    public State findById(Long id) {
		State state = stateRepository.findOne(id);
        return state;
    }
	
	@Override
	public GenericDao<State, Long> getDao() {
		// TODO Auto-generated method stub
		return stateRepository;
	}

	@Override
	public State getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}