package com.fullerton.olp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fullerton.olp.model.base.BaseModel;

/**
 * The persistent class for the industry_type database table.
 * 
 */
@Entity
@Table(name="industry_type")
@NamedQuery(name="IndustryType.findAll", query="SELECT i FROM IndustryType i")
public class IndustryType extends BaseModel<Long, IndustryType> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Boolean active;

	private String name;

	//bi-directional many-to-one association to Company
	@OneToMany(mappedBy="industryType")
	private List<Company> companies;

	//bi-directional many-to-one association to City
	@ManyToOne
	private City city;

	public IndustryType() {
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

	public List<Company> getCompanies() {
		return this.companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public Company addCompany(Company company) {
		getCompanies().add(company);
		company.setIndustryType(this);

		return company;
	}

	public Company removeCompany(Company company) {
		getCompanies().remove(company);
		company.setIndustryType(null);

		return company;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public IndustryType copy() {
		IndustryType industryType = new IndustryType();
		copyTo(industryType);
		return industryType;
	}

	@Override
	public void copyTo(IndustryType industryType) {
		industryType.setId(getId());
		industryType.setName(getName());
		industryType.setActive(getActive());
		industryType.setCity(getCity()!=null?getCity().copy():null);
		
	}
}