package com.fullerton.olp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.jpa.internal.metamodel.PluralAttributeImpl;

@StaticMetamodel(Qualification.class)
public class Qualification_ {
	public static volatile SingularAttribute<Qualification, Long> id;
	public static volatile SingularAttribute<Qualification, Boolean> active;
	public static volatile SingularAttribute<Qualification, String> name;
	public static volatile SingularAttribute<Qualification, Profession> profession;
	public static volatile PluralAttributeImpl<Qualification, LoanEligibility, Set<LoanEligibility>> loanEligibilities;
	public static volatile PluralAttributeImpl<Qualification, UserDetail, Set<UserDetail>> userDetails;
	public static volatile SingularAttribute<Qualification, Date> createdOn;
	public static volatile SingularAttribute<Qualification, String> createdBy;
	public static volatile SingularAttribute<Qualification, Date> modifiedOn;
	public static volatile SingularAttribute<Qualification, String> modifiedBy;
}
