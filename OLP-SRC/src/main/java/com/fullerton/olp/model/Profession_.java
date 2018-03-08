package com.fullerton.olp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.jpa.internal.metamodel.PluralAttributeImpl;

@StaticMetamodel(Profession.class)
public class Profession_ {
	public static volatile SingularAttribute<Profession, Long> id;
	public static volatile SingularAttribute<Profession, Boolean> active;
	public static volatile SingularAttribute<Profession, String> name;
	public static volatile PluralAttributeImpl<Profession, Qualification, Set<Qualification>> qualifications;
	public static volatile PluralAttributeImpl<Profession, UserDetail, Set<UserDetail>> userDetails;
	public static volatile SingularAttribute<Profession, Date> createdOn;
	public static volatile SingularAttribute<Profession, String> createdBy;
	public static volatile SingularAttribute<Profession, Date> modifiedOn;
	public static volatile SingularAttribute<Profession, String> modifiedBy;
}
