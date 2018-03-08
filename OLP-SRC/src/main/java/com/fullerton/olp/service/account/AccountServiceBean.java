package com.fullerton.olp.service.account;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.Profession;
import com.fullerton.olp.model.account.Account;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.account.AccountRepository;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.service.GenericEntityServiceImpl;
import com.fullerton.olp.service.ProfessionService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AccountServiceBean extends GenericEntityServiceImpl<Account, Long> implements AccountService {

    /**
     * The Spring Data repository for Account entities.
     */
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProfessionService professionService;

    
    @Override
    @Transactional
    public void save(Account model) {
    		Account toBeSaved = model.copy();
    		
    		Set<Profession> professions = model.getProfessions();
    		if(professions != null) {
    			for (Profession profession : professions) {
    				toBeSaved.getProfessions().add(professionService.getById(profession.getId()));
			}
    		}
    		super.save(toBeSaved);
    }
    
    @Override
    public Long findProductAdminCount() {
    	// TODO Auto-generated method stub
    	return accountRepository.findProductAdminCount();
    }
    @Override
    public Long findCallCenterAgentCount() {
    	// TODO Auto-generated method stub
    	return accountRepository.findCallCenterAgentCount();
    }
       /**
     * Find and return all accounts
     * @return collection of all accounts
     */
    @Override
    public Collection<Account> findAll() {
        Collection<Account> accounts = accountRepository.find(new Account(), new SearchTemplate());
        return accounts;
    }

    /**
     * Find user by username
     * @param username the username of the user
     * @return the user account
     */
    @Override
    public Account findByUsername(String username) {
        Account account = accountRepository.findByUsernameOrEmail(username);
        return account;
    }

    /**
     * Create a new user as simple user. Find the simple user role from the database
     * add assign to the many to many collection
     * @param account - new Account of user
     * @return - the created account
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Account createNewAccount(Account account) {

    	//Set<Role> roles = new HashSet<>();
    
    	Account persistantAccount = accountRepository.save(account);
        
        return persistantAccount;
    }


	@Override
    public Account findById(Long id) {
		Account account = accountRepository.findOne(id);
        return account;
    }
	
	@Override
	public GenericDao<Account, Long> getDao() {
		// TODO Auto-generated method stub
		return accountRepository;
	}

	@Override
	public Account getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}

}
