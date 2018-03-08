package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.City;
import com.fullerton.olp.repository.CityRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.service.GenericEntityServiceImpl;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CityServiceBean extends GenericEntityServiceImpl<City, Long> implements CityService {

    /**
     * The Spring Data repository for City entities.
     */
    @Autowired
    private CityRepository cityRepository;

    /**
     * Find and return all citys
     * @return collection of all citys
     */
    @Override
    public Collection<City> findAll() {
        Collection<City> citys = cityRepository.find(new City(), new SearchTemplate());
        return citys;
    }

    @Override
    public City findById(Long id) {
		City city = cityRepository.findOne(id);
        return city;
    }
	
	@Override
	public GenericDao<City, Long> getDao() {
		// TODO Auto-generated method stub
		return cityRepository;
	}

	@Override
	public City getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public City getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}