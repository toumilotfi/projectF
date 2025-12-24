/*
ackage rabbit;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.BindingBuilder;


@Configuration
public class RabbitConfig {

    // Admin → Users (broadcast)
    @Bean
    public FanoutExchange adminToUsersExchange() {
        return new FanoutExchange("adminToUsers");
    }


    @Bean
    public Queue userQueue() {
        return new Queue("userQueue", false);
    }

    @Bean
    public Binding bindUser(FanoutExchange adminToUsersExchange, Queue userQueue) {
        return BindingBuilder.bind(userQueue).to(adminToUsersExchange);
    }

    // Users → Admin (direct)
    @Bean
    public DirectExchange usersToAdminExchange() {
        return new DirectExchange("usersToAdmin");
    }

    @Bean
    public Queue adminQueue() {
        return new Queue("adminQueue", false);
    }

    @Bean
    public Binding bindAdmin(DirectExchange usersToAdminExchange, Queue adminQueue) {
        return BindingBuilder.bind(adminQueue).to(usersToAdminExchange).with("admin");
    }
}

 */
