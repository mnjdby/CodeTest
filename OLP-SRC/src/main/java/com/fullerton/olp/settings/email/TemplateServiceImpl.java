package com.fullerton.olp.settings.email;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fullerton.olp.service.EmailTemplateService;


@Service
public class TemplateServiceImpl implements TemplateService {

	@Autowired
	private VelocityEngine velocityEngine;
	
	@Autowired
	private EmailTemplateService emailTemplateService;

	@Override
	public String getFormattedText(String templatePath, Map<String, String> data) {
		Assert.notNull(templatePath);
		Assert.notNull(data);

		Template template = velocityEngine.getTemplate(templatePath);

		VelocityContext velocityContext = new VelocityContext();

		velocityContext.put("dataMap", data);

		StringWriter stringWriter = new StringWriter();

		template.merge(velocityContext, stringWriter);

		return stringWriter.toString();
	}

	private String readFromInputStream(InputStream inputStream)
	  		  throws IOException {
	  		    StringBuilder resultStringBuilder = new StringBuilder();
	  		    try (BufferedReader br
	  		      = new BufferedReader(new InputStreamReader(inputStream))) {
	  		        String line;
	  		        while ((line = br.readLine()) != null) {
	  		            resultStringBuilder.append(line).append("\n");
	  		        }
	  		    }
	  		  return resultStringBuilder.toString();
	  		}
	  
	  

	public String getFormattedTextFromClassPathFile(String file, Map<String, String> data) {

		ClassPathResource cpr = new ClassPathResource(file);
		
		
		String sb = "";
		try {
			sb = readFromInputStream(cpr.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String key : data.keySet()) {
			sb = sb.replaceAll("@@"+key+"@@", data.get(key));
		}
		
		return sb;
	}
	
	public static void main(String[] args) {
		System.out.println("asdf@@link@@asdf".replaceAll("@@"+"link"+"@@", "somelink"));
		
	}
	@Override
	public String getFormattedText(String templatePath) {
		return getFormattedText(templatePath, new HashMap<String, String>());
	}

	@Override
	public String getFormattedText(String templatePath, Map<String, String> contentToReplace,
			Map<String, String> listToShow) {
		Assert.notNull(templatePath);
		// Assert.notNull(contentToReplace);
		// Assert.notNull(listToShow);

		Template template = velocityEngine.getTemplate(templatePath);

		VelocityContext velocityContext = null;
		if (contentToReplace != null && !contentToReplace.isEmpty()) {
			velocityContext = new VelocityContext(contentToReplace);
		} else {
			velocityContext = new VelocityContext();
		}

		if (listToShow != null && !listToShow.isEmpty()) {
			velocityContext.put("dataMap", listToShow);
		}

		StringWriter stringWriter = new StringWriter();

		template.merge(velocityContext, stringWriter);

		return stringWriter.toString();
	}
	
	 
}
