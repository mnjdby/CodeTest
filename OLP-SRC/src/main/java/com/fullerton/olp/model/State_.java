package com.fullerton.olp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.jpa.internal.metamodel.PluralAttributeImpl;

@StaticMetamodel(State.class)
public class State_ {
	public static volatile SingularAttribute<State, Long> id;
	public static volatile SingularAttribute<State, Boolean> active;
	public static volatile SingularAttribute<State, String> name;
	public static volatile PluralAttributeImpl<State, City, Set<City>> cities;
	public static volatile SingularAttribute<State, Territory> territory;
	public static volatile SingularAttribute<State, Date> createdOn;
	public static volatile SingularAttribute<State, String> createdBy;
	public static volatile SingularAttribute<State, Date> modifiedOn;
	public static volatile SingularAttribute<State, String> modifiedBy;
}
