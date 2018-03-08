package com.fullerton.olp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fullerton.olp.model.base.BaseModel;

/**
 * The persistent class for the professional_detail database table.
 * 
 */
@Entity
@Table(name="professional_detail")
@NamedQuery(name="ProfessionalDetail.findAll", query="SELECT p FROM ProfessionalDetail p")
public class ProfessionalDetail extends BaseModel<Long, ProfessionalDetail>  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="annual_income")
	private int annualIncome;

	@Column(name="monthly_income")
	private int monthlyIncome;

	@Column(name="other_income")
	private int otherIncome;
	
	@Column(name="pan_number")
	private String panNumber;
	

	@Column(name="pan_verified")
	private Boolean panVerified = Boolean.FALSE;
	

	@Column(name="registration_number")
	private String registrationNumber;
	
	//bi-directional many-to-one association to WorkAddress
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="work_address_id")
	private WorkAddress workAddress;

	//bi-directional many-to-one association to UserDetail
	@OneToMany(mappedBy="professionalDetail",fetch=FetchType.LAZY)
	private List<UserDetail> userDetails;

	public ProfessionalDetail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAnnualIncome() {
		return this.annualIncome;
	}

	public void setAnnualIncome(int annualIncome) {
		this.annualIncome = annualIncome;
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

	public int getMonthlyIncome() {
		return this.monthlyIncome;
	}

	public void setMonthlyIncome(int monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public int getOtherIncome() {
		return this.otherIncome;
	}

	public void setOtherIncome(int otherIncome) {
		this.otherIncome = otherIncome;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public Boolean getPanVerified() {
		return panVerified;
	}

	public void setPanVerified(Boolean panVerified) {
		this.panVerified = panVerified;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public WorkAddress getWorkAddress() {
		return this.workAddress;
	}

	public void setWorkAddress(WorkAddress workAddress) {
		this.workAddress = workAddress;
	}

	public List<UserDetail> getUserDetails() {
		return this.userDetails;
	}

	public void setUserDetails(List<UserDetail> userDetails) {
		this.userDetails = userDetails;
	}

	public UserDetail addUserDetail(UserDetail userDetail) {
		getUserDetails().add(userDetail);
		userDetail.setProfessionalDetail(this);

		return userDetail;
	}

	public UserDetail removeUserDetail(UserDetail userDetail) {
		getUserDetails().remove(userDetail);
		userDetail.setProfessionalDetail(null);

		return userDetail;
	}

	@Override
	public ProfessionalDetail copy() {
		ProfessionalDetail professionalDetail = new ProfessionalDetail();
		copyTo(professionalDetail);
		return professionalDetail;
	}
	public ProfessionalDetail shortCopy() {
		ProfessionalDetail professionalDetail = new ProfessionalDetail();
		shortCopyTo(professionalDetail);
		return professionalDetail;
	}

	@Override
	public void copyTo(ProfessionalDetail professionalDetail) {
		professionalDetail.setId(getId());
		professionalDetail.setAnnualIncome(getAnnualIncome());
		professionalDetail.setMonthlyIncome(getMonthlyIncome());
		professionalDetail.setOtherIncome(getOtherIncome());
		professionalDetail.setPanNumber(getPanNumber());
		professionalDetail.setPanVerified(getPanVerified());
		professionalDetail.setRegistrationNumber(getRegistrationNumber());
		
		professionalDetail.setWorkAddress(getWorkAddress() !=null?getWorkAddress().copy():null);
	}
	
	public void shortCopyTo(ProfessionalDetail professionalDetail) {
		professionalDetail.setId(getId());
		professionalDetail.setAnnualIncome(getAnnualIncome());
		professionalDetail.setMonthlyIncome(getMonthlyIncome());
		professionalDetail.setOtherIncome(getOtherIncome());
		professionalDetail.setPanNumber(getPanNumber());
		professionalDetail.setPanVerified(getPanVerified());
		professionalDetail.setRegistrationNumber(getRegistrationNumber());
		
	}

}