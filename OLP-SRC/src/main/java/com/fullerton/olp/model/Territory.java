package com.fullerton.olp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fullerton.olp.model.base.BaseModel;

/**
 * The persistent class for the territory database table.
 * 
 */
@Entity
@NamedQuery(name="Territory.findAll", query="SELECT t FROM Territory t")
public class Territory extends BaseModel<Long,Territory>  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Boolean active;// = Boolean.TRUE;

	private String name;

	//bi-directional many-to-one association to State
	@OneToMany(mappedBy="territory", fetch = FetchType.LAZY)
	private List<State> states;

	public Territory() {
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

	public List<State> getStates() {
		return this.states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public State addState(State state) {
		getStates().add(state);
		state.setTerritory(this);

		return state;
	}

	public State removeState(State state) {
		getStates().remove(state);
		state.setTerritory(null);

		return state;
	}

	@Override
	public Territory copy() {
		Territory territory = new Territory();
		copyTo(territory);
		return territory;
	}

	@Override
	public void copyTo(Territory territory) {
		territory.setId(getId());
		territory.setName(getName());
		territory.setActive(getActive());
	}
}