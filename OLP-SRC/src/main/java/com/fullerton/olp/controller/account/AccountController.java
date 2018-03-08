package com.fullerton.olp.controller.account;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fullerton.olp.controller.BaseController;
import com.fullerton.olp.controller.util.SearchParameters;
import com.fullerton.olp.model.account.Account;
import com.fullerton.olp.service.account.AccountService;

@Controller
@RequestMapping("user")
// @PermitAllConsumers
public class AccountController extends BaseController {

	private static String STATUS_ACTIVE = "ACTIVE";
	private static String STATUS_INACTIVE = "INACTIVE";

	@Autowired
	public FormattingConversionService formattingConversionService;

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/{pk}")
	@ResponseBody
	public Account get(@PathVariable("pk") Account account) {
		return account.copy();
	}

	@RequestMapping(value = "/{pk}", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Boolean> update(@RequestBody @Valid Account account, BindingResult bindingResult) {
		logger.info("Account:->" + account);

		// As we do not want to allow superadmin to be updated, checked against
		// id==1 in below line.
		if (bindingResult.hasErrors() && account.getId() == 1) {
			if(bindingResult.hasFieldErrors("username") || bindingResult.hasFieldErrors("email")) {
				return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.ALREADY_REPORTED);	
			}
			
			return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.NOT_ACCEPTABLE);
		}
		accountService.save(account);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{pk}/password", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Boolean> updatePassword(@RequestBody @Valid Account account, BindingResult bindingResult) {
		logger.info("Account:->" + account);

		// As we do not want to allow superadmin to be updated, checked against
		// id==1 in below line.
		if (StringUtils.isNotBlank(account.getPassword()) && account.getId() == 1) {
			return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.NOT_ACCEPTABLE);
		}
		account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
		accountService.save(account);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}

	@RequestMapping(value = "/{pk}/{status}", method = POST)
	@ResponseBody
	public ResponseEntity<Boolean> update(@PathVariable(value = "pk") Long id,
			@PathVariable(value = "status") String status) {
		logger.info("ID:->" + id);
		logger.info("Status:->" + status);
		// Here id is checked against value "2", as we do not want to update
		// superadmin who is assumed to have ID 1.
		if (id < 2 || (status == null || !(status.trim().equalsIgnoreCase(STATUS_ACTIVE)
				|| status.trim().equalsIgnoreCase(STATUS_INACTIVE)))) {
			return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.NOT_ACCEPTABLE);
		}

		Account account = accountService.findById(id);
		account.setActive(status.trim().equalsIgnoreCase(STATUS_ACTIVE) ? true : false);
		accountService.save(account);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}

	// Create Account
	@RequestMapping(value = "/", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Boolean> create(@RequestBody @Valid Account account, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			if(bindingResult.hasFieldErrors("username") || bindingResult.hasFieldErrors("email")) {
				return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.ALREADY_REPORTED);	
			}
			return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.NOT_ACCEPTABLE);
		}
		account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
		accountService.save(account);

		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.CREATED);
	}

	@RequestMapping(value = { "/", "" }, method = GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> list(Account account, SearchParameters searchParameters) {
		if (searchParameters.getPageSize() == 0) {
			searchParameters.setPageSize(600);// TODO remove this hard limit,
												// use autocomplete if possible
		}

		logger.info("Search Account :->" + account);
		logger.info("Search Parameters :->" + searchParameters);
		List<Account> accounts = accountService.find(account, searchParameters.toSearchTemplate());
		List<Account> ret = new ArrayList<Account>();
		for (Account _account : accounts) {
			ret.add(_account.copy());
		}
		Map<String, Object> result = new HashMap<>();

		result.put("size", accountService.findCount(account, searchParameters.toSearchTemplate()));
		result.put("value", ret);

		ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "/{pk}", method = DELETE)
	@ResponseBody
	public boolean delete(@PathVariable("pk") Account account) {
		accountService.delete(account);
		return true;
	}

}