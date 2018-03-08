package com.fullerton.olp.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fullerton.olp.controller.util.SearchParameters;
import com.fullerton.olp.model.AuditTrail;
import com.fullerton.olp.service.AuditTrailService;

@Controller
@RequestMapping("audit")
// @PermitAllConsumers
public class AuditTrailController extends BaseController {

	@Autowired
	private AuditTrailService auditTrailService;

	@RequestMapping(value = "/{pk}")
	@ResponseBody
	public AuditTrail get(@PathVariable("pk") AuditTrail auditTrail) {
		return auditTrail.copy();
	}

	@RequestMapping(value = { "/", "" }, method = GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> list(AuditTrail auditTrail, SearchParameters searchParameters) {
		if (searchParameters.getPageSize() == 0) {
			searchParameters.setPageSize(600);// TODO remove this hard limit,
												// use autocomplete if possible
		}

		logger.info("Search AuditTrail :->" + auditTrail);
		logger.info("Search Parameters :->" + searchParameters);
		List<AuditTrail> auditTrails = auditTrailService.find(auditTrail, searchParameters.toSearchTemplate());
		List<AuditTrail> ret = new ArrayList<AuditTrail>();
		for (AuditTrail _auditTrail : auditTrails) {
			ret.add(_auditTrail.copy());
		}
		Map<String, Object> result = new HashMap<>();
		result.put("size", auditTrailService.findCount(auditTrail, searchParameters.toSearchTemplate()));
		result.put("value", ret);

		ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

}