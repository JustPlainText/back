package de.mosst.jpt;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import de.mosst.jpt.data.NoteDao;
import de.mosst.jpt.data.NoteDaoForGDataStore;
import de.mosst.jpt.data.NoteDaoWithHashMap;

@SpringBootApplication
public class JptBackApplication {

	private static boolean withDataStore;

	public static void main(String[] args) throws IOException {

		try { // try to load google credential for Data Store form a key.json file
			System.out.println("############# env: " + System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));
			GoogleCredential credential = GoogleCredential.getApplicationDefault();
			System.out.println("############# credential: " + credential.getServiceAccountId());
			withDataStore = true;
		} catch (Exception e) {
			System.out.println("Google credetial for Data Store couldn't be load, switch to HashMap Version!");
		}

		SpringApplication.run(JptBackApplication.class, args);
	}

	@Bean
	public NoteDao getNoteDao() {
		if (withDataStore) {
			return new NoteDaoForGDataStore();
		} else {
			return new NoteDaoWithHashMap();
		}
	}
}
