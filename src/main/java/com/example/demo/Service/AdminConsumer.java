package com.example.demo.Service;

import com.example.demo.models.ChatMessage;
import com.example.demo.rabbit.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class AdminConsumer {

    @RabbitListener(queues = RabbitConfig.ADMIN_QUEUE)
    public void receiveFromUser(ChatMessage message) {
        System.out.println("ADMIN RECEIVED: " + message);
    }
}