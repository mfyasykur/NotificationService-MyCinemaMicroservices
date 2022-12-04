package edu.binar.challenge.MyCinemaMicroservices.NotifService.controller;

import edu.binar.challenge.MyCinemaMicroservices.NotifService.model.PushNotificationRequest;
import edu.binar.challenge.MyCinemaMicroservices.NotifService.model.PushNotificationResponse;
import edu.binar.challenge.MyCinemaMicroservices.NotifService.service.PushNotifService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/mycinema-v1")
public class PushNotifController {

    @Autowired
    private PushNotifService pushNotifService;

    private static final String MESSAGE = "Notification has been sent";

    @PostMapping("/notification/topic")
    public ResponseEntity<PushNotificationResponse> sendNotification(@RequestBody PushNotificationRequest request) {
        pushNotifService.sendPushNotificationWithoutData(request);

        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), MESSAGE), HttpStatus.OK);
    }

    @PostMapping("/notification/token")
    public ResponseEntity<PushNotificationResponse> sendTokenNotification(@RequestBody PushNotificationRequest request) {

        pushNotifService.sendPushNotificationToToken(request);

        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), MESSAGE), HttpStatus.OK);
    }

    @PostMapping("/notification/data")
    public ResponseEntity<PushNotificationResponse> sendDataNotification(@RequestBody PushNotificationRequest request) {

        pushNotifService.sendPushNotification(request);

        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), MESSAGE), HttpStatus.OK);
    }

    @GetMapping("/notification")
    public ResponseEntity<PushNotificationResponse> sendSampleNotification() {

        pushNotifService.sendSamplePushNotification();

        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), MESSAGE), HttpStatus.OK);
    }
}
