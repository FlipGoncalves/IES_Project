package Project.TwitterBot;


import Project.TwitterBot.model.CountTweets.TweetCount;
import Project.TwitterBot.model.SearchTweets.Datum;
import Project.TwitterBot.model.SearchTweets.TweetSearchResponse;
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
  private static final Logger logger = LogManager.getLogger( "QueueSender" );
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
  
  @Scheduled(initialDelay = 8000L, fixedDelay = 1000L)
  public void sendMessage() {
    if ( queue.size() != 0 ) {
      logger.info( "Sending message... Timed" );
      String s = queue.get( 0 );
      logger.info(s);
      rabbitTemplate.convertAndSend( TwitterBotApp.topicExchangeName, "foo.bar.baz", s );
      queue.remove( 0 );
      logger.info("Queue Size -> " + queue.size());
    }
  }
  
  @Override
  public void run( String... args ) throws Exception {
    //
    System.out.println( "args" );
    for (String s : args) {
      System.out.println( s );
    }
    System.out.println( "end" );
    Random r = new Random();
    List<TweetTrendsJson> t = ts.getTrends( 1 );
    
    logger.debug( "t -> " + t );
    List<String> queries = t.stream().map( TweetTrendsJson::getQuery ).collect( Collectors.toList() );
    
    System.out.println( "queries" );
    queries.stream().forEach( System.out::print );
    System.out.println( "end" );
    
    this.queue.addAll( t.stream().map( TweetTrendsJson::toString ).collect( Collectors.toList()) );
  
    for (String s : queries) {
      try {
  
        this.queue.addAll( ts.searchTweets( s ).stream().map( Datum::toString ).collect( Collectors.toList() ) );
      }
      catch (NullPointerException n){
        logger.error( "Null stuff query returned 0 tweets" );
      }
      this.queue.addAll( ts.getInterestCount( s ).stream().map( TweetCount::toString ).collect( Collectors.toList() ) );
    }
    
    this.queue.addAll( ts.searchTweets( args[0] ).stream().map( Datum::toString ).collect( Collectors.toList() ) );
    this.queue.addAll( ts.getInterestCount( args[0] ).stream().map( TweetCount::toString ).collect( Collectors.toList() ) );
    
    //this.queue.addAll( ls );
    
    while(this.queue.size() != 0) {
    }
  }
  
}