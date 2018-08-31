package com.ruiliang.management;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "true");
		new Application()
		.configure(new SpringApplicationBuilder(Application.class))
        .run(args);
	}
}
