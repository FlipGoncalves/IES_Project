package Project.TwitterBot.TwitterService;


import Project.TwitterBot.model.SearchTweets.Datum;
import Project.TwitterBot.model.CountTweets.TweetCount;
import Project.TwitterBot.model.TrendTweet.TweetTrendsJson;

import java.util.List;

public interface TTService {


  List<TweetTrendsJson> getTrends( long id );

  List<TweetCount> getInterestCount( String query );

  List<Datum> searchTweets( String query);

}
