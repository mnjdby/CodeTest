package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.UserDocument;
import com.fullerton.olp.service.GenericEntityService;

public interface UserDocumentService extends GenericEntityService<UserDocument, Long>{

	UserDocument findById(Long id);
	
    Collection<UserDocument> findAll();
    
    void markInactive(Long userDetailId, Long documentId, Long documentTypeId);
}
