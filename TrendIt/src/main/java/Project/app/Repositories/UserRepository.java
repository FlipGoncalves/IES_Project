package Project.app.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import Project.app.Models.User;

@Repository
    public interface UserRepository extends MongoRepository<User, Integer>{
        public User findById(String id);
        public User findByUsername(String username);
}