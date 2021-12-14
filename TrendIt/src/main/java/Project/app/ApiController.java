package Project.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Project.app.Repositories.*;
import Project.app.Models.*;
import Project.app.Exceptions.*;

@RestController
@RequestMapping("/Trend_It")
public class ApiController {
    @Autowired
    private TweetRepository tweets_rep;
    @Autowired
    private UserRepository user_rep;

    @GetMapping("/all_tweets")
    public List<Tweet> getAllTweets(@RequestParam(value = "trend", required = false) String trend) {
        if(trend != null){
            List<Tweet> tweet = tweets_rep.findAllByTrend(trend);
            return tweet;
        }
        return tweets_rep.findAll();
    }

    @GetMapping("/tweet_get/{id}")
    public ResponseEntity<Tweet> getTweetById(@PathVariable(value = "id" ) Integer tweet_id) throws ResourceNotFoundException {
        Tweet tweet = tweets_rep.findById(tweet_id).orElseThrow(() -> new ResourceNotFoundException("Tweet not found for this id ::" + tweet_id));
        return ResponseEntity.ok().body(tweet);
    }

    @PostMapping("/insert_tweet")
    public Tweet insertTweet(@Valid @RequestBody Tweet tweet){
        return tweets_rep.save(tweet);
    }

    // @PutMapping("/user/{id}")
    // public ResponseEntity<Tweet> updateUser(@PathVariable(value = "id") Integer user_id, @Valid @RequestBody User user) throws ResourceNotFoundException{
    //     Tweet tweet = tweets_rep.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + user_id));

    //     tweet.setDescription(user.getDescription());
    //     tweet.setTrends(user.getTrends());
    //     tweet.setPerson(user.getPerson());
    //     final Tweet updated_tweet = tweets_rep.save(tweet);
    //     return ResponseEntity.ok(updated_tweet);
    // }

    @DeleteMapping("/tweet_del/{id}")
    public Map<String, Boolean> deleteTweet(@PathVariable(value = "id") Integer tweet_id) throws ResourceNotFoundException{
        Tweet tweet = tweets_rep.findById(tweet_id).orElseThrow(() -> new ResourceNotFoundException("Tweet not found for this id :: " + tweet_id));

        tweets_rep.delete(tweet);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/all_users")
    public List<User> getAllUsers() {
        return user_rep.findAll();
    }

    // @GetMapping("/user_get/{id}")
    // public ResponseEntity<User> getUserById(@PathVariable(value = "id" ) Integer user_id) throws ResourceNotFoundException {
    //     User user = user_rep.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User not found for this id ::" + user_id));
    //     return ResponseEntity.ok().body(user);
    // }

    @PostMapping("/insert_user")
    public User insertUser(@Valid @RequestBody User user){
        return user_rep.save(user);
    }
}
