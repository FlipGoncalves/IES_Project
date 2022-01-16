package Project.TwitterBot.repository;

import java.util.List;
import Project.TwitterBot.model.Tweet;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import Project.TwitterBot.model.SearchTweets.*;

public interface TweetSearchResponseRepository extends MongoRepository<Tweet, Long> {

    Includes findByIncludes(Includes inc);
    List<Tweet> findByTag(Long tag);
}