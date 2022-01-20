package Project.TwitterBot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import Project.TwitterBot.model.TrendTweet.*;

public interface TweetTrendResponseRepository extends MongoRepository<TweetTrendsResponse, Long> {

}