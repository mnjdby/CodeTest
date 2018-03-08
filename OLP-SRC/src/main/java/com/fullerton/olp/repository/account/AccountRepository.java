package com.fullerton.olp.repository.account;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullerton.olp.model.account.Account;
import com.fullerton.olp.repository.GenericDao;

//@Repository
public interface AccountRepository extends GenericDao<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.email = :username or a.username = :username")
    Account findByUsernameOrEmail(@Param("username") String username);

    @Query("SELECT a FROM Account a  WHERE a.mobile = :mobile")
    Account findByMobile(@Param("mobile") String mobile);
    
    @Query("SELECT a FROM Account a  WHERE a.email = :email")
    Account findByEmail(@Param("email") String email);
    
    @Query("SELECT a FROM Account a  WHERE a.username = :username")
    Account findByUsername(@Param("username") String username);
    
    @Query("SELECT  count(*) FROM Account a  WHERE a.role.id = 2")
    Long findProductAdminCount();
    
    @Query("SELECT  count(*) FROM Account a  WHERE a.role.id = 3")
    Long findCallCenterAgentCount();
    
    
}