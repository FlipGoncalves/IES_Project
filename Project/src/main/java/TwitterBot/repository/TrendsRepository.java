package TwitterBot.repository;

import TwitterBot.TweetTrendsResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrendsRepository extends MongoRepository<TweetTrendsResponse, String> {
}
