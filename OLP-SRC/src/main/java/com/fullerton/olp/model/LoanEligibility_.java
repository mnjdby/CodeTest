package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(LoanEligibility.class)
public class LoanEligibility_ {
	public static volatile SingularAttribute<LoanEligibility, Long> id;
	public static volatile SingularAttribute<LoanEligibility, Boolean> active;
	public static volatile SingularAttribute<LoanEligibility, Integer> maxAmount;
	public static volatile SingularAttribute<LoanEligibility, Integer> minAmount;
	public static volatile SingularAttribute<LoanEligibility, Tier> tier;
	public static volatile SingularAttribute<LoanEligibility, Qualification> qualification;
	public static volatile SingularAttribute<LoanEligibility, Date> createdOn;
	public static volatile SingularAttribute<LoanEligibility, String> createdBy;
	public static volatile SingularAttribute<LoanEligibility, Date> modifiedOn;
	public static volatile SingularAttribute<LoanEligibility, String> modifiedBy;
}
