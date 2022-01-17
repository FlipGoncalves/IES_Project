package Project.TwitterBot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import Project.TwitterBot.model.SearchTweets.*;

public interface TweetSearchResponseRepository extends MongoRepository<TweetSearchResponse, Long> {

}