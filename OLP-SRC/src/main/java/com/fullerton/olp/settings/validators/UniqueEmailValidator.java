package com.fullerton.olp.settings.validators;

import com.fullerton.olp.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator of unique email
 */
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        // If the repository is null then return null
        if(accountRepository == null){
            return true;
        }
        // Check if the mobile is unique
        return accountRepository.findByEmail(email) == null;
    }
}
