package com.fullerton.olp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fullerton.olp.model.base.BaseModel;

/**
 * The persistent class for the qualification database table.
 * 
 */
@Entity
@NamedQuery(name="Qualification.findAll", query="SELECT q FROM Qualification q")
public class Qualification extends BaseModel<Long, Qualification> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Boolean active;

	private String name;

	//bi-directional many-to-one association to LoanEligibility
	@OneToMany(mappedBy="qualification")
	private List<LoanEligibility> loanEligibilities;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="profession_id" )
	private Profession profession;

	//bi-directional many-to-one association to UserDetail
	@OneToMany(mappedBy="qualification")
	private List<UserDetail> userDetails;

	public Qualification() {
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

	public List<LoanEligibility> getLoanEligibilities() {
		return this.loanEligibilities;
	}

	public void setLoanEligibilities(List<LoanEligibility> loanEligibilities) {
		this.loanEligibilities = loanEligibilities;
	}

	public LoanEligibility addLoanEligibility(LoanEligibility loanEligibility) {
		getLoanEligibilities().add(loanEligibility);
		loanEligibility.setQualification(this);

		return loanEligibility;
	}

	public LoanEligibility removeLoanEligibility(LoanEligibility loanEligibility) {
		getLoanEligibilities().remove(loanEligibility);
		loanEligibility.setQualification(null);

		return loanEligibility;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public List<UserDetail> getUserDetails() {
		return this.userDetails;
	}

	public void setUserDetails(List<UserDetail> userDetails) {
		this.userDetails = userDetails;
	}

	public UserDetail addUserDetail(UserDetail userDetail) {
		getUserDetails().add(userDetail);
		userDetail.setQualification(this);

		return userDetail;
	}

	public UserDetail removeUserDetail(UserDetail userDetail) {
		getUserDetails().remove(userDetail);
		userDetail.setQualification(null);

		return userDetail;
	}

	@Override
	public Qualification copy() {
		Qualification qualification = new Qualification();
		copyTo(qualification);
		return qualification;
	}

	@Override
	public void copyTo(Qualification qualification) {
		qualification.setId(getId());
		qualification.setName(getName());
		qualification.setActive(getActive());
		//qualification.setProfession(getProfession());
	}
}