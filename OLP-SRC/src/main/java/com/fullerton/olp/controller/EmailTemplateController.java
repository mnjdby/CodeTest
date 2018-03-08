package com.fullerton.olp.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fullerton.olp.controller.util.SearchParameters;
import com.fullerton.olp.model.EmailTemplate;
import com.fullerton.olp.service.EmailTemplateService;

@Controller
@RequestMapping("email")
// @PermitAllConsumers
public class EmailTemplateController extends BaseController {

	private static String STATUS_ACTIVE = "ACTIVE";
	private static String STATUS_INACTIVE = "INACTIVE";

	@Autowired
	public FormattingConversionService formattingConversionService;

	@Autowired
	private EmailTemplateService emailTemplateService;

	@RequestMapping(value = "/{pk}")
	@ResponseBody
	public EmailTemplate get(@PathVariable("pk") EmailTemplate emailTemplate) {
		return emailTemplate.copy();
	}

	@RequestMapping(value = "/{pk}", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<EmailTemplate> update(@RequestBody @Valid EmailTemplate emailTemplate,
			BindingResult bindingResult) {
		logger.info("EmailTemplate:->" + emailTemplate);
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<EmailTemplate>(new EmailTemplate(), HttpStatus.NOT_ACCEPTABLE);
		}
		emailTemplateService.save(emailTemplate);
		emailTemplate = emailTemplateService.find(emailTemplate).get(0);
		return new ResponseEntity<EmailTemplate>(emailTemplate.copy(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{pk}/{status}", method = POST)
	@ResponseBody
	public ResponseEntity<EmailTemplate> update(@PathVariable(value = "pk") Long id,
			@PathVariable(value = "status") String status) {
		logger.info("ID:->" + id);
		logger.info("Status:->" + status);
		if (id < 1 || (status == null || !(status.trim().equalsIgnoreCase(STATUS_ACTIVE)
				|| status.trim().equalsIgnoreCase(STATUS_INACTIVE)))) {
			return new ResponseEntity<EmailTemplate>(new EmailTemplate(), HttpStatus.NOT_ACCEPTABLE);
		}

		EmailTemplate emailTemplate = emailTemplateService.findById(id);
		emailTemplate.setActive(status.trim().equalsIgnoreCase(STATUS_ACTIVE) ? true : false);
		emailTemplateService.save(emailTemplate);
		return new ResponseEntity<EmailTemplate>(emailTemplate.copy(), HttpStatus.OK);
	}

	// Create EmailTemplate
	@RequestMapping(value = "/", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<EmailTemplate> create(@RequestBody @Valid EmailTemplate emailTemplate,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<EmailTemplate>(new EmailTemplate(), HttpStatus.NOT_ACCEPTABLE);
		}
		emailTemplateService.save(emailTemplate);
		logger.info(" EmailTemplate:->" + emailTemplate);
		// smsTemplate = smsTemplateService.find(smsTemplate).get(0);
		// logger.info("POST SMSTemplate:->"+smsTemplate);

		return new ResponseEntity<EmailTemplate>(emailTemplate.copy(), HttpStatus.CREATED);
	}

	@RequestMapping(value = { "/", "" }, method = GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> list(EmailTemplate emailTemplate, SearchParameters searchParameters) {
		if (searchParameters.getPageSize() == 0) {
			searchParameters.setPageSize(600);// TODO remove this hard limit,
												// use autocomplete if possible
		}

		logger.info("Search EmailTemplate :->" + emailTemplate);
		logger.info("Search Parameters :->" + searchParameters);
		List<EmailTemplate> emailTemplates = emailTemplateService.find(emailTemplate,
				searchParameters.toSearchTemplate());
		List<EmailTemplate> ret = new ArrayList<EmailTemplate>();
		for (EmailTemplate _emailTemplate : emailTemplates) {
			ret.add(_emailTemplate.copy());
		}
		Map<String, Object> result = new HashMap<>();

		result.put("size", emailTemplateService.findCount(emailTemplate, searchParameters.toSearchTemplate()));
		result.put("value", ret);

		ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "/{pk}", method = DELETE)
	@ResponseBody
	public boolean delete(@PathVariable("pk") EmailTemplate emailTemplate) {
		emailTemplateService.delete(emailTemplate);
		return true;
	}

}