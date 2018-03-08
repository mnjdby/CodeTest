package com.fullerton.olp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.jpa.internal.metamodel.PluralAttributeImpl;

@StaticMetamodel(ResidenceAddress.class)
public class ResidenceAddress_ {
	public static volatile SingularAttribute<ResidenceAddress, Long> id;
	public static volatile SingularAttribute<ResidenceAddress, String> address1;
	public static volatile SingularAttribute<ResidenceAddress, String> address2;
	public static volatile SingularAttribute<ResidenceAddress, String> address3;
	public static volatile SingularAttribute<ResidenceAddress, Date> livingSince;
	public static volatile SingularAttribute<UserDetail, City> city;
	public static volatile PluralAttributeImpl<ResidenceAddress, PersonalDetail, Set<PersonalDetail>> personalDetails;
	public static volatile SingularAttribute<ResidenceAddress, Date> createdOn;
	public static volatile SingularAttribute<ResidenceAddress, String> createdBy;
	public static volatile SingularAttribute<ResidenceAddress, Date> modifiedOn;
	public static volatile SingularAttribute<ResidenceAddress, String> modifiedBy;
}
