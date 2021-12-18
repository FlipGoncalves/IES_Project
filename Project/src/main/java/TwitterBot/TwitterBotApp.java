package TwitterBot;

import TwitterBot.APIInterface.APITwitter;
import TwitterBot.model.TweetTrendsJson;
import TwitterBot.model.TweetTrendsResponse;
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

import java.io.IOException;
import java.util.List;


// useful site : https://twitter4j.org/en/code-examples.html


@SpringBootApplication
@ComponentScan("com.app.repository.TweetRepository")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class TwitterBotApp {
  static Twitter twitter;
  
  public static void main( String[] args ) throws TwitterException {
    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl( "https://api.twitter.com/1.1/" )
      .addConverterFactory( GsonConverterFactory.create() )
      .build();
    
    String token = System.getenv( "token" );
    if (token.isEmpty() || token.isBlank()){
      System.out.println("Bearer Token not set");
      System.exit( -1 );
    }
    APITwitter client = retrofit.create( APITwitter.class );
    Call<List<TweetTrendsResponse>> calltargetResponse = client.getTrends( "1", 2, "Bearer " + token );
    calltargetResponse.enqueue( new Callback<List<TweetTrendsResponse>>() {
      @Override public void onResponse( Call<List<TweetTrendsResponse>> call,
                                        Response<List<TweetTrendsResponse>> response ) {
        System.out.println();
        System.out.println();
        System.out.println("------------------");
        System.out.println("This -> " + this + "\n RESPONSE ->" + response.body());
        response.body().get( 0 ).getTrends().forEach( System.out::println );
        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println();
      }
      
      @Override public void onFailure( Call<List<TweetTrendsResponse>> call, Throwable throwable ) {
        System.out.println( call );
        System.out.println(call.request());
        System.err.println( throwable.getCause() );
        System.out.println( "Something Went Wrong" );
        System.exit( -1 );
      }
    } );
    SpringApplication.run( TwitterBotApp.class, args );
  }

// access the twitter API using your twitter4j.properties file
// The factory instance is re-useable and thread safe.
}
    
