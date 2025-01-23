package com.winja.Captcha.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Timer;
import java.util.TimerTask;

import static com.winja.Captcha.Util.Constants.PASSWORD;
import static com.winja.Captcha.Util.Constants.USERNAME;

public class UserNameGen {

    private static Logger log = LoggerFactory.getLogger(UserNameGen.class);
    public void getUserName() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SecureRandom secureRandom = new SecureRandom();
                int lineNumber = secureRandom.nextInt(100)+1;
                try {
                   // log.info(new File(".").getCanonicalPath());

                    String str = Files.lines(Paths.get("./top100usernames.txt")).skip(lineNumber - 1).findFirst().get();
                    USERNAME = str;
                    PASSWORD = str;
                    log.info("Latest Username:{}",USERNAME);
                } catch (Exception e) {
                    log.error("Error generating random number",e);
                }

            }
        }, 0, 180000);
    }


//    @Override
//    public void run() {
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                SecureRandom secureRandom = new SecureRandom();
//                int lineNumber = secureRandom.nextInt(100)+1;
//                try {
//                    String str = Files.lines(Paths.get("../../../top100usernames.txt")).skip(lineNumber - 1).findFirst().get();
//                    USERNAME = str;
//                    PASSWORD = str;
//                } catch (Exception e) {
//                    log.error("Error generating random number",e);
//                }
//
//            }
//        }, 0, 1000);
//        log.info(USERNAME,PASSWORD);
//    }
}
