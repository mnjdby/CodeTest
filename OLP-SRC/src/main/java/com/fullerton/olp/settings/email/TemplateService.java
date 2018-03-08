package com.fullerton.olp.settings.email;

import java.util.Map;


public interface TemplateService {
	String getFormattedText(String templatePath, Map<String, String> data);

	String getFormattedText(String templatePath);

	String getFormattedText(String templatePath, Map<String, String> contentToReplace, Map<String, String> listToShow);
	
	String getFormattedTextFromClassPathFile(String file, Map<String, String> data) ;
}
