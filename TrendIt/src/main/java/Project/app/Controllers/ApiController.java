package Project.app.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Project.app.Models.*;
import Project.app.Exceptions.*;
import Project.app.Service.*;
import Project.app.Repositories.*;

@RestController
@RequestMapping("/TrendIt")
public class ApiController {
    @Autowired
    private ApiService service;

    @Autowired
    private TweetRepository tweet_rep;

    @Autowired
    private UserRepository user_rep;

    // TWEETS

    @GetMapping("/all_tweets")
    public List<Tweet> getAllTweets(@RequestParam(value = "trends", required = false) String trends) {
        return service.getAllTweets();
    }

    @GetMapping("/get_tweet/{id}")
    public Tweet getTweetById(@PathVariable(value = "id" ) Integer tweet_id) throws ResourceNotFoundException {
        return service.getTweetById(tweet_id);
    }

    @PostMapping("/insert_tweet")
    public Tweet insertTweet(@Valid @RequestBody Tweet tweet){
        return service.saveTweet(tweet);
    }

    @PutMapping("/update_tweet/{id}")
    public String updateTweet(@PathVariable(value = "id") Integer tweet_id, @Valid @RequestBody Tweet tweet) throws ResourceNotFoundException{
        service.updateTweet(tweet_id, tweet);
        return "Updated Tweet with ID: " + tweet_id;
    }

    @DeleteMapping("/delete_tweet/{id}")
    public String deleteTweet(@PathVariable(value = "id") Integer tweet_id) throws ResourceNotFoundException{
        Tweet tweet = tweet_rep.findById(tweet_id).orElseThrow(() -> new ResourceNotFoundException("Tweet not found for this id :: " + tweet_id));
        if (tweet != null) {
            tweet_rep.deleteById(tweet_id);
            return "Deleted Tweet with ID: " + tweet_id;
        }
        return null;
    }


    // USERS
    @GetMapping("/all_users")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/get_user_id/{id}")
    public User getUserById(@PathVariable(value = "id" ) Integer user_id) {
        return service.getUserById(user_id);
    }

    @PostMapping("/insert_user")
    public User insertUser(@Valid @RequestBody User user){
        return service.saveUser(user);
    }

    @DeleteMapping("/delete_user_id/{id}")
    public String deleteUserById(@PathVariable(value = "id") Integer user_id) throws ResourceNotFoundException {
        User user = user_rep.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("Tweet not found for this id :: " + user_id));
        if (user != null) {
            user_rep.delete(user);
            return "Deleted User with ID: " + user_id;
        }
        return null;
    }

    @PutMapping("/update_user_id/{id}")
    public String updateUserById(@PathVariable(value = "id") Integer user_id, @Valid @RequestBody User user) {
        service.updateUserById(user_id, user);
        return "Updated User with ID: " + user_id; 
    }

    // User with CRUD methods for username
    @GetMapping("/get_user_username/{username}")
    public User getUserByUsername(@PathVariable(value = "username" ) String username) {
        return service.getUserbyUsername(username);
    }

    @DeleteMapping("/delete_user_username/{username}")
    public String deleteUserByUsername(@PathVariable(value = "username") String username) {
        User user = user_rep.findByUsername(username);
        if (user != null) {
            user_rep.delete(user);
            return "Deleted User with username: " + username;
        }
        return null;
    }

    @PutMapping("/update_user_username/{username}")
    public String updateUserByUsername(@PathVariable(value = "username") String username, @Valid @RequestBody User user) {
        service.updateUserByUsername(username, user);
        return "Updated User with username: " + username; 
    }
}
