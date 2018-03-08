package com.fullerton.olp.service.account;

import com.fullerton.olp.model.account.Role;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.account.RoleRepository;
import com.fullerton.olp.service.GenericEntityServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manage the data from database from Role table user
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RoleServiceBean extends GenericEntityServiceImpl<Role, Long> implements  RoleService{
    /**
     * The Spring Data repository for Account entities.
     */
    @Autowired
    private RoleRepository roleRepository;

	@Override
	public GenericDao<Role, Long> getDao() {
		// TODO Auto-generated method stub
		return roleRepository;
	}

	@Override
	public Role getNew() {
		// TODO Auto-generated method stub
		return new Role();
	}

	@Override
	public Role getNewWithDefaults() {
		// TODO Auto-generated method stub
		return getNew();
	}

	@Override
	public Role findById(Long id) {
		// TODO Auto-generated method stub
		return getDao().getOne(id);
	}

	@Override
	public Role findByCode(String code) {
		// TODO Auto-generated method stub
		return roleRepository.findByCode(code);
	}

    /**
     * Get by id
     * @param id
     * @return
     */
    
}
