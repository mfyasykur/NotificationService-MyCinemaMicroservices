package edu.binar.challenge.MyCinemaMicroservices.NotifService.service;

import edu.binar.challenge.MyCinemaMicroservices.NotifService.model.PushNotificationRequest;

public interface PushNotifService {

    void sendSamplePushNotification();
    void sendPushNotification(PushNotificationRequest request);
    void sendPushNotificationWithoutData(PushNotificationRequest request);
    void sendPushNotificationToToken(PushNotificationRequest request);
}
