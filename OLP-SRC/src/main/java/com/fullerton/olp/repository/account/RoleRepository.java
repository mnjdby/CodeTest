package com.fullerton.olp.repository.account;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullerton.olp.model.account.Role;
import com.fullerton.olp.repository.GenericDao;

//@Repository
public interface RoleRepository extends GenericDao<Role, Long> {

    @Query("SELECT a FROM Role a WHERE a.name = :name")
    Role findByCode(@Param("name") String name);

}
