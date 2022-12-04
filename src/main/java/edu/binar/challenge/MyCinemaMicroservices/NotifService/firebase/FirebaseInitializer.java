package edu.binar.challenge.MyCinemaMicroservices.NotifService.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;

@Service
public class FirebaseInitializer {

    Logger logger = LoggerFactory.getLogger(FirebaseInitializer.class);

    @PostConstruct
    public void initialize() {

        try (FileInputStream serviceAccount = new FileInputStream("src/main/resources/MyCinema-ServiceAccount.json")) {

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://mycinema-f7acc-default-rtdb.asia-southeast1.firebasedatabase.app/")
                    .build();
            FirebaseApp.initializeApp(options);
            logger.info("Firebase application has been initialized");
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
    }
}
