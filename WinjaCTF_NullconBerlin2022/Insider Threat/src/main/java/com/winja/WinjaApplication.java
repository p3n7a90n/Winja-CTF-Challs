package com.winja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WinjaApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(WinjaApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
