package TwitterBot.Services;

import TwitterBot.model.TweetCount;
import TwitterBot.model.TweetTrendsJson;
import TwitterBot.model.Tweets;

import java.util.List;

public interface Service {
  
  
  List<TweetTrendsJson> getTrends( long id );
  
  List<TweetCount> getInterestCount( String query );
  
  List<Tweets> searchTweets( String query);
  
}
