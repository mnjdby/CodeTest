package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SMSTemplate.class)
public class SMSTemplate_ {
	public static volatile SingularAttribute<SMSTemplate, Long> id;
	public static volatile SingularAttribute<SMSTemplate, Boolean> active;
	public static volatile SingularAttribute<SMSTemplate, String> name;
	public static volatile SingularAttribute<SMSTemplate, String> body;
	public static volatile SingularAttribute<SMSTemplate, Date> createdOn;
	public static volatile SingularAttribute<SMSTemplate, String> createdBy;
	public static volatile SingularAttribute<SMSTemplate, Date> modifiedOn;
	public static volatile SingularAttribute<SMSTemplate, String> modifiedBy;
}
