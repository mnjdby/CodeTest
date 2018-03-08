package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(PersonalDetail.class)
public class PersonalDetail_ {
	public static volatile SingularAttribute<PersonalDetail, Long> id;
	public static volatile SingularAttribute<PersonalDetail, Date> dob;
	public static volatile SingularAttribute<PersonalDetail, String> upiId;
	public static volatile SingularAttribute<PersonalDetail, Gender> gender;
	public static volatile SingularAttribute<PersonalDetail, ResidenceAddress> residenceAddress;
	public static volatile SingularAttribute<PersonalDetail, MarritalStatus> marritalStatus;
	public static volatile SingularAttribute<PersonalDetail, Date> createdOn;
	public static volatile SingularAttribute<PersonalDetail, String> createdBy;
	public static volatile SingularAttribute<PersonalDetail, Date> modifiedOn;
	public static volatile SingularAttribute<PersonalDetail, String> modifiedBy;
}
