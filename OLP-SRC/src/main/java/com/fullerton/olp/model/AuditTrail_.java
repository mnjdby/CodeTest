package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuditTrail.class)
public class AuditTrail_ {
	public static volatile SingularAttribute<AuditTrail, Long> id;
	public static volatile SingularAttribute<AuditTrail, String> name;
	public static volatile SingularAttribute<AuditTrail, String> action;
	public static volatile SingularAttribute<AuditTrail, String> actionDetails;
	public static volatile SingularAttribute<AuditTrail, Date> createdOn;
	public static volatile SingularAttribute<AuditTrail, String> createdBy;
	public static volatile SingularAttribute<AuditTrail, Date> modifiedOn;
	public static volatile SingularAttribute<AuditTrail, String> modifiedBy;
}
