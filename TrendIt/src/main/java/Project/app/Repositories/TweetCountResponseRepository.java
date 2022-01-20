package Project.app.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import Project.app.Models.TweetCount;

public interface TweetCountResponseRepository extends MongoRepository<TweetCount, Long> {

}