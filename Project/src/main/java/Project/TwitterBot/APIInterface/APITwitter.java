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
  Call<TweetCountResponse> getCount( @Query(value = "query", encoded = false) String query,
                                     @Query("granularity") String granularity,
                                     @Header("Authorization") String auth );
  
  // TODO allow for more things to be added here!!
  @Headers({"Content-Type: application/json;charset=UTF-8"})
  @GET("tweets/search/recent")
  Call<TweetSearchResponse> searchTweets( @Query(value = "query", encoded = false) String query,
                                          @Query(value = "max_results", encoded = false) int i,
                                          @Query(value = "expansions", encoded = false) String s,
                                          @Query(value = "tweet.fields",
                                            encoded = false) String s1,
                                          @Query(value = "user.fields", encoded = false) String s2,
                                          @Header("Authorization") String auth );
}
  
  //https://api.twitter.com/2/tweets/search/recent?query=%25E3%2583%2599%25E3%2583%25A9%25E3%2583%25AA%25E3%2582%25B6
// &max_results%3D=10&expansions%3Dauthor_id=&tweet.fields%3Dcreated_at%2Clang%2Cconversation_id=&user.fields%3Dcreated_at%2Centities=}