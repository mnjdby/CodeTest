package com.fullerton.olp.settings.configuration;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  template configuration
 * 
 * @author nitish
 *
 */
@Configuration
public class VelocityTemplateConfig {
 
	
	@Bean
	public VelocityEngine  velocityEngine() {
		 
		return new VelocityEngine();
	}

	
}