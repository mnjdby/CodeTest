package com.fullerton.olp.service.account;

import java.util.Collection;

import com.fullerton.olp.model.account.Account;
import com.fullerton.olp.service.GenericEntityService;

public interface AccountService extends GenericEntityService<Account, Long>{

	Account findById(Long id);
	
    Collection<Account> findAll();

    Account findByUsername(String userename);

    Account createNewAccount(Account account);
    
    Long findProductAdminCount();
    Long findCallCenterAgentCount();
    
    
}
