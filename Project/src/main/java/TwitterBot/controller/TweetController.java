package TwitterBot.controller;

import TwitterBot.Services.TwitterService;
import TwitterBot.model.TweetCount;
import TwitterBot.model.TweetTrendsJson;
import TwitterBot.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TweetController {
  @Autowired private TweetRepository tweetRepository;
  @Autowired private TwitterService twitterService;
  
  @GetMapping("/trends") public List<TweetTrendsJson> getTrends( @RequestParam("id") Long[] cityWoeid ) {
    List<TweetTrendsJson> result = null;
    for (int i = 0 ; i < cityWoeid.length ; i++) {
      result.addAll( twitterService.getTrends( cityWoeid[i] ) );
      //TODO twitterService.saveTrends(); save which trend or save the full response body?
      
    }
    return result;
  }
  
  @GetMapping("/tweet_count")
  public List<TweetCount> tweetCounts( @RequestParam("query") String query ) {
    
    return twitterService.getInterestCount( query );
  }
  
  @GetMapping("/search")
  public void filteredStreamSearch(@RequestParam("query") String query){
  
  }
  
  
}