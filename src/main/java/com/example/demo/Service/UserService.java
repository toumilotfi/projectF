/*
package com.example.demo.Service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import rabbit.UserSender;

@Service
public class UserService {

    private final UserSender sender;

    public UserService(UserSender sender) {
        this.sender = sender;
    }

    public String sendToAdmin(String username, String msg) {
        sender.sendToAdmin(username, msg); // VOID call
        return "Message sent to admin";
    }
}

 */