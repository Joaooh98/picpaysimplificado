package com.picpaysimplificado.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaysimplificado.domain.user.AccountUser;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(AccountUser user, String message) throws Exception {

        boolean isSendTransaction = isSendTransaction();

        if (!isSendTransaction) {
            throw new Exception("Notification service is down!!");
        }
        System.out.println("e-mail Send");

    }

    public boolean isSendTransaction() {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> authorizationResponse = restTemplate
                .getForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", Map.class);

        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            Boolean sendTransaction = (Boolean) authorizationResponse.getBody().get("message");
            return Boolean.TRUE.equals(sendTransaction);
        }
        return false;
    }

}
