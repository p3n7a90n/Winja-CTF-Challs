package com.winja.Captcha.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {

    private String userName;
    private String password;
    private String captcha;

}
