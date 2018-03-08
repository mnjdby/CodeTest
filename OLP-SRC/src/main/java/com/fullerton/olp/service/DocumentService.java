package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.Document;

public interface DocumentService extends GenericEntityService<Document, Long>{

	Document findById(Long id);
	
    Collection<Document> findAll();

}
