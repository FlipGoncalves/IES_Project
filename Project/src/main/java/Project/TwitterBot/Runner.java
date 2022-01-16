package Project.TwitterBot;


import Project.TwitterBot.model.CountTweets.TweetCount;
import Project.TwitterBot.model.SearchTweets.Datum;
import Project.TwitterBot.model.TrendTweet.TweetTrendsJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class Runner implements CommandLineRunner {
  private static final Logger logger = LogManager.getLogger( "RabbitMqSender" );
  private static final int[] ids = new int[10];
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
    
    if ( queue.size() == 0 ) System.exit( 0 );
    
    logger.info( "Sending message... Timed" );
    String s = queue.get( 0 );
    rabbitTemplate.convertAndSend( TwitterBotApp.topicExchangeName, "foo.bar.baz", s);
    queue.remove( 0 );
  }
  
  @Override
  public void run( String... args ) throws Exception {
    //
    Random r = new Random();
    List<TweetTrendsJson> t = ts.getTrends( r.nextInt() );
    
    List<String>queries = t.stream().map( TweetTrendsJson::getQuery ).collect( Collectors.toList());
    
    List<String> ls = t.stream().map( TweetTrendsJson::toString ).collect( Collectors.toList());
    
    for (String s: queries) {
      ls.addAll(ts.searchTweets( s ).stream().map( Datum::toString ).collect( Collectors.toList()) );
      ls.addAll(ts.getInterestCount( s ).stream().map( TweetCount::toString ).collect( Collectors.toList()) );
    }
    
    ls.addAll( ts.searchTweets( args[0] ).stream().map( Datum::toString ).collect( Collectors.toList() ) );
    ls.addAll( ts.getInterestCount( args[0] ).stream().map( TweetCount::toString ).collect( Collectors.toList() ) );
    
    this.queue.addAll( ls );
    
    System.out.println( "Sending message..." );
    rabbitTemplate.convertAndSend( TwitterBotApp.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!" );
  }
  
}