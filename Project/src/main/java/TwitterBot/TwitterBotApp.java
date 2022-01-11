package TwitterBot;

import TwitterBot.TwitterService.TwitterService;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


// useful site : https://twitter4j.org/en/code-examples.html


@SpringBootApplication
//@ComponentScan("com.app.repository.TweetRepository")
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
    TwitterService ts = new TwitterService();
    ts.getTrends( 1 );
    //SpringApplication.run( TwitterBotApp.class, args );
  }

  // access the twitter API using your twitter4j.properties file
  // The factory instance is re-useable and thread safe.
  private static void error( Exception e ) {
    System.out.println( e.getStackTrace() );
  }
}
