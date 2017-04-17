package de.mosst.jpt;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

@SpringBootApplication
public class JptBackApplication {

	public static void main(String[] args) throws IOException {

		System.out.println("############# env: " + System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));
		GoogleCredential credential = GoogleCredential.getApplicationDefault();
		System.out.println("############# credential: " + credential.getServiceAccountId());

		SpringApplication.run(JptBackApplication.class, args);
	}
}
