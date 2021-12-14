package Project.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.Trends;

import Project.app.model.Tweet;
import Project.app.controller.TweetController;


// useful site : https://twitter4j.org/en/code-examples.html



@SpringBootApplication
public class TwitterBotApp {

	public static void main(String[] args) {
		SpringApplication.run(TwitterBotApp.class, args);
		Twitter twitter ;
		try{
			twitter = new TwitterFactory().getInstance();

			Trends trends = twitter.getPlaceTrends(736634);
			for (int i = 0; i < trends.getTrends().length; i++) {
				System.out.println(trends.getTrends()[i].getName());
			}
		}catch(TwitterException e){
			System.out.println(e);
		}

	}

}
