package com.fullerton.olp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.jpa.internal.metamodel.PluralAttributeImpl;

@StaticMetamodel(WorkAddress.class)
public class WorkAddress_ {
	public static volatile SingularAttribute<WorkAddress, Long> id;
	public static volatile SingularAttribute<WorkAddress, String> address1;
	public static volatile SingularAttribute<WorkAddress, String> address2;
	public static volatile SingularAttribute<WorkAddress, String> address3;
	public static volatile SingularAttribute<WorkAddress, String> entityName;
	public static volatile SingularAttribute<WorkAddress, String> landline;
	public static volatile SingularAttribute<WorkAddress, Date> workingSince;
	public static volatile SingularAttribute<WorkAddress, City> city;
	public static volatile PluralAttributeImpl<WorkAddress, ProfessionalDetail, Set<ProfessionalDetail>> professionalDetails;
	public static volatile SingularAttribute<WorkAddress, Date> createdOn;
	public static volatile SingularAttribute<WorkAddress, String> createdBy;
	public static volatile SingularAttribute<WorkAddress, Date> modifiedOn;
	public static volatile SingularAttribute<WorkAddress, String> modifiedBy;
}
