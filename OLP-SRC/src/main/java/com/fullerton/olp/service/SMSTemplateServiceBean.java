package com.fullerton.olp.service;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fullerton.olp.model.SMSTemplate;
import com.fullerton.olp.repository.GenericDao;
import com.fullerton.olp.repository.SMSTemplateRepository;
import com.fullerton.olp.repository.support.SearchTemplate;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SMSTemplateServiceBean extends GenericEntityServiceImpl<SMSTemplate, Long> implements SMSTemplateService {

	/**
	 * The Spring Data repository for SMSTemplate entities.
	 */
	@Autowired
	private SMSTemplateRepository smsTemplateRepository;

	@Override
	public String getFormattedText(Long smsTemplateId, Map<String, String> data) {
		
		String sb = getById(smsTemplateId).getBody();
		
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
	public Collection<SMSTemplate> findAll() {
		Collection<SMSTemplate> templates = smsTemplateRepository.find(new SMSTemplate(), new SearchTemplate());
		return templates;
	}

	@Override
	public SMSTemplate findById(Long id) {
		SMSTemplate template = smsTemplateRepository.findOne(id);
		return template;
	}

	@Override
	public GenericDao<SMSTemplate, Long> getDao() {
		// TODO Auto-generated method stub
		return smsTemplateRepository;
	}

	@Override
	public SMSTemplate getNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SMSTemplate getNewWithDefaults() {
		// TODO Auto-generated method stub
		return null;
	}
}