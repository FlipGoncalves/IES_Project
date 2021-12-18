package TwitterBot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalTweetCount {
  @Expose
  @SerializedName("total_tweet_count")
  Long totalTweetCount;
  
  public Long getTotalTweetCount() {
    return totalTweetCount;
  }
  
  public void setTotalTweetCount( Long totalTweetCount ) {
    this.totalTweetCount = totalTweetCount;
  }
  
  @Override public String toString() {
    return "TotalTweetCount{" +
      "totalTweetCount=" + totalTweetCount +
      '}';
  }
}

