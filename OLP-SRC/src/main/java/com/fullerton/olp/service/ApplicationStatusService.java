/**
 * 
 */
package com.fullerton.olp.service;

import java.util.Collection;

import com.fullerton.olp.model.ApplicationStatus;

/**
 * @author nitish
 *
 */
public interface ApplicationStatusService extends GenericEntityService<ApplicationStatus, Long> {

	ApplicationStatus findById(Long id);
	
    Collection<ApplicationStatus> findAll();
}
