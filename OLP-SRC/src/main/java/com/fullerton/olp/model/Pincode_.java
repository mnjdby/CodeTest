package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Pincode.class)
public class Pincode_ {
	public static volatile SingularAttribute<Pincode, Long> id;
	public static volatile SingularAttribute<Pincode, Boolean> active;
	public static volatile SingularAttribute<Pincode, String> name;
	public static volatile SingularAttribute<Pincode, City> city;
	public static volatile SingularAttribute<Pincode, Date> createdOn;
	public static volatile SingularAttribute<Pincode, String> createdBy;
	public static volatile SingularAttribute<Pincode, Date> modifiedOn;
	public static volatile SingularAttribute<Pincode, String> modifiedBy;
}
