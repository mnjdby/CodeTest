package com.fullerton.olp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.jpa.internal.metamodel.PluralAttributeImpl;

@StaticMetamodel(IndustryType.class)
public class IndustryType_ {
	public static volatile SingularAttribute<IndustryType, Long> id;
	public static volatile SingularAttribute<IndustryType, Boolean> active;
	public static volatile SingularAttribute<IndustryType, String> name;
	public static volatile SingularAttribute<IndustryType, City> city;
	public static volatile PluralAttributeImpl<IndustryType, Company, Set<Company>> companies;
	public static volatile SingularAttribute<IndustryType, Date> createdOn;
	public static volatile SingularAttribute<IndustryType, String> createdBy;
	public static volatile SingularAttribute<IndustryType, Date> modifiedOn;
	public static volatile SingularAttribute<IndustryType, String> modifiedBy;
}
