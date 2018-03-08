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
 * The persistent class for the state database table.
 * 
 */
@Entity
@NamedQuery(name="State.findAll", query="SELECT s FROM State s")
public class State extends BaseModel<Long, State> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Boolean active;// = Boolean.TRUE;

	private String name;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="state", fetch = FetchType.LAZY)
	private List<City> cities;

	//bi-directional many-to-one association to Territory
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="territory_id")
	private Territory territory;

	public State() {
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

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public City addCity(City city) {
		getCities().add(city);
		city.setState(this);

		return city;
	}

	public City removeCity(City city) {
		getCities().remove(city);
		city.setState(null);

		return city;
	}

	public Territory getTerritory() {
		return this.territory;
	}

	public void setTerritory(Territory territory) {
		this.territory = territory;
	}

	@Override
	public State copy() {
		State state = new State();
		copyTo(state);
		return state;
	}

	@Override
	public void copyTo(State state) {
		state.setId(getId());
		state.setName(getName());
		state.setActive(getActive());
		state.setTerritory(getTerritory().copy());
	}
}