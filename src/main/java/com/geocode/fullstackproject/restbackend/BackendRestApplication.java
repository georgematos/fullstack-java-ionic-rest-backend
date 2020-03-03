package com.geocode.fullstackproject.restbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendRestApplication implements CommandLineRunner {

	// @Autowired
	// private S3Service s3service;

	public static void main(String[] args) {
		SpringApplication.run(BackendRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// s3service.uploadFile("/home/george/Imagens/MinhasFotos/my_avatar.png");

	}

}
