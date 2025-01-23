package com.winja.application;

import com.winja.application.in.InputDataValidatorUseCase;
import com.winja.domain.User;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class InputDataValidatorService implements InputDataValidatorUseCase {

    @Override
    public boolean validate(User user){

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

            for (ConstraintViolation<User> violation : violations) {
                System.out.println(violation.getMessage());
            }

        if(!violations.isEmpty()) {
            return false;
        }
        else{
            return true;
        }

    }
}
