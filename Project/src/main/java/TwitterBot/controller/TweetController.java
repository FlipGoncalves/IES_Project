package TwitterBot.controller;

import TwitterBot.Services.TwitterService;
import TwitterBot.model.SearchTweets.Datum;
import TwitterBot.model.CountTweets.TweetCount;
import TwitterBot.model.TrendTweet.TweetTrendsJson;
import TwitterBot.repository.TweetRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@NoArgsConstructor
public class TweetController {
  @Autowired private TweetRepository tweetRepository;
  @Autowired private TwitterService twitterService;
  
  private static final Logger logger = LogManager.getLogger( "TweetController" );
  
  @GetMapping("/trends") public List<TweetTrendsJson> getTrends( @RequestParam("id") Long[] cityWoeid ) {
    List<TweetTrendsJson> result = null;
    for (int i = 0 ; i < cityWoeid.length ; i++) {
      return  twitterService.getTrends( cityWoeid[0] );
      //result.addAll( twitterService.getTrends( cityWoeid[i] ) );
      //TODO twitterService.saveTrends(); save which trend or save the full response body?
      
    }
    return result;
  }
  
  @GetMapping("/tweet_count")
  public ResponseEntity<List<TweetCount>> tweetCounts( @RequestParam("query") String query ) {
    logger.info( "Tweet_count " + query );
    ResponseEntity<List<TweetCount>> tweetCount =
      (ResponseEntity<List<TweetCount>>) twitterService.getInterestCount( query );
    return tweetCount;
  }
  
  @GetMapping("/search")
  public List<Datum> filteredStreamSearch( @RequestParam("query") String query){
    return twitterService.searchTweets( query );
  }
  
  
}