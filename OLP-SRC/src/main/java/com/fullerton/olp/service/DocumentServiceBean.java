package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.Document;
import com.fullerton.olp.repository.DocumentRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DocumentServiceBean extends GenericEntityServiceImpl<Document, Long> implements DocumentService {

    /**
     * The Spring Data repository for Document entities.
     */
    @Autowired
    private DocumentRepository documentRepository;

    /**
     * Find and return all documents
     * @return collection of all documents
     */
    @Override
    public Collection<Document> findAll() {
        Collection<Document> documents = documentRepository.find(new Document(), new SearchTemplate());
        return documents;
    }

    @Override
    public Document findById(Long id) {
		Document document = documentRepository.findOne(id);
        return document;
    }
	
	@Override
	public GenericDao<Document, Long> getDao() {
		// TODO Auto-generated method stub
		return documentRepository;
	}

	@Override
	public Document getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}