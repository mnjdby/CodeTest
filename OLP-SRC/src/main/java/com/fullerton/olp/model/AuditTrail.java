/**
 * 
 */
package com.fullerton.olp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fullerton.olp.model.base.BaseModel;

/**
 * @author saurabh.shastri
 *
 */
@Entity
@Table(name = "audit_trail")
public class AuditTrail extends BaseModel<Long, AuditTrail> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String action;
	private String entity;

	@Column(name = "action_details")
	private String actionDetails;

	public AuditTrail() {
	}
	
	public AuditTrail(String entity, String action, String actionDetails){
		this.entity=entity;
		this.action=action;
		this.actionDetails=actionDetails;		
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActionDetails() {
		return actionDetails;
	}

	public void setActionDetails(String actionDetails) {
		this.actionDetails = actionDetails;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	@Override
	public AuditTrail copy() {
		AuditTrail auditTrail = new AuditTrail();
		copyTo(auditTrail);
		return auditTrail;
	}

	@Override
	public void copyTo(AuditTrail auditTrail) {
		auditTrail.setId(getId());
		auditTrail.setAction(getAction());
		auditTrail.setEntity(getEntity());
		auditTrail.setActionDetails(getActionDetails());
	}
}
