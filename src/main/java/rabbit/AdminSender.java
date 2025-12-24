/*
package rabbit;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AdminSender {

    private final RabbitTemplate rabbitTemplate;

    public AdminSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String message) {
        rabbitTemplate.convertAndSend("usersToAdminExchange", "", message);
    }
}

 */