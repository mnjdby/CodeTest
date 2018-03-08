package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserDetail.class)
public class UserDetail_ {
	public static volatile SingularAttribute<UserDetail, Long> id;
	public static volatile SingularAttribute<UserDetail, Boolean> active;
	public static volatile SingularAttribute<UserDetail, String> name;
	public static volatile SingularAttribute<UserDetail, String> aadhaarNumber;
	public static volatile SingularAttribute<UserDetail, String> mobile;
	public static volatile SingularAttribute<UserDetail, String> email;
	public static volatile SingularAttribute<UserDetail, City> city;
	public static volatile SingularAttribute<UserDetail, PersonalDetail> personalDetail;
	public static volatile SingularAttribute<UserDetail, Profession> profession;
	public static volatile SingularAttribute<UserDetail, ProfessionalDetail> professionalDetail;
	public static volatile SingularAttribute<UserDetail, Qualification> qualification;
	public static volatile SingularAttribute<UserDetail, Date> createdOn;
	public static volatile SingularAttribute<UserDetail, String> createdBy;
	public static volatile SingularAttribute<UserDetail, Date> modifiedOn;
	public static volatile SingularAttribute<UserDetail, String> modifiedBy;
}
