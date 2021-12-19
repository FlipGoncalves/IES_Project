package TwitterBot.Services;

import TwitterBot.APIInterface.APITwitter;
import TwitterBot.TwitterBotApp;
import TwitterBot.model.TweetCount;
import TwitterBot.model.TweetCountResponse;
import TwitterBot.model.TweetTrendsJson;
import TwitterBot.model.TweetTrendsResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class TwitterService implements Service {
  private static Logger logger = LogManager.getLogger( "TweetController" );
  
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
    
    List<TweetTrendsJson> result = null;
    APITwitter client = apiV1_1.create( APITwitter.class );
    
    
    Call<List<TweetTrendsResponse>> calltargetResponse = client.getTrends( id + "", 2,
      "Bearer " + TwitterBotApp.token );
    
    
    calltargetResponse.enqueue( new Callback<>() {
      
      @Override public void onResponse( Call<List<TweetTrendsResponse>> call,
                                        Response<List<TweetTrendsResponse>> response ) {
        
        logger.debug( "RESPONSE ->" + response.body() );
        response.body().get( 0 ).getTrends().forEach( logger::debug );
        response.body().get( 0 ).getTrends().forEach( ( TweetTrendsJson t ) -> result.add( t ) ); // add every trend
        // to the result vaiable so that after that we can send it through rabitmq
        
      }
      
      @Override public void onFailure( Call<List<TweetTrendsResponse>> call, Throwable throwable ) {
        logger.error( "Something Went Wrong: " + throwable );
        return;
      }
    } );
    
    
    logger.info( calltargetResponse );
    return result;
  }
  
  @Override public List<TweetCount> getInterestCount( String query ) {
    
    logger.debug( "Service getInterestCount for query: " + query );
    final Retrofit apiV2 =
      new Retrofit.Builder().baseUrl( "https://api.twitter.com/2/" )
                            .addConverterFactory( GsonConverterFactory.create() )
                            .build();
    
    List<TweetCount> result = null;
    APITwitter client = apiV2.create( APITwitter.class );
    
    
    Call<TweetCountResponse> calltargetResponse = client.getCount( query, "day",
      "Bearer " + TwitterBotApp.token );
    
    
    calltargetResponse.enqueue( new Callback<>() {
      
      @Override public void onResponse( Call<TweetCountResponse> call,
                                        Response<TweetCountResponse> response ) {
        
        logger.debug( "RESPONSE ->" + response.body() );
        response.body().getTweetCountList().forEach( logger::debug );
        response.body().getTweetCountList().forEach( ( TweetCount t ) -> result.add( t ) );
        //TODO save the full request or each TweetCount?
        // add every trend
        // to the result vaiable so that after that we can send it through rabitmq
        
      }
      
      @Override public void onFailure( Call<TweetCountResponse> call, Throwable throwable ) {
        logger.error( "Something Went Wrong: " + throwable );
        return;
      }
    } );
    logger.info( result );
    return result;
  }
}
