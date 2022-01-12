package Project.app.Repositories;

import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import Project.app.Models.Tweet;

@Repository
public interface TweetRepository extends MongoRepository<Tweet, Integer>{
    public Tweet findById(int id);
    public Set<Tweet> findAllByTrends(String trends);
    public Tweet deleteById(int id);
}
