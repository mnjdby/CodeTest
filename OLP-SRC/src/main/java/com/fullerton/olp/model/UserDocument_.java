package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserDocument.class)
public class UserDocument_ {
	public static volatile SingularAttribute<UserDocument, Long> id;
	public static volatile SingularAttribute<UserDocument, Boolean> active;
	public static volatile SingularAttribute<UserDocument, String> fileLocation;
	public static volatile SingularAttribute<UserDocument, Document> document;
	public static volatile SingularAttribute<UserDocument, DocumentType> documentType;
	public static volatile SingularAttribute<UserDocument, UserDetail> userDetail;
	public static volatile SingularAttribute<UserDocument, Date> createdOn;
	public static volatile SingularAttribute<UserDocument, String> createdBy;
	public static volatile SingularAttribute<UserDocument, Date> modifiedOn;
	public static volatile SingularAttribute<UserDocument, String> modifiedBy;
}
