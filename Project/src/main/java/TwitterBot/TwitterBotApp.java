package TwitterBot;

import TwitterBot.Services.TwitterService;
import TwitterBot.controller.TweetController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import twitter4j.Twitter;
import twitter4j.TwitterException;


// useful site : https://twitter4j.org/en/code-examples.html


@SpringBootApplication
@ComponentScan(basePackageClasses=TweetController.class)
@ComponentScan(basePackageClasses= TwitterService.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class TwitterBotApp {
  static Twitter twitter;
  public static String token = null;
  public static void main( String[] args ) throws TwitterException {

    token = System.getenv( "token" );
    if ( token == null && ( token.isEmpty() || token.isBlank() ) ) {
      System.out.println( "Bearer Token not set" );
      System.exit( - 1 );
    }

    SpringApplication.run( TwitterBotApp.class, args );
  }

// access the twitter API using your twitter4j.properties file
// The factory instance is re-useable and thread safe.
}
