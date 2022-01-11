package Project.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Project.app.Models.*;
import Project.app.Repositories.*;

import java.util.List;

@Service
public class ApiService {
    @Autowired
    TweetRepository tweet_rep;
    @Autowired
    UserRepository user_rep;


    // USERS

    public User saveUser(User user) {
        return user_rep.save(user);
    }

    public List<User> saveUsers(List<User> list_users) {
        return user_rep.saveAll(list_users);
    }

    public List<User> getAllUsers() {
        return user_rep.findAll();
    }

    public User getUserbyUsername(String username) {
        return (user_rep.findByUsername(username) != null) ? user_rep.findByUsername(username): new User();
    }
    
    public User getUserById(int user_id) {
        return (user_rep.findById(user_id) != null) ? user_rep.findById(user_id): new User();
    }

    public void deleteUserbyUsername(String username) {
        user_rep.delete((user_rep.findByUsername(username) != null) ? user_rep.findByUsername(username): null);
    }

    public void deleteUserbyId(int user_id) {
        user_rep.delete((user_rep.findById(user_id) != null) ? user_rep.findById(user_id): null);
    }

    public User updateUserById(int user_id, User user) {
        User us = user_rep.findById(user_id);
        if (us != null) {
            us.setUsername(user.getUsername());
            us.setPassword(user.getPassword());
            us.setInterests(user.getInterests());
            return user_rep.save(us);
        }
        return null;
    }

    public User updateUserByUsername(String username, User user) {
        User us = user_rep.findByUsername(username);
        if (us != null) {
            us.setUsername(user.getUsername());
            us.setPassword(user.getPassword());
            us.setInterests(user.getInterests());
            return user_rep.save(us);
        }
        return null;
    }
}