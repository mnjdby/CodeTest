package com.fullerton.olp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.jpa.internal.metamodel.PluralAttributeImpl;

@StaticMetamodel(DocumentType.class)
public class DocumentType_ {
	public static volatile SingularAttribute<DocumentType, Long> id;
	public static volatile SingularAttribute<DocumentType, Boolean> active;
	public static volatile SingularAttribute<DocumentType, String> name;
	public static volatile PluralAttributeImpl<DocumentType, Document, Set<Document>> documents;
	public static volatile SingularAttribute<DocumentType, Date> createdOn;
	public static volatile SingularAttribute<DocumentType, String> createdBy;
	public static volatile SingularAttribute<DocumentType, Date> modifiedOn;
	public static volatile SingularAttribute<DocumentType, String> modifiedBy;
}
