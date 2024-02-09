package com.example.voiture.services;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FCMService {

    @PostConstruct
    public void init() {
        try {
            // Initialisation de Firebase Admin SDK
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream("src/main/java/com/example/voiture/data/voiture-aa6db-firebase-adminsdk-9ph17-3d2a426c56.json")))
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNotification(String deviceToken, String title, String body) {
        // Création de la notification
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        // Création du message
        Message message = Message.builder()
                .setNotification(notification)
                .setToken(deviceToken)
                .build();

        // Envoi de la notification
        try {
            FirebaseMessaging.getInstance().send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
