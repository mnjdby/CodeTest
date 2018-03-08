package com.fullerton.olp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.jpa.internal.metamodel.PluralAttributeImpl;

@StaticMetamodel(City.class)
public class City_ {
	public static volatile SingularAttribute<City, Long> id;
	public static volatile SingularAttribute<City, Boolean> active;
	public static volatile SingularAttribute<City, String> name;
	public static volatile SingularAttribute<City, State> state;
	public static volatile PluralAttributeImpl<City, IndustryType, Set<IndustryType>> industryTypes;
	public static volatile SingularAttribute<City, Tier> tier;
	public static volatile PluralAttributeImpl<City, Pincode, Set<Pincode>> pincodes;
	public static volatile PluralAttributeImpl<ResidenceAddress, UserDetail, Set<UserDetail>> userDetails;
	public static volatile SingularAttribute<City, Date> createdOn;
	public static volatile SingularAttribute<City, String> createdBy;
	public static volatile SingularAttribute<City, Date> modifiedOn;
	public static volatile SingularAttribute<City, String> modifiedBy;
}
