package Project.TwitterBot.repository;

import Project.TwitterBot.model.TrendTweet.TweetTrendsResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrendsRepository extends MongoRepository<TweetTrendsResponse, String> {
}
