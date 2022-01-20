package Project.app;

import Project.app.Models.Datum;
import Project.app.Models.TweetCount;
import Project.app.Models.TweetTrendsJson;
import Project.app.Service.ApiService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class Receiver {
  @Autowired ApiService apiService;
  static final Logger logger = LogManager.getLogger("TwitterService");
  
  public void receiveMessage( String message ) {
    //code to load a class stored in a json object
    
    Gson g = new Gson();
    try {
      if ( message.contains( "author_id" ) ) {
        
        Datum t = g.fromJson( message, Datum.class );
        apiService.saveTweet( t );
        logger.info( t.toString() + "Datum" );
        // save to repo
      }
      else if ( message.contains( "tweet_count" ) ) {
        TweetCount t = g.fromJson( message, TweetCount.class );
        logger.info( t.toString() + "Tweet Count" );
        apiService.saveTweetCount( t );
        //save to repo
      }
      else {
        TweetTrendsJson t = g.fromJson( message, TweetTrendsJson.class );
        logger.info( t.toString() + "Tweet Trends" );
        apiService.saveTweetTrend( t );
        //save to repo
      }
    } catch (Exception aaa) {
      //logger.error("Message not deserializable");
    }
    System.out.println( "Received <" + message + ">" );
  }
  
}