package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.DocumentType;

public interface DocumentTypeService extends GenericEntityService<DocumentType, Long>{

	DocumentType findById(Long id);
	
    Collection<DocumentType> findAll();

}
