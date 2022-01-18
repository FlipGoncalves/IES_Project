package Project.app;

import Project.app.Models.Datum;
import Project.app.Models.TweetCount;
import Project.app.Models.TweetTrendsJson;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
  
  public void receiveMessage( String message ) {
    //code to load a class stored in a json object
    
    Gson g = new Gson();
    try {
      if ( message.contains( "author_id" ) ) {
        
        Datum t = g.fromJson( message, Datum.class );
        //logger.info( t.toString() + "Datum" );
        // save to repo
      }
      else if ( message.contains( "tweet_count" ) ) {
        TweetCount t = g.fromJson( message, TweetCount.class );
        //logger.info( t.toString() + "Tweet Count" );
        //save to repo
      }
      else {
        TweetTrendsJson t = g.fromJson( message, TweetTrendsJson.class );
        //logger.info( t.toString() + "Tweet Trends" );
        //save to repo
      }
    } catch (Exception aaa) {
      //logger.error("Message not deserializable");
    }
    System.out.println( "Received <" + message + ">" );
  }
  
}