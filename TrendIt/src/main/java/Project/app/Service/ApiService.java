package Project.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Project.app.Models.*;
import Project.app.Repositories.*;

import java.util.List;
import java.util.Date;

@Service
public class ApiService {
    @Autowired
    TweetSearchResponseRepository tweet_rep;
    @Autowired
    TweetTrendResponseRepository tweet_trend;
    @Autowired
    TweetCountResponseRepository tweet_count;
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

    public User getUserById(String user_id) {
        return (user_rep.findById(user_id) != null) ? user_rep.findById(user_id): new User();
    }

    public void deleteUserbyUsername(String username) {
        user_rep.delete((user_rep.findByUsername(username) != null) ? user_rep.findByUsername(username): null);
    }

    public void deleteUserbyId(String user_id) {
        user_rep.delete((user_rep.findById(user_id) != null) ? user_rep.findById(user_id): null);
    }

    public User updateUserById(String user_id, User user) {
        User us = user_rep.findById(user_id);
        if (us != null) {
            us.setUsername(user.getUsername());
            us.setPassword(user.getPassword());
            us.setInterests(user.getInterests());
            user_rep.delete(user);
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
            user_rep.delete(user);
            return user_rep.save(us);
        }
        return null;
    }


    // TWEETS

/*
    public Tweet saveTweet(Tweet tweet) {
        tweet.setInsert(new Date());
        return tweet_rep.save(tweet);
    }

    public List<Tweet> saveTweets(List<Tweet> list_tweets) {
        return tweet_rep.saveAll(list_tweets);
    }

    public List<Tweet> getAllTweets() {
        return tweet_rep.findAll();
    }

    public Tweet getTweetById(String id) {
        return (tweet_rep.findById(id) != null) ? tweet_rep.findById(id): new Tweet();
    }

    public void deleteTweet(String id) {
        tweet_rep.delete((tweet_rep.findById(id) != null) ? tweet_rep.findById(id): null);
    }

    public Tweet updateTweet(String id, Tweet tweet) {
        Tweet tw = tweet_rep.findById(id);
        if (tw != null) {
            tw.setDescription(tweet.getDescription());
            tw.setTrends(tweet.getTrends());
            tw.setPerson(tweet.getPerson());
            tweet_rep.delete(tw);
            return tweet_rep.save(tw);
        }
        return null;
    }
    */

    // datum -> tweet
    public  Datum saveTweet (Datum d){
        return tweet_rep.save( d );

    }

    public List<Datum> getAllTweets() {
        return tweet_rep.findAll();
    }

    /*
    public Tweet updateTweet(String id, Datum datum) {
        Tweet tw = tweet_rep.findById(id);
        if (tw != null) {
            tw.setDescription(tweet.getDescription());
            tw.setTrends(tweet.getTrends());
            tw.setPerson(tweet.getPerson());
            tweet_rep.delete(tw);
            return tweet_rep.save(tw);
        }
        return null;
    }
    */

    public  TweetCount saveTweetCount (TweetCount d){
        return tweet_count.save( d );

    }

    public List<TweetCount> getAllTweetCount() {
        return tweet_count.findAll();
    }


    public  TweetTrendsJson saveTweetTrend (TweetTrendsJson d){
        return tweet_trend.save( d );

    }

    public List<TweetTrendsJson> getAllTweetTrend() {
        return tweet_trend.findAll();
    }

    public void updateTweet( String tweet_id, Datum tweet ) {
        return;
    }
}
