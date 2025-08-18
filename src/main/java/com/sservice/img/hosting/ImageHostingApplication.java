package com.sservice.img.hosting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.sservice.img.hosting")
public class ImageHostingApplication {
	public static void main(String[] args) {
		SpringApplication.run(ImageHostingApplication.class, args);
	}

}
