/*
package rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserSender {

    private final RabbitTemplate rabbitTemplate;

    public UserSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToAdmin(String username, String message) {
        rabbitTemplate.convertAndSend(
                "usersToAdmin",
                "admin",
                username + ": " + message
        );
    }
}


 */