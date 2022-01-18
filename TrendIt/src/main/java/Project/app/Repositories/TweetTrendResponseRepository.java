package Project.app.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import Project.app.Models.TweetTrendsJson;

public interface TweetTrendResponseRepository extends MongoRepository<TweetTrendsJson, Long> {

}