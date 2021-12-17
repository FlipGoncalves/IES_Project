package TwitterBot.repository;

import java.util.List;
import TwitterBot.model.Tweet;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TweetRepository extends MongoRepository<Tweet, Long> {

    List<Tweet> findByTag(Long tag);
}