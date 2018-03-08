package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Tier.class)
public class Tier_ {
	public static volatile SingularAttribute<Tier, Long> id;
	public static volatile SingularAttribute<Tier, Boolean> active;
	public static volatile SingularAttribute<Tier, String> name;
	public static volatile SingularAttribute<Tier, Float> minFees;
	public static volatile SingularAttribute<Tier, Float> maxFees;
	public static volatile SingularAttribute<Tier, LoanEligibility> loanEligibility;
	public static volatile SingularAttribute<Tier, Date> createdOn;
	public static volatile SingularAttribute<Tier, String> createdBy;
	public static volatile SingularAttribute<Tier, Date> modifiedOn;
	public static volatile SingularAttribute<Tier, String> modifiedBy;
}
