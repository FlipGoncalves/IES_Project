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
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Runner implements CommandLineRunner {
  private static final Logger logger = LogManager.getLogger( "TwitterService" );
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
      for (int i = 0 ; i < 2 ; i++) {
        String s = queue.get( j );
        /*
        Gson g = new Gson();
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
        if(queue.size() == 0) break;
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
      logger.info( s );
    }
    System.out.println( "end" );
    Random r = new Random();
    List<TweetTrendsJson> t = ts.getTrends( 23424975 );
    
    logger.debug( "t -> " + t );
    /*
    List<String> queries =
      t.stream().map(  (TweetTrendsJson t_) -> new String(Base64.getDecoder().decode(t_.getQuery().getBytes(
      StandardCharsets.UTF_8 )))  )
       .collect( Collectors.toList() );
    */
    
    Gson g = new Gson();
    List<String> queries = new ArrayList<>();
    for (TweetTrendsJson t_ : t) {
      String sQuery = null;
      try {
        sQuery =
          new String( Base64.getDecoder().decode( t_.getQuery().replaceAll( "[^0-9a-zA-Z]","" ).getBytes( StandardCharsets.UTF_8 ) ) );
        logger.error(
          "!!!!decoded - > " + sQuery + "|||" );
        //queries.add( sQuery );
      } catch (StringIndexOutOfBoundsException e) {
        logger.error( "t query - >+" + t_ +"\n"   + e.toString() );
      }
      catch (IllegalArgumentException e1){
        logger.error( "t query - >+" + t_ +"\n"   + e1.toString() );
      }
      
    }
  
    queries.addAll( Arrays.stream( args ).collect( Collectors.toList()) );
    queries.stream().map( (String l) -> !l.isEmpty() || l != null );

    queries.stream().forEach(logger::error);

    //this.queue.addAll( t.stream().map( TweetTrendsJson::toString ).collect( Collectors.toList() ) );
    this.queue.addAll( t.stream().map( g::toJson ).collect( Collectors.toList()) );
    queries.stream().map( (String l) -> l.isEmpty() || l == null );
    logger.error( "--------------------------------" );
    queries.stream().forEach( logger::error );
    logger.error( "--------------------------------" );
  
    logger.error( "--------------------------------" );
    System.out.println( "args" );
    for (String s : args) {
      logger.error( s );
    }
  
  
    logger.error( "--------------------------------" );
    logger.error( "--------------------------------" );
    for (String s : queries) {
      for (TweetCount tweetCount : ts.getInterestCount( s )) {
        tweetCount.setQuery( s );
        String toJson = g.toJson( tweetCount );
        this.queue.add( toJson );
      }
      for (Datum tweetCount : ts.searchTweets( s )) {
        tweetCount.setQuery( s );
        String toJson = g.toJson( tweetCount );
        this.queue.add( toJson );
      }
    }
    
    //this.queue.addAll( ls );
    
    while (this.queue.size() != 0) {
    }
  }
  
}