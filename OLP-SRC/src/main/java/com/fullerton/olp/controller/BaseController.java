package com.fullerton.olp.controller;

import java.util.Map;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fullerton.olp.model.account.Account;
import com.fullerton.olp.service.account.AccountService;
import com.fullerton.olp.settings.exceptions.DefaultExceptionAttributes;
import com.fullerton.olp.settings.exceptions.ExceptionAttributes;

/**
 * Base of all controllers
 */
public class BaseController {
	
	@Autowired
	private AccountService accountService;
	
    /**
     * The Logger for this class.
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Long getLoggedInUserAccountId(){
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	Account loggedInUserAccount = accountService.findByUsername(auth.getPrincipal().toString());
    	if(loggedInUserAccount != null ) {
    		return loggedInUserAccount.getId();
    	}
    	return 0L;
    }

	protected Account getLoggedInUserAccount(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    return accountService.findByUsername(auth.getPrincipal().toString());
	}  
	
    
    protected String getLoggedInUsername (){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	return auth.getPrincipal().toString();
    }
    /**
     * Handles JPA NoResultExceptions thrown from web service controller
     * methods. Creates a response with Exception Attributes as JSON and HTTP
     * status code 404, not found.
     *
     * @param noResultException A NoResultException instance.
     * @param request The HttpServletRequest in which the NoResultException was
     *        raised.
     * @return A ResponseEntity containing the Exception Attributes in the body
     *         and HTTP status code 404.
     */
    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Map<String, Object>> handleNoResultException(
            NoResultException noResultException, HttpServletRequest request) {

        logger.info("> handleNoResultException");
		if (StringUtils.containsIgnoreCase(ExceptionUtils.getRootCauseMessage(noResultException), "Broken pipe")) {
			return null; // socket is closed, cannot return any response
		} else {
			ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();

			Map<String, Object> responseBody = exceptionAttributes.getExceptionAttributes(noResultException, request,
					HttpStatus.NOT_FOUND);

			logger.info("< handleNoResultException");
			return new ResponseEntity<Map<String, Object>>(responseBody, HttpStatus.NOT_FOUND);
		}
    }

    /**
     * Handles all Exceptions not addressed by more specific
     * <code>@ExceptionHandler</code> methods. Creates a response with the
     * Exception Attributes in the response body as JSON and a HTTP status code
     * of 500, internal server error.
     *
     * @param exception An Exception instance.
     * @param request The HttpServletRequest in which the Exception was raised.
     * @return A ResponseEntity containing the Exception Attributes in the body
     *         and a HTTP status code 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(
            Exception exception, HttpServletRequest request) {
    	 logger.error("> handleException");
         logger.error("- Exception: ", exception);
         
    	 if (StringUtils.containsIgnoreCase(ExceptionUtils.getRootCauseMessage(exception), "Broken pipe")) { 
    	      return null;        //socket is closed, cannot return any response    
    	    } else {
    	    	 ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();

    	         Map<String, Object> responseBody = exceptionAttributes
    	                 .getExceptionAttributes(exception, request,
    	                         HttpStatus.INTERNAL_SERVER_ERROR);

    	         logger.error("< handleException");
    	         return new ResponseEntity<Map<String, Object>>(responseBody,
    	                 HttpStatus.INTERNAL_SERVER_ERROR);
    	    }
       

       
    }

}
