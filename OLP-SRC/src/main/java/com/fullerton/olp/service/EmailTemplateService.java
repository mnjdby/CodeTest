/**
 * 
 */
package com.fullerton.olp.service;

import java.util.Collection;
import java.util.Map;

import com.fullerton.olp.model.EmailTemplate;

/**
 * @author saurabh.shastri
 *
 */
public interface EmailTemplateService extends GenericEntityService<EmailTemplate, Long> {

	EmailTemplate findById(Long id);
	
    Collection<EmailTemplate> findAll();
    
	String getFormattedText(EmailTemplate emailTemplate, Map<String, String> data);
	
}
