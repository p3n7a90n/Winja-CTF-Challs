package com.winja.application.in;

import com.winja.domain.User;

public interface InputDataValidatorUseCase {

    boolean validate(User user);
}
