package Project.TwitterBot.APIInterface;



import Project.TwitterBot.model.CountTweets.TweetCountResponse;
import Project.TwitterBot.model.SearchTweets.TweetSearchResponse;
import Project.TwitterBot.model.TrendTweet.TweetTrendsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import java.util.List;

public interface APITwitter {
  @Headers({"Content-Type: application/json;charset=UTF-8"})
  @GET("trends/place.json")
  Call<List<TweetTrendsResponse>> getTrends( @Query("id") String id,
                                             @Header("Authorization") String auth );

  @Headers({"Content-Type: application/json;charset=UTF-8"})
  @GET("tweets/counts/recent")
  Call<TweetCountResponse> getCount( @Query("query") String query, @Query("granularity") String granularity,
                                     @Header("Authorization") String auth );

  // TODO allow for more things to be added here!!
  @Headers({"Content-Type: application/json;charset=UTF-8"})
  @GET("tweets/search/recent")
  Call<TweetSearchResponse> searchTweets( @Query("query") String query,
                                          @Header("Authorization") String auth );
}
