package Project.app.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Project.app.Repositories.*;
import Project.app.Models.*;
import Project.app.Exceptions.*;
import Project.app.Service.*;

@RestController
@RequestMapping("/TrendIt")
public class ApiController {
    @Autowired
    private TweetRepository tweets_rep;
    @Autowired
    private ApiService service;

    // TWEETS

    @GetMapping("/all_tweets")
    public Set<Tweet> getAllTweets(@RequestParam(value = "trends", required = false) String trends) {
        if(trends != null){
            Set<Tweet> tweet = tweets_rep.findAllByTrends(trends);
            return tweet;
        }
        return null;
    }

    @GetMapping("/get_tweet{id}")
    public ResponseEntity<Tweet> getTweetById(@PathVariable(value = "id" ) Integer tweet_id) throws ResourceNotFoundException {
        Tweet tweet = tweets_rep.findById(tweet_id).orElseThrow(() -> new ResourceNotFoundException("Tweet not found for this id ::" + tweet_id));
        return ResponseEntity.ok().body(tweet);
    }

    @PostMapping("/insert_tweet")
    public Tweet insertTweet(@Valid @RequestBody Tweet tweet){
        return tweets_rep.save(tweet);
    }

    @PutMapping("/update_tweet/{id}")
    public ResponseEntity<Tweet> updateUser(@PathVariable(value = "id") Integer tweet_id, @Valid @RequestBody Tweet tweet) throws ResourceNotFoundException{
        Tweet tw = tweets_rep.findById(tweet_id).orElseThrow(() -> new ResourceNotFoundException("Tweet not found for this id :: " + tweet_id));

        tw.setDescription(tweet.getDescription());
        tw.setTrends(tweet.getTrends());
        tw.setPerson(tweet.getPerson());
        final Tweet updated_tweet = tweets_rep.save(tw);
        return ResponseEntity.ok(updated_tweet);
    }

    @DeleteMapping("/delete_tweet/{id}")
    public Map<String, Boolean> deleteTweet(@PathVariable(value = "id") Integer tweet_id) throws ResourceNotFoundException{
        Tweet tweet = tweets_rep.findById(tweet_id).orElseThrow(() -> new ResourceNotFoundException("Tweet not found for this id :: " + tweet_id));

        tweets_rep.delete(tweet);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
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
    public String deleteUserById(@PathVariable(value = "id") Integer user_id) {
        service.deleteUserbyId(user_id);
        return "Deleted User with ID: " + user_id;
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
    public Map<String, Boolean> deleteUserByUsername(@PathVariable(value = "username") String username) throws ResourceNotFoundException {
        service.deleteUserbyUsername(username);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/update_user_username/{username}")
    public ResponseEntity<User> updateUserByUsername(@PathVariable(value = "username") String username, @Valid @RequestBody User user) throws ResourceNotFoundException {
        final User updatedUser = service.updateUserByUsername(username, user);
        return ResponseEntity.ok(updatedUser); 
    }
}
