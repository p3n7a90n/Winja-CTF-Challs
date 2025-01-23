package com.winja.Captcha;

import com.winja.Captcha.Util.UserNameGen;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class CaptchaApplication {

	public static void main(String[] args) {
		UserNameGen userNameGen = new UserNameGen();
		userNameGen.getUserName();
		SpringApplication.run(CaptchaApplication.class, args);
	}

}
