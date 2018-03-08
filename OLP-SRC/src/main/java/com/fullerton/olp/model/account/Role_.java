package com.fullerton.olp.model.account;

import java.util.Date;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.jpa.internal.metamodel.PluralAttributeImpl;

@StaticMetamodel(Role.class)
public class Role_ {
	public static volatile SingularAttribute<Role, Long> id;
	public static volatile SingularAttribute<Role, Boolean> active;
	public static volatile SingularAttribute<Role, String> name;
	public static volatile PluralAttributeImpl<Role, Account, Set<Account>> accounts;
	public static volatile SingularAttribute<Role, Date> createdOn;
	public static volatile SingularAttribute<Role, String> createdBy;
	public static volatile SingularAttribute<Role, Date> modifiedOn;
	public static volatile SingularAttribute<Role, String> modifiedBy;
}
