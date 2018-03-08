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
@Table(name="application_status")
public class ApplicationStatus extends BaseModel<Long, ApplicationStatus> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String status;

	private String detail;

	private Boolean active;// = Boolean.TRUE;

	public ApplicationStatus() {
	}

	public ApplicationStatus(Long id) {
		super();
		this.id = id;
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
 

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public ApplicationStatus copy() {
		ApplicationStatus smsTemplate = new ApplicationStatus();
		copyTo(smsTemplate);
		return smsTemplate;
	}

	@Override
	public void copyTo(ApplicationStatus smsTemplate) {
		smsTemplate.setId(getId());
		smsTemplate.setStatus(getStatus());
		smsTemplate.setDetail(getDetail());
		smsTemplate.setActive(getActive());
	}
}