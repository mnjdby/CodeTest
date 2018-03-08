package com.fullerton.olp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fullerton.olp.model.base.BaseModel;

/**
 * The persistent class for the profession database table.
 * 
 */
@Entity
@Table(name="residence_type")
public class ResidenceType extends BaseModel<Long, ResidenceType> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Boolean active;// = Boolean.TRUE;

	private String name;

	//bi-directional many-to-one association to Qualification
	@OneToMany(mappedBy="residenceType")
	private List<ResidenceAddress> residenceAddresses;

	public ResidenceType() {
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
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<ResidenceAddress> getResidenceAddresses() {
		return residenceAddresses;
	}

	public void setResidenceAddresses(List<ResidenceAddress> residenceAddresses) {
		this.residenceAddresses = residenceAddresses;
	}

	@Override
	public ResidenceType copy() {
		ResidenceType residenceType = new ResidenceType();
		copyTo(residenceType);
		return residenceType;
	}

	@Override
	public void copyTo(ResidenceType residenceType) {
		residenceType.setId(getId());
		residenceType.setName(getName());
		residenceType.setActive(getActive());
	}
}