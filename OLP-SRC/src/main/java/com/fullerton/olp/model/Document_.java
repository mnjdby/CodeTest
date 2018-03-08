package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Document.class)
public class Document_ {
	public static volatile SingularAttribute<Document, Long> id;
	public static volatile SingularAttribute<Document, Boolean> active;
	public static volatile SingularAttribute<Document, String> name;
	public static volatile SingularAttribute<Document, Date> createdOn;
	public static volatile SingularAttribute<Document, String> createdBy;
	public static volatile SingularAttribute<Document, Date> modifiedOn;
	public static volatile SingularAttribute<Document, String> modifiedBy;
}
