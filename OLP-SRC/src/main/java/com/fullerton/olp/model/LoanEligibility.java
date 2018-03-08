package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fullerton.olp.model.base.BaseModel;

/**
 * The persistent class for the loan_eligibility database table.
 * 
 */
@Entity
@Table(name="loan_eligibility")
@NamedQuery(name="LoanEligibility.findAll", query="SELECT l FROM LoanEligibility l")
public class LoanEligibility extends BaseModel<Long, LoanEligibility> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Boolean active;

	@Column(name="max_amount")
	private Integer maxAmount;

	@Column(name="min_amount")
	private Integer minAmount;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="tier_id")
	private Tier tier;

	//bi-directional many-to-one association to Qualification
	@ManyToOne
	@JoinColumn(name="qualification_id")
	private Qualification qualification;

	public LoanEligibility() {
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

	public Integer getMaxAmount() {
		return this.maxAmount;
	}

	public void setMaxAmount(Integer maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Integer getMinAmount() {
		return this.minAmount;
	}

	public void setMinAmount(Integer minAmount) {
		this.minAmount = minAmount;
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


	public Tier getTier() {
		return tier;
	}

	public void setTier(Tier tier) {
		this.tier = tier;
	}

	public Qualification getQualification() {
		return this.qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	@Override
	public LoanEligibility copy() {
		LoanEligibility loanEligibility = new LoanEligibility();
		copyTo(loanEligibility);
		return loanEligibility;
	}

	@Override
	public void copyTo(LoanEligibility loanEligibility) {
		loanEligibility.setId(getId());
		loanEligibility.setActive(getActive());
		loanEligibility.setMinAmount(getMinAmount());
		loanEligibility.setMaxAmount(getMaxAmount());
		loanEligibility.setTier(getTier()!=null?getTier().copy():null);
		
		//loanEligibility.setCity(getCity());
		loanEligibility.setQualification(getQualification()!=null?getQualification().copy():null);
	}
}