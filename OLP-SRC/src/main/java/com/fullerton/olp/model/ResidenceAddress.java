package com.fullerton.olp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fullerton.olp.model.base.BaseModel;

/**
 * The persistent class for the residence_address database table.
 * 
 */
@Entity
@Table(name="residence_address")
@NamedQuery(name="ResidenceAddress.findAll", query="SELECT r FROM ResidenceAddress r")
public class ResidenceAddress extends BaseModel<Long, ResidenceAddress>  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="address_1")
	private String address1;

	@Column(name="address_2")
	private String address2;

	@Column(name="address_3")
	private String address3;

	@Temporal(TemporalType.DATE)
	@Column(name="living_since")
	private Date livingSince;

	//bi-directional many-to-one association to UserDetail
	@OneToMany(mappedBy="residenceAddress")
	private List<PersonalDetail> personalDetails;
	
	@ManyToOne
	@JoinColumn(name="residence_type_id")
	private ResidenceType residenceType;
	
	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;

	@ManyToOne
	@JoinColumn(name="pincode_id")
	private Pincode pincode;

	public ResidenceAddress() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return this.address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
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

	public Date getLivingSince() {
		return this.livingSince;
	}

	public void setLivingSince(Date livingSince) {
		this.livingSince = livingSince;
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

	 
	public List<PersonalDetail> getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(List<PersonalDetail> personalDetails) {
		this.personalDetails = personalDetails;
	}

	public ResidenceType getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(ResidenceType residenceType) {
		this.residenceType = residenceType;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Pincode getPincode() {
		return pincode;
	}

	public void setPincode(Pincode pincode) {
		this.pincode = pincode;
	}

	public PersonalDetail addPersonalDetail(PersonalDetail personalDetail) {
		getPersonalDetails().add(personalDetail);
		personalDetail.setResidenceAddress(this);

		return personalDetail;
	}

	public PersonalDetail removePersonalDetail(PersonalDetail personalDetail) {
		getPersonalDetails().remove(personalDetail);
		personalDetail.setResidenceAddress(null);

		return personalDetail;
	}

	@Override
	public ResidenceAddress copy() {
		ResidenceAddress residenceAddress = new ResidenceAddress();
		copyTo(residenceAddress);
		return residenceAddress;
	}

	@Override
	public void copyTo(ResidenceAddress residenceAddress) {
		residenceAddress.setId(getId());
		residenceAddress.setAddress1(getAddress1());
		residenceAddress.setAddress2(getAddress2());
		residenceAddress.setAddress3(getAddress3());
		residenceAddress.setLivingSince(getLivingSince());
		residenceAddress.setResidenceType(getResidenceType()!=null?getResidenceType().copy():null);
		residenceAddress.setCity(getCity()!=null?getCity().copy():null);
		residenceAddress.setPincode(getPincode()!=null?getPincode().copy():null);
		
		
	}
}