/*
package rabbit;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiver {

    @RabbitListener(queues = "userQueue")
    public void receiveFromAdmin(String message) {
        System.out.println("User received: " + message);
    }

    @RabbitListener(queues = "adminQueue")
    public void receiveFromUsers(String message) {
        System.out.println("Admin received: " + message);
    }
}

 */