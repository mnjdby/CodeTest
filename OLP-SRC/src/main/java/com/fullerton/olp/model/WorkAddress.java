package com.fullerton.olp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
 * The persistent class for the work_address database table.
 * 
 */
@Entity
@Table(name="work_address")
@NamedQuery(name="WorkAddress.findAll", query="SELECT w FROM WorkAddress w")
public class WorkAddress extends BaseModel<Long,WorkAddress>  {
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

	@Column(name="entity_name")
	private String entityName;

	@Temporal(TemporalType.DATE)
	@Column(name="working_since")
	private Date workingSince;

	private String landline;

	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;

	
	@ManyToOne
	@JoinColumn(name="pincode_id")
	private Pincode pincode;

	//bi-directional many-to-one association to ProfessionalDetail
	@OneToMany(mappedBy="workAddress")
	private List<ProfessionalDetail> professionalDetails;

	public WorkAddress() {
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

	public String getEntityName() {
		return this.entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
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

	public Date getWorkingSince() {
		return this.workingSince;
	}

	public void setWorkingSince(Date workingSince) {
		this.workingSince = workingSince;
	}

	public List<ProfessionalDetail> getProfessionalDetails() {
		return this.professionalDetails;
	}

	public void setProfessionalDetails(List<ProfessionalDetail> professionalDetails) {
		this.professionalDetails = professionalDetails;
	}

	public ProfessionalDetail addProfessionalDetail(ProfessionalDetail professionalDetail) {
		getProfessionalDetails().add(professionalDetail);
		professionalDetail.setWorkAddress(this);

		return professionalDetail;
	}

	public ProfessionalDetail removeProfessionalDetail(ProfessionalDetail professionalDetail) {
		getProfessionalDetails().remove(professionalDetail);
		professionalDetail.setWorkAddress(null);

		return professionalDetail;
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

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	@Override
	public WorkAddress copy() {
		WorkAddress workAddress = new WorkAddress();
		copyTo(workAddress);
		return workAddress;
	}

	@Override
	public void copyTo(WorkAddress workAddress) {
		workAddress.setId(getId());
		workAddress.setAddress1(getAddress1());
		workAddress.setAddress2(getAddress2());
		workAddress.setAddress3(getAddress3());
		workAddress.setEntityName(getEntityName());
		workAddress.setLandline(getLandline());
		workAddress.setWorkingSince(getWorkingSince());
		workAddress.setCity(getCity()!=null?getCity().copy():null);
		workAddress.setPincode(getPincode()!=null?getPincode().copy():null);
		
	}
}