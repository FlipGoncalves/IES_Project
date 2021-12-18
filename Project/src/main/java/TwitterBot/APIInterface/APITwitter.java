package TwitterBot.APIInterface;


import TwitterBot.model.TweetTrendsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import java.util.List;

public interface APITwitter {
  @Headers({"Content-Type: application/json;charset=UTF-8"})
  @GET("trends/place.json")
  Call<List<TweetTrendsResponse>> getTrends( @Query("id") String id, @Query("max_results") int maxT,
                                             @Header("Authorization") String auth );
  
}
