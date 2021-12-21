package TwitterBot.Services;

import TwitterBot.APIInterface.APITwitter;
import TwitterBot.TwitterBotApp;
import TwitterBot.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class TwitterService implements Service {
  private static final Logger logger = LogManager.getLogger( "TweetController" );
  
  /**
   * @param id city id according to twitter city id assignment available at
   *           https://api.twitter.com/1.1/trends/available.json
   *           This method takes an id to retrieve trends from a certain place
   *           1 being worldwide
   *           This call to the api returns a TweetTrendJson obj
   */
  @Override public List<TweetTrendsJson> getTrends( long id ) {
    logger.debug( "Service getTrends for place: " + id );
    
    final Retrofit apiV1_1 =
      new Retrofit.Builder().baseUrl( "https://api.twitter.com/2/" )
                            .addConverterFactory( GsonConverterFactory.create() )
                            .build();
    
    List<TweetTrendsJson> result = new ArrayList<>();
    APITwitter client = apiV1_1.create( APITwitter.class );
    
    
    Call<List<TweetTrendsResponse>> callTargetResponse = client.getTrends( id + "", 2,
      "Bearer " + TwitterBotApp.token );
    
    
    callTargetResponse.enqueue( new Callback<>() {
      
      @Override public void onResponse( Call<List<TweetTrendsResponse>> call,
                                        Response<List<TweetTrendsResponse>> response ) {
        
        logger.debug( "RESPONSE ->" + response.body() );
        assert response.body() != null;
        response.body().get( 0 ).getTrends().forEach( logger::debug );
        result.addAll( response.body().get( 0 ).getTrends() ); // add every trend
        // to the result vaiable so that after that we can send it through rabitmq
        
      }
      
      @Override public void onFailure( Call<List<TweetTrendsResponse>> call, Throwable throwable ) {
        logger.error( "Something Went Wrong: " + throwable );
      }
    } );
    
    
    logger.info( callTargetResponse );
    return result;
  }
  
  @Override public List<TweetCount> getInterestCount( String query ) {
    
    logger.debug( "Service getInterestCount for query: " + query );
    final Retrofit apiV2 =
      new Retrofit.Builder().baseUrl( "https://api.twitter.com/2/" )
                            .addConverterFactory( GsonConverterFactory.create() )
                            .build();
    
    List<TweetCount> result = new ArrayList<>();
    APITwitter client = apiV2.create( APITwitter.class );
    
    
    Call<TweetCountResponse> callTargetResponse = client.getCount( query, "day",
      "Bearer " + TwitterBotApp.token );
    
    
    callTargetResponse.enqueue( new Callback<>() {
      
      @Override public void onResponse( Call<TweetCountResponse> call,
                                        Response<TweetCountResponse> response ) {
        
        logger.debug( "RESPONSE ->" + response.body() );
        assert response.body() != null;
        response.body().getTweetCountList().forEach( logger::debug );
        result.addAll( response.body().getTweetCountList() );
        //TODO save the full request or each TweetCount?
        // add every trend
        // to the result variable so that after that we can send it through RabbitMQ
        
      }
      
      @Override public void onFailure( Call<TweetCountResponse> call, Throwable throwable ) {
        logger.error( "Something Went Wrong: " + throwable );
      }
    } );
    logger.info( result );
    return result;
  }
  
  //TODO THIS ONE NEEDS TO BE REVIEWED THIS Query as a lot of parameters and we need to decide what to do and with what
  @Override public List<Tweets> searchTweets( String query ) {
    logger.debug( "Service getInterestCount for query: " + query );
    final Retrofit apiV2 =
      new Retrofit.Builder().baseUrl( "https://api.twitter.com/2/" )
                            .addConverterFactory( GsonConverterFactory.create() )
                            .build();
    
    List<Datum> result = new ArrayList<>();
    APITwitter client = apiV2.create( APITwitter.class );
    Call<TweetSearchResponse> callTargetResponse = client.searchTweets( "from: TwitterDev",
      "Bearer " + TwitterBotApp.token );
    
    callTargetResponse.enqueue( new Callback<>() {
      @Override public void onResponse( Call<TweetSearchResponse> call, Response<TweetSearchResponse> response ) {
  
        logger.debug( "RESPONSE ->" + response.body() );
        assert response.body() != null;
        for (Datum datum : response.body().getData()) {
          logger.debug( datum.getText() );
        }
        result.addAll( response.body().getData() );
        //TODO save the full request or each data[i]
        // add every datum
        // to the result variable so that after that we can send it through RabbitMQ
      }
  
      @Override public void onFailure( Call<TweetSearchResponse> call, Throwable throwable ) {
    
        logger.error( "Something Went Wrong: " + throwable );
      }
    } );
    
    return null;
  }
}
