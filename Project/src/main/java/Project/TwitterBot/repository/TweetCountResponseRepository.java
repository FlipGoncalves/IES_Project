package Project.TwitterBot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import Project.TwitterBot.model.CountTweets.*;

public interface TweetCountResponseRepository extends MongoRepository<TweetCountResponse, Long> {

}