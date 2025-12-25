package com.example.demo.Service;

import com.example.demo.models.ChatMessage;
import com.example.demo.rabbit.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {

    @RabbitListener(queues = RabbitConfig.USER_QUEUE)
    public void receiveFromAdmin(ChatMessage message) {
        System.out.println("USER RECEIVED: " + message);
    }
}