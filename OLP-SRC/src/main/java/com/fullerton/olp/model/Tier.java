package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fullerton.olp.model.base.BaseModel;

/**
 * The persistent class for the tier database table.
 * 
 */
@Entity
public class Tier extends BaseModel<Long, Tier> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Boolean active;// = Boolean.TRUE;

	private String name;
	
	@Column(name="min_fees")
	private Float minFees;
	
	@Column(name="max_fees")
	private Float maxFees;

	//@OneToOne(mappedBy="loanEligibility")
	@Transient
	private LoanEligibility loanEligibility;

	public Tier() {
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
  

	public Float getMinFees() {
		return minFees;
	}

	public void setMinFees(Float minFees) {
		this.minFees = minFees;
	}

	public Float getMaxFees() {
		return maxFees;
	}

	public void setMaxFees(Float maxFees) {
		this.maxFees = maxFees;
	}

	@Override
	public Tier copy() {
		Tier tier = new Tier();
		copyTo(tier);
		return tier;
	}

	@Override
	public void copyTo(Tier tier) {
		tier.setId(getId());
		tier.setName(getName());
		tier.setActive(getActive());
		tier.setMinFees(getMinFees());
		tier.setMaxFees(getMaxFees());
	}
}