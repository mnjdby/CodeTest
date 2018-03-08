package com.fullerton.olp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.jpa.internal.metamodel.PluralAttributeImpl;

@StaticMetamodel(MarritalStatus.class)
public class MarritalStatus_ {
	public static volatile SingularAttribute<MarritalStatus, Long> id;
	public static volatile SingularAttribute<MarritalStatus, Boolean> active;
	public static volatile SingularAttribute<MarritalStatus, String> name;
	public static volatile PluralAttributeImpl<MarritalStatus, PersonalDetail, Set<PersonalDetail>> personalDetails;
	public static volatile SingularAttribute<MarritalStatus, Date> createdOn;
	public static volatile SingularAttribute<MarritalStatus, String> createdBy;
	public static volatile SingularAttribute<MarritalStatus, Date> modifiedOn;
	public static volatile SingularAttribute<MarritalStatus, String> modifiedBy;
}
