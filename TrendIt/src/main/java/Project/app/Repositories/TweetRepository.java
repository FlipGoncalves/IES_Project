package Project.app.Repositories;

import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import Project.app.Models.Tweet;

@Repository
public interface TweetRepository extends MongoRepository<Tweet, Integer>{
    public Tweet findById(int id);
    public List<Tweet> findAllByTrend(String trend);
}
