package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Company.class)
public class Company_ {
	public static volatile SingularAttribute<Company, Long> id;
	public static volatile SingularAttribute<Company, Boolean> active;
	public static volatile SingularAttribute<Company, String> name;
	public static volatile SingularAttribute<Company, IndustryType> industryType;
	public static volatile SingularAttribute<Company, Date> createdOn;
	public static volatile SingularAttribute<Company, String> createdBy;
	public static volatile SingularAttribute<Company, Date> modifiedOn;
	public static volatile SingularAttribute<Company, String> modifiedBy;
}
