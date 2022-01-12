package TwitterBot.repository;

import java.util.List;
import TwitterBot.model.Tweet;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import TwitterBot.model.TrendTweet.*;

public interface TweetTrendResponseRepository extends MongoRepository<Tweet, Long> {

    List<TweetTrendsJson> findByTrend(TweetTrendsJson trend);
    List<Tweet> findByTag(Long tag);
}