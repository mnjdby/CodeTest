package com.fullerton.olp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fullerton.olp.model.base.BaseModel;

/**
 * The persistent class for the profession database table.
 * 
 */
@Entity
@NamedQuery(name="Profession.findAll", query="SELECT p FROM Profession p")
public class Profession extends BaseModel<Long, Profession> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Boolean active;

	private String name;

	//bi-directional many-to-one association to Qualification
	@OneToMany(mappedBy="profession")
	private List<Qualification> qualifications;

	//bi-directional many-to-one association to UserDetail
	@OneToMany(mappedBy="profession")
	private List<UserDetail> userDetails;

	
	@ManyToOne
	@JoinColumn(name="loan_duration_id")
	private LoanDuration loanDuration;
	
	
	 
	public Profession() {
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


	public List<Qualification> getQualifications() {
		return qualifications;
	}

	public void setQualifications(List<Qualification> qualifications) {
		this.qualifications = qualifications;
	}

	public List<UserDetail> getUserDetails() {
		return this.userDetails;
	}

	public void setUserDetails(List<UserDetail> userDetails) {
		this.userDetails = userDetails;
	}

	public LoanDuration getLoanDuration() {
		return loanDuration;
	}

	public void setLoanDuration(LoanDuration loanDuration) {
		this.loanDuration = loanDuration;
	}

	public Qualification addQualification(Qualification qualification) {
		getQualifications().add(qualification);
		qualification.setProfession(this);

		return qualification;
	}

	public Qualification removeProfession(Qualification qualification) {
		getQualifications().remove(qualification);
		qualification.setProfession(null);

		return qualification;
	}
	
	public UserDetail addUserDetail(UserDetail userDetail) {
		getUserDetails().add(userDetail);
		userDetail.setProfession(this);

		return userDetail;
	}

	public UserDetail removeUserDetail(UserDetail userDetail) {
		getUserDetails().remove(userDetail);
		userDetail.setProfession(null);

		return userDetail;
	}

	@Override
	public Profession copy() {
		Profession profession = new Profession();
		copyTo(profession);
		return profession;
	}

	@Override
	public void copyTo(Profession profession) {
		profession.setId(getId());
		profession.setName(getName());
		profession.setActive(getActive());
		profession.setLoanDuration(getLoanDuration()!=null?getLoanDuration().copy():null);
	}
}