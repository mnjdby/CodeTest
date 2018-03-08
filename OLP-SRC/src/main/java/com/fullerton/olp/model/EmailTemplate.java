package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fullerton.olp.model.base.BaseModel;

/**
 * The persistent class for the emailtemplate database table.
 * 
 */
@Entity
@Table(name="email_template")
public class EmailTemplate extends BaseModel<Long, EmailTemplate> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String subject;

	private String body;

	private Boolean active;// = Boolean.TRUE;

	public EmailTemplate() {
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	

	@Override
	public EmailTemplate copy() {
		EmailTemplate emailTemplate = new EmailTemplate();
		copyTo(emailTemplate);
		return emailTemplate;
	}

	@Override
	public void copyTo(EmailTemplate emailTemplate) {
		emailTemplate.setId(getId());
		emailTemplate.setName(getName());
		emailTemplate.setSubject(getSubject());
		emailTemplate.setBody(getBody());
		emailTemplate.setActive(getActive());
	}
}