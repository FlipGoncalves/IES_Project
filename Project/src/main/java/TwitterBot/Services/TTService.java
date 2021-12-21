package TwitterBot.Services;

import TwitterBot.model.Datum;
import TwitterBot.model.TweetCount;
import TwitterBot.model.TweetTrendsJson;

import java.util.List;

public interface TTService {
  
  
  List<TweetTrendsJson> getTrends( long id );
  
  List<TweetCount> getInterestCount( String query );
  
  List<Datum> searchTweets( String query);
  
}
