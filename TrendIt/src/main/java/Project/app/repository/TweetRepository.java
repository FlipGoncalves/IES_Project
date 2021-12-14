package Project.app.repository;

import java.util.List;
import Project.app.model.Tweet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet,Long> {

    @Query(value = "SELECT * FROM Tweet t where t.tag = ?1 ", nativeQuery=true)
    List<Tweet> findByTag(String tag);
}