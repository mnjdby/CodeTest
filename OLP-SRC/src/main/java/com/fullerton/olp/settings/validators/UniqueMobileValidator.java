package com.fullerton.olp.settings.validators;

import com.fullerton.olp.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator of unique username
 */
public class UniqueMobileValidator implements ConstraintValidator<UniqueMobile, String> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void initialize(UniqueMobile constraintAnnotation) {

    }

    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext context) {
        // If the repository is null then return null
        if(accountRepository == null){
            return true;
        }
        // Check if the mobile is unique
        return accountRepository.findByMobile(mobile) == null;
    }
}
