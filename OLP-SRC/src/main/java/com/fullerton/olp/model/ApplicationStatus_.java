package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ApplicationStatus.class)
public class ApplicationStatus_ {
	public static volatile SingularAttribute<ApplicationStatus, Long> id;
	public static volatile SingularAttribute<ApplicationStatus, Boolean> active;
	public static volatile SingularAttribute<ApplicationStatus, String> status;
	public static volatile SingularAttribute<ApplicationStatus, String> detail;
	public static volatile SingularAttribute<ApplicationStatus, Date> createdOn;
	public static volatile SingularAttribute<ApplicationStatus, String> createdBy;
	public static volatile SingularAttribute<ApplicationStatus, Date> modifiedOn;
	public static volatile SingularAttribute<ApplicationStatus, String> modifiedBy;
}
