package com.example.demo.Service;

import com.example.demo.models.ChatMessage;
import com.example.demo.rabbit.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendToAdmin(ChatMessage message) {
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "admin", message);
    }

    public void sendToUser(ChatMessage message) {
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "user", message);
    }
}

