package Project.TwitterBot;


import Project.TwitterBot.model.CountTweets.TweetCount;
import Project.TwitterBot.model.SearchTweets.Datum;
import Project.TwitterBot.model.TrendTweet.TweetTrendsJson;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class Runner implements CommandLineRunner {
  private static final Logger logger = LogManager.getLogger( "QueueSender" );
  private static final int[] ids = new int[10];
  private static Random r = new Random();
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
    int j;
    if ( queue.size() != 0 ) {
      j = r.nextInt( queue.size() );
      for (int i = 0 ; i < 4 ; i++) {
        String s = queue.get( j );
        Gson g = new Gson();
        /*
        code to load a class
        try {
          if ( s.contains( "author_id" ) ) {
  
            Datum t = g.fromJson( s, Datum.class );
            logger.info( t.toString() + "Datum" );
          }
          else if ( s.contains( "tweet_count" ) ) {
            TweetCount t = g.fromJson( s, TweetCount.class );
            logger.info( t.toString() + "Tweet Count" );
          }
          else {
            TweetTrendsJson t = g.fromJson( s, TweetTrendsJson.class );
            logger.info( t.toString() + "Tweet Trends" );
          }
        } catch (Exception aaa) {
          continue;
        }
         */
        
        logger.info( s );
        rabbitTemplate.convertAndSend( TwitterBotApp.topicExchangeName, "foo.bar.baz", s );
        queue.remove( j );
        j = r.nextInt( queue.size() );
        logger.info( "Sending message... Timed" );
        logger.info( "Queue Size -> " + queue.size() );
      }
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
    /*
    List<String> queries =
      t.stream().map(  (TweetTrendsJson t_) -> new String(Base64.getDecoder().decode(t_.getQuery().getBytes(
      StandardCharsets.UTF_8 )))  )
       .collect( Collectors.toList() );
    */
    
    List<String> queries = new ArrayList<>();
    for (TweetTrendsJson t_ : t) {
      String sQuery = null;
      try {
        sQuery = new String( Base64.getDecoder().decode( t_.getQuery().getBytes( StandardCharsets.UTF_8 ) ) );
      } catch (IllegalArgumentException e) {
        logger.info( "t query - > " + t_.getQuery() );
        continue;
      }
      logger.error(
        "decoded - > " + sQuery );
      queries.add( sQuery );
    }
    
    this.queue.addAll( t.stream().map( TweetTrendsJson::toString ).collect( Collectors.toList() ) );
    
    for (String s : queries) {
      try {
        this.queue.addAll( ts.searchTweets( s ).stream().map( Datum::toString ).collect( Collectors.toList() ) );
      } catch (NullPointerException n) {
        logger.error( "Null stuff query returned 0 tweets" + s );
      }
      this.queue.addAll(
        ts.getInterestCount( s ).stream().map( TweetCount::toString ).collect( Collectors.toList() ) );
    }
    
    List<Datum> datum = ts.searchTweets( args[0] );
    logger.info( "OLA MENINOS" );
    for (Datum d : datum) {
      logger.error( "d ->> " + d.toString() + "||||||||||" );
    }
    logger.info( "OLA MENINOS" );
    this.queue.addAll( datum.stream().map( Datum::toString ).collect( Collectors.toList() ) );
    this.queue.addAll(
      ts.getInterestCount( args[0] ).stream().map( TweetCount::toString ).collect( Collectors.toList() ) );
    
    //this.queue.addAll( ls );
    
    while (this.queue.size() != 0) {
    }
  }
  
}