package com.fullerton.olp.service;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.EmailTemplate;
import com.fullerton.olp.repository.EmailTemplateRepository;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.support.SearchTemplate;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EmailTemplateServiceBean extends GenericEntityServiceImpl<EmailTemplate, Long> implements EmailTemplateService {

	/**
	 * The Spring Data repository for EmailTemplate entities.
	 */
	@Autowired
	private EmailTemplateRepository emailTemplateRepository;


	@Override
	public String getFormattedText(EmailTemplate emailTemplate, Map<String, String> data) {

		String sb = emailTemplate.getBody();
		
		for (String key : data.keySet()) {
			sb = sb.replaceAll("@@"+key+"@@", data.get(key));
		}
		
		return sb;
	}
	
	/**
	 * Find and return all templates
	 * 
	 * @return collection of all templates
	 */
	@Override
	public Collection<EmailTemplate> findAll() {
		Collection<EmailTemplate> templates = emailTemplateRepository.find(new EmailTemplate(), new SearchTemplate());
		return templates;
	}

	@Override
	public EmailTemplate findById(Long id) {
		EmailTemplate template = emailTemplateRepository.findOne(id);
		return template;
	}

	@Override
	public GenericDao<EmailTemplate, Long> getDao() {
		// TODO Auto-generated method stub
		return emailTemplateRepository;
	}

	@Override
	public EmailTemplate getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmailTemplate getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}