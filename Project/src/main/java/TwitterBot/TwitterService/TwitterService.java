package TwitterBot.TwitterService;

import TwitterBot.APIInterface.APITwitter;
import TwitterBot.TwitterBotApp;
import TwitterBot.model.CountTweets.TweetCount;
import TwitterBot.model.CountTweets.TweetCountResponse;
import TwitterBot.model.SearchTweets.Datum;
import TwitterBot.model.SearchTweets.TweetSearchResponse;
import TwitterBot.model.TrendTweet.TweetTrendsJson;
import TwitterBot.model.TrendTweet.TweetTrendsResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TwitterService implements TTService {
  private static final Logger logger = LogManager.getLogger( "TwitterService" );

  /**
   * @param id city id according to twitter city id assignment available at
   *           https://api.twitter.com/1.1/trends/available.json
   *           This method takes an id to retrieve trends from a certain place
   *           1 being worldwide
   *           This call to the api returns a TweetTrendJson obj
   */
  @Override public List<TweetTrendsJson> getTrends( long id ) {
    logger.debug( "First Service getTrends for place: " + id );

    final Retrofit apiV1_1 =
      new Retrofit.Builder().baseUrl( "https://api.twitter.com/1.1/" )
                            .addConverterFactory( GsonConverterFactory.create() )
                            .build();

    List<TweetTrendsJson> result = new ArrayList<>();

    APITwitter client = apiV1_1.create( APITwitter.class );

    Call<List<TweetTrendsResponse>> callTargetResponse = client.getTrends( id + "",
      "Bearer " + TwitterBotApp.token );

    Response<List<TweetTrendsResponse>> execute = null;
    try {
      execute = callTargetResponse.execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
    logger.debug( execute.code() );
    if ( execute != null && execute.isSuccessful()){
      logger.debug( execute.body() );
      return execute.body().get( 0 ).getTrends();
    }
    else{
      logger.error( "ERRO" );
    }

    /*callTargetResponse.enqueue( new Callback<>() {
      @Override public void onResponse( Call<List<TweetTrendsResponse>> call,
                                        Response<List<TweetTrendsResponse>> response ) {
        assert response.body() != null;
        logger.debug( "RESPONSE ->" + response.body() );
        response.body().get( 0 ).getTrends().forEach( logger::debug );
        result.addAll( response.body().get( 0 ).getTrends() ); // add every trend FIXME why the fck you no work
        // to the result variable so that after that we can send it through RabbitMQ
      }
      @Override public void onFailure( Call<List<TweetTrendsResponse>> call, Throwable throwable ) {
        logger.error( "Something Went Wrong: " + throwable );
      }
    } );*/


    logger.info( "Last get Trends result  -> " + result );
    return result;
  }

  /**
   * @param query query that must respect
   *              https://developer.twittercom/en/docs/twitter-api/tweets/search/integrate/build-a-query this
   *              basically yes a query language
   *              for their api
   * @return a List of TweetCounts which basically an object that has a theme and a date and finally how many tweets
   * were posted
   */
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
        result.addAll( response.body().getTweetCountList() ); // FIXME why the fck you no work
        //TODO save the full request or each TweetCount?
        // add every trend
        // to the result variable so that after that we can send it through RabbitMQ
      }

      @Override public void onFailure( Call<TweetCountResponse> call, Throwable throwable ) {
        logger.error( "Something Went Wrong: " + throwable );
      }
    } );

    logger.info( "Result from getInterestCount -> " + result );
    return result;
  }

  //TODO THIS ONE NEEDS TO BE REVIEWED THIS Query as a lot of parameters and we need to decide what to do and with what

  /**
   * Tweets that match a certain query this returns a set of those tweets and some sort of pagination
   * and if we want to further explore more of a certain query we should provide a token id of the last tweet seen
   *
   * @param query
   * @return
   */
  @Override public List<Datum> searchTweets( String query ) {
    logger.debug( "Service getInterestCount for query: " + query );
    final Retrofit apiV2 =
      new Retrofit.Builder().baseUrl( "https://api.twitter.com/2/" )
                            .addConverterFactory( GsonConverterFactory.create() )
                            .build();

    List<Datum> result = new ArrayList<>();
    APITwitter client = apiV2.create( APITwitter.class );
    // TODO make it dynamic allow for more stuff such has next page token max results and more
    Call<TweetSearchResponse> callTargetResponse = client.searchTweets( "from: TwitterDev",
      "Bearer " + TwitterBotApp.token );

    callTargetResponse.enqueue( new Callback<>() {
      @Override public void onResponse( Call<TweetSearchResponse> call, Response<TweetSearchResponse> response ) {

        logger.debug( "RESPONSE ->" + response.body() );
        assert response.body() != null;
        for (Datum datum : response.body().getData()) {
          logger.debug( datum.getText() );
        }
        result.addAll( response.body().getData() ); // FIXME why the fck you no work
        //TODO save the full request or each data[i]
        // add every datum
        // to the result variable so that after that we can send it through RabbitMQ
      }

      @Override public void onFailure( Call<TweetSearchResponse> call, Throwable throwable ) {
        logger.error( "Something Went Wrong: " + throwable );
      }
    } );

    logger.info( "searchTweets result -> " + result );
    return result;
  }
}
