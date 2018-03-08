/**
 * 
 */
package com.fullerton.olp.service;

import java.util.Collection;
import java.util.Map;

import com.fullerton.olp.model.SMSTemplate;

/**
 * @author saurabh.shastri
 *
 */
public interface SMSTemplateService extends GenericEntityService<SMSTemplate, Long> {

	SMSTemplate findById(Long id);
	
    Collection<SMSTemplate> findAll();
    
    String getFormattedText(Long smsTemplateId, Map<String, String> data);
}
