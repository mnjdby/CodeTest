package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fullerton.olp.model.base.BaseModel;

/**
 * The persistent class for the industry_type database table.
 * 
 */
@Entity
@Table(name="user_document")
public class UserDocument extends BaseModel<Long, UserDocument> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Boolean active;

	@Column(name="file_location")
	private String fileLocation;


	@ManyToOne
	@JoinColumn(name="document_id" )
	private Document document;
	
	@ManyToOne
	@JoinColumn(name="document_type_id" )
	private DocumentType documentType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_detail_id")
	private UserDetail userDetail;

	
	public UserDocument() {
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


	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	@Override
	public UserDocument copy() {
		UserDocument userDocument = new UserDocument();
		copyTo(userDocument);
		return userDocument;
	}

	@Override
	public void copyTo(UserDocument userDocument) {
		userDocument.setId(getId());
		userDocument.setActive(getActive());
		userDocument.setFileLocation(getFileLocation());
		userDocument.setDocument(getDocument()!=null?getDocument().copy():null);
		userDocument.setDocumentType(getDocumentType()!=null?getDocumentType().copy():null);
//		userDocument.setUserDetail(getUserDetail()!=null?getUserDetail().copy():null);
		
	}
}