package TwitterBot;

import TwitterBot.APIInterface.APITwitter;
import TwitterBot.Services.TwitterService;
import TwitterBot.controller.TweetController;
import TwitterBot.model.CountTweets.TweetCountResponse;
import TwitterBot.model.TrendTweet.TweetTrendsResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import TwitterBot.model.*;

import java.util.List;


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
    /*
    // TODO extract this to an endpoint of our controller so that main project can poll this into his own database
    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl( "https://api.twitter.com/2/" )
      .addConverterFactory( GsonConverterFactory.create() )
      .build();
    APITwitter client = retrofit.create( APITwitter.class );
    Call<TweetCountResponse> calltargetResponse = client.getCount( "lakers", "day", "Bearer " + token );
    calltargetResponse.enqueue( new Callback<TweetCountResponse>() {
      @Override public void onResponse( Call<TweetCountResponse> call,
                                        Response<TweetCountResponse> response ) {
        System.out.println();
        System.out.println();
        System.out.println( "------------------" );
        System.out.println( "This -> " + this + "\n RESPONSE ->" + response.body() );
        response.body().getTweetCountList().forEach( System.out::println );
        System.out.println( "In The last 7days there were " + response.body().getTtCount() + " tweets matching this " +
          "query." );
        System.out.println( "Tambem da para ver o dia em que foi mais popular se fizermos uma stream da lista " +
          "tweetCountList" );
        System.out.println( "------------------" );
        System.out.println( "------------------" );
        System.out.println();
      }

      @Override public void onFailure( Call<TweetCountResponse> call, Throwable throwable ) {
        System.out.println( call );
        System.out.println( call.request() );
        System.err.println( throwable.getCause() );
        System.out.println( "Something Went Wrong" );
        System.exit( - 1 );
      }
    } );

    // TODO extract this into a controller so that the main project can poll this for updates some endpoints do not
    //  use this baseUrl
    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl( "https://api.twitter.com/1.1/" )
      .addConverterFactory( GsonConverterFactory.create() )
      .build();
    APITwitter client = retrofit.create( APITwitter.class );
    Call<List<TweetTrendsResponse>> calltargetResponse = client.getTrends( "1", "Bearer " + token );
    calltargetResponse.enqueue( new Callback<List<TweetTrendsResponse>>() {
      @Override public void onResponse( Call<List<TweetTrendsResponse>> call,
                                        Response<List<TweetTrendsResponse>> response ) {
        System.out.println();
        System.out.println();
        System.out.println( "------------------" );
        System.out.println( "This -> " + this + "\n RESPONSE ->" + response.body() );
        response.body().get( 0 ).getTrends().forEach( System.out::println );
        System.out.println( "------------------" );
        System.out.println( "------------------" );
        System.out.println();
      }

      @Override public void onFailure( Call<List<TweetTrendsResponse>> call, Throwable throwable ) {
        System.out.println( call );
        System.out.println( call.request() );
        System.err.println( throwable.getCause() );
        System.out.println( "Something Went Wrong" );
        System.exit( - 1 );
      }
    } );
    */

    TwitterService twitterService = new TwitterService();
    twitterService.getTrends( 1 );
    twitterService.searchTweets( "query=from%3Atwitterdev%20new%20-is%3Aretweet&max_results=10" );
    twitterService.getInterestCount( "query=from%3Atwitterdev%20new%20-is%3Aretweet");

    SpringApplication.run( TwitterBotApp.class, args );
  }

// access the twitter API using your twitter4j.properties file
// The factory instance is re-useable and thread safe.
}
