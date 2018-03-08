package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(LoanDuration.class)
public class LoanDuration_ {
	public static volatile SingularAttribute<LoanDuration, Long> id;
	public static volatile SingularAttribute<LoanDuration, Boolean> active;
	public static volatile SingularAttribute<LoanDuration, Integer> min;
	public static volatile SingularAttribute<LoanDuration, Integer> max;
	public static volatile SingularAttribute<LoanDuration, Date> createdOn;
	public static volatile SingularAttribute<LoanDuration, String> createdBy;
	public static volatile SingularAttribute<LoanDuration, Date> modifiedOn;
	public static volatile SingularAttribute<LoanDuration, String> modifiedBy;
}
