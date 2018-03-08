package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EmailTemplate.class)
public class EmailTemplate_ {
	public static volatile SingularAttribute<EmailTemplate, Long> id;
	public static volatile SingularAttribute<EmailTemplate, Boolean> active;
	public static volatile SingularAttribute<EmailTemplate, String> name;
	public static volatile SingularAttribute<EmailTemplate, String> subject;
	public static volatile SingularAttribute<EmailTemplate, String> body;
	public static volatile SingularAttribute<EmailTemplate, Date> createdOn;
	public static volatile SingularAttribute<EmailTemplate, String> createdBy;
	public static volatile SingularAttribute<EmailTemplate, Date> modifiedOn;
	public static volatile SingularAttribute<EmailTemplate, String> modifiedBy;
}
