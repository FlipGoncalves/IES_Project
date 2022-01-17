package Project.TwitterBot;

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

import java.io.IOException;
import java.net.Socket;


@SpringBootApplication
@EnableScheduling
public class TwitterBotApp {
  
  private static final Logger logger = LogManager.getLogger( "TwitterBotApp" );
  private static final Logger logger1 = LogManager.getLogger( "TwitterService" );
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
    return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#"); // queue for each type of request must match in
    // runner
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
  
  
  public static void main( String[] args ) {
    token = System.getenv( "token" );
    
    if ( token == null && token.isEmpty() ) {
      System.out.println( "Bearer Token not set" );
      System.exit( - 1 );
    }
    logger.error( token );
    logger.info( token );
    logger1.error( token );
  
  
    SpringApplication.run( TwitterBotApp.class, args ).close();
  }
  
}
