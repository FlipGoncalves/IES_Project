package TwitterBot.TwitterService;

import TwitterBot.model.SearchTweets.Datum;
import TwitterBot.model.CountTweets.TweetCount;
import TwitterBot.model.TrendTweet.TweetTrendsJson;

import java.util.List;

public interface TTService {


  List<TweetTrendsJson> getTrends( long id );

  List<TweetCount> getInterestCount( String query );

  List<Datum> searchTweets( String query);

}
