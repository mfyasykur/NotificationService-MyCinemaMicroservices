package edu.binar.challenge.MyCinemaMicroservices.NotifService.service.impl;

import edu.binar.challenge.MyCinemaMicroservices.NotifService.firebase.FCMService;
import edu.binar.challenge.MyCinemaMicroservices.NotifService.model.PushNotificationRequest;
import edu.binar.challenge.MyCinemaMicroservices.NotifService.service.PushNotifService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class PushNotifServImpl implements PushNotifService {

    private final Logger logger = LoggerFactory.getLogger(PushNotifServImpl.class);

    @Autowired
    private final FCMService fcmService;

    public PushNotifServImpl(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    @Override
    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
    public void sendSamplePushNotification() {

        try {
            fcmService.sendMessageWithoutData(getSamplePushNotificationRequest());
        } catch (InterruptedException | ExecutionException exception) {
            logger.error(exception.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void sendPushNotification(PushNotificationRequest request) {

        try {
            fcmService.sendMessage(getSamplePayloadData(), request);
        } catch (InterruptedException | ExecutionException exception) {
            logger.error(exception.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void sendPushNotificationWithoutData(PushNotificationRequest request) {

        try {
            fcmService.sendMessageWithoutData(request);
        } catch (InterruptedException | ExecutionException exception) {
            logger.error(exception.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void sendPushNotificationToToken(PushNotificationRequest request) {

        try {
            fcmService.sendMessageToToken(request);
        } catch (InterruptedException | ExecutionException exception) {
            logger.error(exception.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private Map<String, String> getSamplePayloadData() {

        String payLoadMessageId = "1";
        String payloadData = "payload data";

        Map<String, String> pushData = new HashMap<>();
        pushData.put("messageId", payLoadMessageId);
        pushData.put("text", payloadData + " " + LocalDateTime.now());

        return pushData;
    }

    private PushNotificationRequest getSamplePushNotificationRequest() {

        final String topic = "booking";
        final String title = "Booking Success";
        final String message = "Ticket booking was successful, thank you.";

        return new PushNotificationRequest(title, message, topic);
    }
}
