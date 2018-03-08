package com.fullerton.olp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.jpa.internal.metamodel.PluralAttributeImpl;

@StaticMetamodel(ProfessionalDetail.class)
public class ProfessionalDetail_ {
	public static volatile SingularAttribute<ProfessionalDetail, Long> id;
	public static volatile SingularAttribute<ProfessionalDetail, Integer> annualIncome;
	public static volatile SingularAttribute<ProfessionalDetail, Integer> monthlyIncome;
	public static volatile SingularAttribute<ProfessionalDetail, Integer> otherIncome;
	public static volatile SingularAttribute<ProfessionalDetail, WorkAddress> workAddress;
	public static volatile PluralAttributeImpl<ResidenceAddress, UserDetail, Set<UserDetail>> userDetails;
	public static volatile SingularAttribute<ProfessionalDetail, Date> createdOn;
	public static volatile SingularAttribute<ProfessionalDetail, String> createdBy;
	public static volatile SingularAttribute<ProfessionalDetail, Date> modifiedOn;
	public static volatile SingularAttribute<ProfessionalDetail, String> modifiedBy;
}
