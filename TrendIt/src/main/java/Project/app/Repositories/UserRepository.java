package Project.app.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import Project.app.Models.User;

@Repository
    public interface UserRepository extends MongoRepository<User, Long>{
        public User findById(int id);
}