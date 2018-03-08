package com.fullerton.olp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fullerton.olp.model.base.BaseModel;

/**
 * The persistent class for the industry_type database table.
 * 
 */
@Entity
@Table(name="document_type")
public class DocumentType extends BaseModel<Long, DocumentType> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Boolean active;

	private String name;

	@Transient
	private Boolean uploaded = Boolean.FALSE;
	//uni-directional many-to-one association to document
	@ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="document_type_has_document", joinColumns=@JoinColumn(name="document_type_id"), inverseJoinColumns=@JoinColumn(name="document_id"))  
	private List<Document> documents;

	public DocumentType() {
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


	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public Boolean getUploaded() {
		return uploaded;
	}

	public void setUploaded(Boolean uploaded) {
		this.uploaded = uploaded;
	}

	@Override
	public DocumentType copy() {
		DocumentType documentType = new DocumentType();
		copyTo(documentType);
		return documentType;
	}

	@Override
	public void copyTo(DocumentType documentType) {
		documentType.setId(getId());
		documentType.setName(getName());
		documentType.setActive(getActive());
		documentType.setUploaded(getUploaded());
		
		List<Document> documentList = new ArrayList<>();
		for (Document document : documents) {
			documentList.add(document.copy());
		}
		documentType.setDocuments(documentList);
		
	}
}