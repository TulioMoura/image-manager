package com.tdm.imagemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageManagerApplication {

	public static void main(String[] args) {
		
			System.out.println(args);
		
		SpringApplication.run(ImageManagerApplication.class, args);
	}

}
