package com.fullerton.olp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.jpa.internal.metamodel.PluralAttributeImpl;

@StaticMetamodel(Territory.class)
public class Territory_ {
	public static volatile SingularAttribute<Territory, Long> id;
	public static volatile SingularAttribute<Territory, Boolean> active;
	public static volatile SingularAttribute<Territory, String> name;
	public static volatile PluralAttributeImpl<Territory, State, Set<State>> states;
	public static volatile SingularAttribute<Territory, Date> createdOn;
	public static volatile SingularAttribute<Territory, String> createdBy;
	public static volatile SingularAttribute<Territory, Date> modifiedOn;
	public static volatile SingularAttribute<Territory, String> modifiedBy;
}
