package com.winja.Captcha.Util;

import java.security.SecureRandom;

public class CaptchaStringGen {

    public static String  getCaptcha(int len)
    {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        SecureRandom secureRandom = new SecureRandom();

        String generatedString = secureRandom.ints(leftLimit, rightLimit + 1)
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println("generated String: "+generatedString);
        return generatedString;
    }
}
