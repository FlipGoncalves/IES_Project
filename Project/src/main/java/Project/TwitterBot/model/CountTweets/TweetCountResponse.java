package Project.TwitterBot.model.CountTweets;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TweetCountResponse {
  @Expose
  @SerializedName( "data" )
  List<TweetCount> tweetCountList;

  @Expose
  @SerializedName( "meta" )
  TotalTweetCount ttCount;
  public List<TweetCount> getTweetCountList() {
    return tweetCountList;
  }

  public void setTweetCountList( List<TweetCount> tweetCountList ) {
    this.tweetCountList = tweetCountList;
  }

  public TotalTweetCount getTtCount() {
    return ttCount;
  }

  public void setTtCount( TotalTweetCount ttCount ) {
    this.ttCount = ttCount;
  }

  @Override public String toString() {
    return "TweetCountResponse{" +
      "tweetCountList=" + tweetCountList +
      ", ttCount=" + ttCount +
      '}';
  }

}
