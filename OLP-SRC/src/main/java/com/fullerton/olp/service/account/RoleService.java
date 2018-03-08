package com.fullerton.olp.service.account;

import com.fullerton.olp.model.account.Role;
import com.fullerton.olp.service.GenericEntityService;

public interface RoleService extends GenericEntityService<Role, Long>{

    Role findById(Long id);

    Role findByCode(String code);

}
