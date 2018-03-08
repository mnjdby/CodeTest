package com.fullerton.olp.settings.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fullerton.olp.settings.converter.TimeConverter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters (FormatterRegistry registry) {
    	super.addFormatters(registry);
        registry.addConverter(new TimeConverter());
    }
    
    
}