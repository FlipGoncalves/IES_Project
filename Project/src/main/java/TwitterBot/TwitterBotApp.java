package TwitterBot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class TwitterBotApp {
  
  private static final Logger logger = LogManager.getLogger( "TwitterBotApp" );
  public static String token = null;
  
  static final String topicExchangeName = "spring-boot-exchange";
  
  static final String queueName = "spring-boot";
  
  @Bean
  Queue queue() {
    return new Queue(queueName, false);
  }
  
  @Bean
  TopicExchange exchange() {
    return new TopicExchange(topicExchangeName);
  }
  
  @Bean
  Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with("trends.ask.#"); // queue for each type of request
  }
  
  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                           MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName);
    container.setMessageListener(listenerAdapter);
    return container;
  }
  
  @Bean
  MessageListenerAdapter listenerAdapter( Receiver receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }
  
  @Bean
  MessageListenerAdapter listenerAdapter1( Runner runner) {
    return new MessageListenerAdapter(runner, "receiveMessage");
  }
  
  public static void main( String[] args ) {
    token = System.getenv( "token" );
    
    if ( token == null && token.isEmpty() ) {
      System.out.println( "Bearer Token not set" );
      System.exit( - 1 );
    }
    
    
    SpringApplication.run( TwitterBotApp.class, args );
  }
  
}
