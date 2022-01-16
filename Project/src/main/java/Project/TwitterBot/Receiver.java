package Project.TwitterBot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {
  private final RabbitTemplate rabbitTemplate;
  private CountDownLatch latch = new CountDownLatch( 2 );
  
  @Autowired
  TwitterServiceimp ts;
  
  private static final Logger logger = LogManager.getLogger( "TwitterService" );
  public Receiver(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }
  public void receiveMessage( String message ) {
    System.out.println( "MINEEEEEEEE Received <" + message + ">" );
    logger.debug( "\n\n\n\n\n\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + message );
    logger.debug( "\n\n\n\n\n\n\n");
    latch.countDown();
  }
  
  @Scheduled(fixedDelay=5000L)
  public void sendMessage() {
    logger.info("-----Ask for dick picks");
    Random r = new Random();
    rabbitTemplate.convertAndSend( TwitterBotApp.topicExchangeName, "foo.bar.baz", r.nextInt());
    //rabbitTemplate.convertAndSend(TwitterBotApp.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
//        rabbitTemplate.convertAndSend(exchange, routingkey,SerializationUtils.deserialize(message));
    //for(int i=0;i<message.size();i++)
    // rabbitTemplate.convertAndSend(exchange,routingkey, message.get(i));
  }
  
  public CountDownLatch getLatch() {
    return latch;
  }
}

