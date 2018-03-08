package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.UserDocument;
import com.fullerton.olp.repository.UserDocumentRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;
import com.fullerton.olp.service.GenericEntityServiceImpl;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserDocumentServiceBean extends GenericEntityServiceImpl<UserDocument, Long> implements UserDocumentService {

    /**
     * The Spring Data repository for UserDocument entities.
     */
    @Autowired
    private UserDocumentRepository userDocumentRepository;

    @Override
    @Transactional
    public void markInactive(Long userDetailId, Long documentId, Long documentTypeId) {
    		userDocumentRepository.markInactive(userDetailId, documentTypeId, documentId);
    	
    }
    /**
     * Find and return all userDocuments
     * @return collection of all userDocuments
     */
    @Override
    public Collection<UserDocument> findAll() {
        Collection<UserDocument> userDocuments = userDocumentRepository.find(new UserDocument(), new SearchTemplate());
        return userDocuments;
    }

    @Override
    public UserDocument findById(Long id) {
		UserDocument userDocument = userDocumentRepository.findOne(id);
        return userDocument;
    }
	
	@Override
	public GenericDao<UserDocument, Long> getDao() {
		// TODO Auto-generated method stub
		return userDocumentRepository;
	}

	@Override
	public UserDocument getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDocument getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}