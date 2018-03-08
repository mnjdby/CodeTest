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
import javax.persistence.OneToMany;

import com.fullerton.olp.model.base.BaseModel;

/**
 * The persistent class for the city database table.
 * 
 */
@Entity
public class City extends BaseModel<Long, City> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Boolean active;// = Boolean.TRUE;

	private String name;

	//bi-directional many-to-one association to State
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="state_id")
	private State state;

	//bi-directional many-to-one association to IndustryType
	@OneToMany(mappedBy="city")
	private List<IndustryType> industryTypes;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tier_id" )
	private Tier tier;


	//bi-directional many-to-one association to Pincode
	@OneToMany(mappedBy="city")
	private List<Pincode> pincodes;

	//bi-directional many-to-one association to UserDetail
	@OneToMany(mappedBy="city")
	private List<UserDetail> userDetails;

	public City() {
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

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<IndustryType> getIndustryTypes() {
		return this.industryTypes;
	}

	public void setIndustryTypes(List<IndustryType> industryTypes) {
		this.industryTypes = industryTypes;
	}

	public IndustryType addIndustryType(IndustryType industryType) {
		getIndustryTypes().add(industryType);
		industryType.setCity(this);

		return industryType;
	}

	public IndustryType removeIndustryType(IndustryType industryType) {
		getIndustryTypes().remove(industryType);
		industryType.setCity(null);

		return industryType;
	}


	public Tier getTier() {
		return tier;
	}

	public void setTier(Tier tier) {
		this.tier = tier;
	}

	public List<Pincode> getPincodes() {
		return this.pincodes;
	}

	public void setPincodes(List<Pincode> pincodes) {
		this.pincodes = pincodes;
	}

	public Pincode addPincode(Pincode pincode) {
		getPincodes().add(pincode);
		pincode.setCity(this);

		return pincode;
	}

	public Pincode removePincode(Pincode pincode) {
		getPincodes().remove(pincode);
		pincode.setCity(null);

		return pincode;
	}

	public List<UserDetail> getUserDetails() {
		return this.userDetails;
	}

	public void setUserDetails(List<UserDetail> userDetails) {
		this.userDetails = userDetails;
	}

	public UserDetail addUserDetail(UserDetail userDetail) {
		getUserDetails().add(userDetail);
		userDetail.setCity(this);

		return userDetail;
	}

	public UserDetail removeUserDetail(UserDetail userDetail) {
		getUserDetails().remove(userDetail);
		userDetail.setCity(null);

		return userDetail;
	}

	@Override
	public City copy() {
		City city = new City();
		copyTo(city);
		return city;
	}

	@Override
	public void copyTo(City city) {
		city.setId(getId());
		city.setName(getName());
		city.setActive(getActive());
		city.setState(getState()!=null?getState().copy():null);
		city.setTier(getTier()!=null?getTier().copy():null);
		
	}
}