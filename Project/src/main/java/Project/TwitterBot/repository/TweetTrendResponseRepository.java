package Project.TwitterBot.repository;

import java.util.List;
import Project.TwitterBot.model.Tweet;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import Project.TwitterBot.model.TrendTweet.*;

public interface TweetTrendResponseRepository extends MongoRepository<Tweet, Long> {

    List<TweetTrendsJson> findByTrend(TweetTrendsJson trend);
    List<Tweet> findByTag(Long tag);
}