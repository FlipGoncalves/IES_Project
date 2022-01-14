package TwitterBot;


import TwitterBot.model.TrendTweet.TweetTrendsJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner {
  private static final Logger logger = LogManager.getLogger( "TwitterService" );
  private final RabbitTemplate rabbitTemplate;
  private final Receiver receiver;
  public ArrayList<String> queue = new ArrayList<>(); // queue for commands and query Strings or ids
  
  @Autowired TwitterServiceimp ts;
  
  public Runner( Receiver receiver, RabbitTemplate rabbitTemplate ) {
    this.receiver = receiver;
    this.rabbitTemplate = rabbitTemplate;
  }
  
  // Receive message
  public void receiveMessage( String message ) {
    // Add a way to know what queue or something similar
    logger.info( "RUNNER RECEIVED: " + message );
    queue.add( message );
  }
  
  @Scheduled(fixedDelay = 5000L)
  public void sendMessage() {
  
    if (queue.size() == 0 ) return;
    
    logger.info( "Sending message... Timed" );
    int i = Integer.parseInt( queue.get( 0 ) );
    logger.info( "He wants -> " + queue.get( 0 ) );
    // TODO switch type of call "ts.getTrends" "ts.search" "ts.getCount"
    //
    queue.remove( 0 );
    List<TweetTrendsJson> tt = ts.getTrends( i );
    rabbitTemplate.convertAndSend( TwitterBotApp.topicExchangeName, "foo.bar.baz", tt.get( 0 ).toString() );
//        rabbitTemplate.convertAndSend(exchange, routingkey,SerializationUtils.deserialize(message));
    //for(int i=0;i<message.size();i++)
    // rabbitTemplate.convertAndSend(exchange,routingkey, message.get(i));
  }
  
  @Override
  public void run( String... args ) throws Exception {
    System.out.println( "Sending message..." );
    rabbitTemplate.convertAndSend( TwitterBotApp.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!" );
    receiver.getLatch().await( 10000, TimeUnit.MILLISECONDS );
  }
  
}