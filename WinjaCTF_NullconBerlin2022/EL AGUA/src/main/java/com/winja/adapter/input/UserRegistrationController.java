package com.winja.adapter.input;

import com.winja.application.in.InputDataValidatorUseCase;
import com.winja.application.out.UserRegistrationPort;
import com.winja.domain.Role;
import com.winja.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@RestController
public class UserRegistrationController {

    @Autowired
    InputDataValidatorUseCase inputDataValidatorUseCase;

    @Autowired
    UserRegistrationPort userRegistrationPort;

    @GetMapping("/")
    public ResponseEntity welcome()
    {
        return  ResponseEntity.status(HttpStatus.OK).body("Welcome to Winja CTF!");
    }

    @PostMapping("/userRegistration")
    public ResponseEntity userRegistration(@RequestBody User user){

        if(inputDataValidatorUseCase.validate(user)){
            userRegistrationPort.saveUser(user);
            return ResponseEntity.status(HttpStatus.OK).body("Created");
        }

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something Wrong in creating the user");

    }
}
