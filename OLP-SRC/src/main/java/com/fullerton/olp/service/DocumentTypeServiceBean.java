package com.fullerton.olp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.DocumentType;
import com.fullerton.olp.repository.DocumentTypeRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DocumentTypeServiceBean extends GenericEntityServiceImpl<DocumentType, Long> implements DocumentTypeService {

    /**
     * The Spring Data repository for DocumentType entities.
     */
    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    /**
     * Find and return all documentTypes
     * @return collection of all documentTypes
     */
    @Override
    public Collection<DocumentType> findAll() {
        Collection<DocumentType> documentTypes = documentTypeRepository.find(new DocumentType(), new SearchTemplate());
        return documentTypes;
    }

    @Override
    public DocumentType findById(Long id) {
		DocumentType documentType = documentTypeRepository.findOne(id);
        return documentType;
    }
	
	@Override
	public GenericDao<DocumentType, Long> getDao() {
		// TODO Auto-generated method stub
		return documentTypeRepository;
	}

	@Override
	public DocumentType getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentType getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}