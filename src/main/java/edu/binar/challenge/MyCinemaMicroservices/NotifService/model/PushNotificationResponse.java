package edu.binar.challenge.MyCinemaMicroservices.NotifService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PushNotificationResponse {

    private int status;
    private String message;
}
