package TwitterBot;

import TwitterBot.TwitterService.TwitterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


// useful site : https://twitter4j.org/en/code-examples.html


@SpringBootApplication
//@ComponentScan("com.app.repository.TweetRepository")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class TwitterBotApp {
  private static final Logger logger = LogManager.getLogger( "TwitterBotApp" );

  public static String token = null;
  public static void main( String[] args ) {
    token = System.getenv( "token" );
    if ( token == null && ( token.isEmpty() || token.isBlank() ) ) {
      System.out.println( "Bearer Token not set" );
      System.exit( - 1 );
    }
    TwitterService ts = new TwitterService();
    logger.info( ts.getTrends( 1 ) );
    logger.info( ts.getInterestCount("Boris"  ) );
    logger.info( ts.searchTweets("Boris"  ) );
    //SpringApplication.run( TwitterBotApp.class, args );
    System.exit( 0 );
  }

  // access the twitter API using your twitter4j.properties file
  // The factory instance is re-useable and thread safe.
  private static void error( Exception e ) {
    System.out.println( e.getStackTrace() );
  }
}
