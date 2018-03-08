package com.fullerton.olp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.jpa.internal.metamodel.PluralAttributeImpl;

@StaticMetamodel(Gender.class)
public class Gender_ {
	public static volatile SingularAttribute<Gender, Long> id;
	public static volatile SingularAttribute<Gender, Boolean> active;
	public static volatile SingularAttribute<Gender, String> name;
	public static volatile PluralAttributeImpl<Gender, PersonalDetail, Set<PersonalDetail>> personalDetails;
	public static volatile SingularAttribute<Gender, Date> createdOn;
	public static volatile SingularAttribute<Gender, String> createdBy;
	public static volatile SingularAttribute<Gender, Date> modifiedOn;
	public static volatile SingularAttribute<Gender, String> modifiedBy;
}
