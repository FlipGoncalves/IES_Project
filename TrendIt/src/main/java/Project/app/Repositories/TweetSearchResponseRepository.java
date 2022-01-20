package Project.app.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import Project.app.Models.Datum;

public interface TweetSearchResponseRepository extends MongoRepository<Datum, Long> {

}