package com.fullerton.olp.model;

import java.util.ArrayList;
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
 * The persistent class for the user_detail database table.
 * 
 */
@Entity
@Table(name="user_detail")
@NamedQuery(name="UserDetail.findAll", query="SELECT u FROM UserDetail u")
public class UserDetail extends BaseModel<Long,UserDetail>  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="aadhaar_number")
	private String aadhaarNumber;

	private Boolean active;

	private Boolean faviorate;
	
	@Column(name="loan_amount")
	private Double loanAmount;

	private Integer tenure;

	private String email;

	private String mobile;
	
	private String name;
	
	@Column(name="ip_address")
	private String ipAddress;
	
	@Column(name="aadhaar_tx_id")
	private String aadhaarTxId;
	

	@Column(name="user_verified")
	private Boolean userVerified;
	
	@Column(name="aadhaar_verified")
	private Boolean aadhaarVerified;
	
	@Column(name="cp_referance_number")
	private String cpReferanceNumber;
	
	@Column(name="bre_status")
	private String breStatus;
	
	@Column(name="crm_lead_id")
	private String crmLeadId;
	
	
	//bi-directional many-to-one association to City
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="city_id")
	private City city;

//	//bi-directional many-to-one association to Gender
//	@ManyToOne
//	@JoinColumn(name="gender_id")
//	private Gender gender;
//
//	//bi-directional many-to-one association to MarritalStatus
//	@ManyToOne
//	@JoinColumn(name="marrital_status_id")
//	private MarritalStatus marritalStatus;

	//bi-directional many-to-one association to Profession
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="profession_id")
	private Profession profession;

	//bi-directional many-to-one association to Profession
		@ManyToOne(fetch=FetchType.EAGER)
		@JoinColumn(name="application_status_id")
		private ApplicationStatus applicationStatus;
		
	//bi-directional many-to-one association to ProfessionalDetail
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="professional_detail_id")
	private ProfessionalDetail professionalDetail;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="personal_detail_id")
	private PersonalDetail personalDetail;
	
	//bi-directional many-to-one association to Qualification
	@ManyToOne
	@JoinColumn(name="qualification_id")
	private Qualification qualification;
	
	//bi-directional many-to-one association to IndustryType
	@OneToMany(mappedBy="userDetail")
	private List<UserDocument> userDocuments;

	//bi-directional many-to-one association to ResidenceAddress
//	@ManyToOne
//	@JoinColumn(name="residence_address_id")
//	private ResidenceAddress residenceAddress;

	public UserDetail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getAadhaarNumber() {
		return this.aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public Boolean getUserVerified() {
		return userVerified;
	}

	public void setUserVerified(Boolean userVerified) {
		this.userVerified = userVerified;
	}

	public Boolean getAadhaarVerified() {
		return aadhaarVerified;
	}

	public void setAadhaarVerified(Boolean aadhaarVerified) {
		this.aadhaarVerified = aadhaarVerified;
	}

	public String getCpReferanceNumber() {
		return cpReferanceNumber;
	}

	public void setCpReferanceNumber(String cpReferanceNumber) {
		this.cpReferanceNumber = cpReferanceNumber;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	 

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Profession getProfession() {
		return this.profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public ProfessionalDetail getProfessionalDetail() {
		return this.professionalDetail;
	}

	public void setProfessionalDetail(ProfessionalDetail professionalDetail) {
		this.professionalDetail = professionalDetail;
	}

	public Qualification getQualification() {
		return this.qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	 

	public String getAadhaarTxId() {
		return aadhaarTxId;
	}

	public void setAadhaarTxId(String aadhaarTxId) {
		this.aadhaarTxId = aadhaarTxId;
	}

	public PersonalDetail getPersonalDetail() {
		return personalDetail;
	}

	public void setPersonalDetail(PersonalDetail personalDetail) {
		this.personalDetail = personalDetail;
	}

	public List<UserDocument> getUserDocuments() {
		return userDocuments;
	}

	public void setUserDocuments(List<UserDocument> userDocuments) {
		this.userDocuments = userDocuments;
	}

	public String getBreStatus() {
		return breStatus;
	}

	public void setBreStatus(String breStatus) {
		this.breStatus = breStatus;
	}

	public String getCrmLeadId() {
		return crmLeadId;
	}

	public void setCrmLeadId(String crmLeadId) {
		this.crmLeadId = crmLeadId;
	}

	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(ApplicationStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public Boolean getFaviorate() {
		return faviorate;
	}

	public void setFaviorate(Boolean faviorate) {
		this.faviorate = faviorate;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	@Override
	public UserDetail copy() {
		UserDetail userDetail = new UserDetail();
		copyTo(userDetail);
		return userDetail;
	}
	
	public UserDetail shortCopy() {
		UserDetail userDetail = new UserDetail();
		shortCopyTo(userDetail);
		return userDetail;
	}

	@Override
	public void copyTo(UserDetail userDetail) {
		userDetail.setId(getId());
		userDetail.setName(getName());
		userDetail.setActive(getActive());
		userDetail.setFaviorate(getFaviorate());
		userDetail.setLoanAmount(getLoanAmount());
		userDetail.setTenure(getTenure());
		userDetail.setAadhaarNumber(getAadhaarNumber());//TODO check if need to remove purposely from copying
		userDetail.setAadhaarVerified(getAadhaarVerified());//TODO check if need to remove purposely from copying
		userDetail.setEmail(getEmail());
		userDetail.setMobile(getMobile());
		userDetail.setCrmLeadId(getCrmLeadId());
		userDetail.setBreStatus(getBreStatus());
		userDetail.setMobile(getMobile());
		userDetail.setCity(getCity()!=null?getCity().copy():null);
		userDetail.setProfession(getProfession()!=null?getProfession().copy():null);
		userDetail.setProfessionalDetail(getProfessionalDetail()!=null?getProfessionalDetail().copy():null);
		userDetail.setPersonalDetail(getPersonalDetail()!=null?getPersonalDetail().copy():null);
		userDetail.setQualification(getQualification()!=null?getQualification().copy():null);
		userDetail.setApplicationStatus(getApplicationStatus());
		
		List<UserDocument> documents = new ArrayList<UserDocument>();
		if(userDocuments != null) {
			for (UserDocument userDocument : userDocuments) {
				documents.add(userDocument.copy());
			}
		}
		userDetail.setUserDocuments(documents);
	}
	
	public void shortCopyTo(UserDetail userDetail) {
		userDetail.setId(getId());
		userDetail.setName(getName());
		userDetail.setActive(getActive());
		userDetail.setFaviorate(getFaviorate());
		userDetail.setLoanAmount(getLoanAmount());
		userDetail.setTenure(getTenure());
		userDetail.setEmail(getEmail());
		userDetail.setMobile(getMobile());
		userDetail.setCrmLeadId(getCrmLeadId());
		userDetail.setBreStatus(getBreStatus());
		userDetail.setMobile(getMobile());
		userDetail.setQualification(getQualification()!=null?getQualification().copy():null);
		userDetail.setCity(getCity()!=null?getCity().copy():null);
		userDetail.setApplicationStatus(getApplicationStatus());
		//userDetail.setProfession(getProfession()!=null?getProfession().copy():null);
		//userDetail.setProfessionalDetail(getProfessionalDetail()!=null?getProfessionalDetail().copy():null);
	}
}