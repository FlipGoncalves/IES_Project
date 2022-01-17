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
  private CountDownLatch latch = new CountDownLatch( 2 );
  
  
  private static final Logger logger = LogManager.getLogger( "TwitterService" );
  public void receiveMessage( String message ) {
    System.out.println( "MINEEEEEEEE Received <" + message + ">" );
    logger.debug( "\n\n\n\n\n\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + message );
    logger.debug( "\n\n\n\n\n\n\n");
    latch.countDown();
  }
  
  
  public CountDownLatch getLatch() {
    return latch;
  }
}

