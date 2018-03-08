package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.Company;
import com.fullerton.olp.repository.CompanyRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.service.GenericEntityServiceImpl;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CompanyServiceBean extends GenericEntityServiceImpl<Company, Long> implements CompanyService {

    /**
     * The Spring Data repository for Company entities.
     */
    @Autowired
    private CompanyRepository companyRepository;

    /**
     * Find and return all companys
     * @return collection of all companys
     */
    @Override
    public Collection<Company> findAll() {
        Collection<Company> companys = companyRepository.find(new Company(), new SearchTemplate());
        return companys;
    }

    @Override
    public Company findById(Long id) {
		Company company = companyRepository.findOne(id);
        return company;
    }
	
	@Override
	public GenericDao<Company, Long> getDao() {
		// TODO Auto-generated method stub
		return companyRepository;
	}

	@Override
	public Company getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}