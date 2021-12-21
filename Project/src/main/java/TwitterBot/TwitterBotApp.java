package TwitterBot;

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
@ComponentScan("com.app.repository.TweetRepository")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class TwitterBotApp {
  static Twitter twitter;
  
  public static void main( String[] args ) throws TwitterException {
    String tweetResponse = null;
    
    CloseableHttpClient httpClient = HttpClients.custom()
                                                .setDefaultRequestConfig(
                                                  RequestConfig.custom()
                                                               .setCookieSpec( CookieSpecs.STANDARD )
                                                               .build()
                                                )
                                                .build();
    
    String ids = "1138505981460193280,1261326399320715264";
<<<<<<< HEAD
    String bearerToken = System.getenv( "ACCESS_TOKEN" );  // ACCESS_TOKEN="<BEARER TOKEN>"
=======
    String bearerToken = System.getenv( "ACCESS_TOKEN" );
>>>>>>> b6d54cddca7f7f240698b2c78a545fa4917ecc4e
    URIBuilder uriBuilder = null;
    try {
      uriBuilder = new URIBuilder( "https://api.twitter.com/2/tweets" );
    } catch (URISyntaxException e) {
      error( e );
    }
    ArrayList<NameValuePair> queryParameters;
    queryParameters = new ArrayList<>();
    queryParameters.add( new BasicNameValuePair( "ids", ids ) );
    queryParameters.add( new BasicNameValuePair( "tweet.fields", "created_at" ) );
    uriBuilder.addParameters( queryParameters );
    
    HttpGet httpGet = null;
    try {
      httpGet = new HttpGet( uriBuilder.build() );
    } catch (
      URISyntaxException e) {
      error( e );
    }
    httpGet.setHeader( "Authorization", String.format( "Bearer %s", bearerToken ) );
    httpGet.setHeader( "Content-Type", "application/json" );
    CloseableHttpResponse response = null;
    try {
      response = httpClient.execute( httpGet ); //
    } catch (IOException e) {
      error( e );
    }
    HttpEntity entity = null;
    entity = response.getEntity();
    if ( null != entity ) {
      try {
        tweetResponse = EntityUtils.toString( entity, "UTF-8" ); //
      } catch (IOException e) {
        error( e );
      }
    }
    System.out.println(  );
    System.out.println(  );
    System.out.println(  );
    System.out.println( tweetResponse );
    System.out.println(  );
    System.out.println(  );
    System.out.println(  );
    try {
      uriBuilder = new URIBuilder("https://api.twitter.com/2/tweets/search/recent");
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    queryParameters = new ArrayList<>();
<<<<<<< HEAD
    queryParameters.add(new BasicNameValuePair("query", "from:x    OR from:KingJames OR from:DailyNASA"));
=======
    queryParameters.add(new BasicNameValuePair("query", "from:hyperlegen OR from:KingJames OR from:DailyNASA"));
>>>>>>> b6d54cddca7f7f240698b2c78a545fa4917ecc4e
    uriBuilder.addParameters(queryParameters);
  
    try {
      httpGet = new HttpGet(uriBuilder.build());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    httpGet.setHeader("Authorization", String.format("Bearer %s", bearerToken));
    httpGet.setHeader("Content-Type", "application/json");
  
    try {
      response = httpClient.execute(httpGet);
    } catch (IOException e) {
      e.printStackTrace();
    }
    entity = response.getEntity();
    String searchResponse = null;
    if (null != entity) {
      try {
        searchResponse = EntityUtils.toString(entity, "UTF-8");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    System.out.println(  );
    System.out.println(  );
    System.out.println(  );
    System.out.println(searchResponse);
    System.out.println(  );
    System.out.println(  );
    System.out.println(  );
    SpringApplication.run( TwitterBotApp.class, args );
  }
  
  // access the twitter API using your twitter4j.properties file
  // The factory instance is re-useable and thread safe.
  private static void error( Exception e ) {
    System.out.println( e.getStackTrace() );
  }
}
    
