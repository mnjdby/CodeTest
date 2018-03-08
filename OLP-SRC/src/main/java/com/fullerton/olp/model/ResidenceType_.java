package com.fullerton.olp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.jpa.internal.metamodel.PluralAttributeImpl;

@StaticMetamodel(ResidenceType.class)
public class ResidenceType_ {
	public static volatile SingularAttribute<ProfessionalDetail, Long> id;
	public static volatile SingularAttribute<IndustryType, Boolean> active;
	public static volatile SingularAttribute<IndustryType, String> name;
	public static volatile PluralAttributeImpl<ResidenceAddress, ResidenceAddress, Set<ResidenceAddress>> residenceAddresses;
	public static volatile SingularAttribute<ProfessionalDetail, Date> createdOn;
	public static volatile SingularAttribute<ProfessionalDetail, String> createdBy;
	public static volatile SingularAttribute<ProfessionalDetail, Date> modifiedOn;
	public static volatile SingularAttribute<ProfessionalDetail, String> modifiedBy;
}
