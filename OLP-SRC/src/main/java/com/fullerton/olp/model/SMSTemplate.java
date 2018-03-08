package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fullerton.olp.model.base.BaseModel;

/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@Table(name="sms_template")
public class SMSTemplate extends BaseModel<Long, SMSTemplate> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String body;

	private Boolean active;// = Boolean.TRUE;

	public SMSTemplate() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public SMSTemplate copy() {
		SMSTemplate smsTemplate = new SMSTemplate();
		copyTo(smsTemplate);
		return smsTemplate;
	}

	@Override
	public void copyTo(SMSTemplate smsTemplate) {
		smsTemplate.setId(getId());
		smsTemplate.setName(getName());
		smsTemplate.setBody(getBody());
		smsTemplate.setActive(getActive());
	}
}