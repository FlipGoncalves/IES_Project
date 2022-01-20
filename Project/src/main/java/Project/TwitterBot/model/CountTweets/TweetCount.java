package Project.TwitterBot.model.CountTweets;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.bson.internal.Base64;

public class TweetCount {
  @Expose
  @SerializedName("end")
  String end;
  @Expose
  @SerializedName("start")
  String start;
  @Expose
  @SerializedName("tweet_count")
  Long tweet_count;
  
  @Expose( serialize = true, deserialize = false)
  String query;
  
  public String getQuery() {
    return new String( Base64.decode(query));
  }
  
  public void setQuery( String query ) {
    this.query = query;
  }
  
  public String getEnd() {
    return end;
  }

  public void setEnd( String end ) {
    this.end = end;
  }

  public String getStart() {
    return start;
  }

  public void setStart( String start ) {
    this.start = start;
  }

  public Long getTweet_count() {
    return tweet_count;
  }

  public void setTweet_count( Long tweet_count ) {
    this.tweet_count = tweet_count;
  }

  @Override public String toString() {
    return "{" +
      "\"end\":\"" + end + '\"' +
      ",\"start\":\"" + start + '\"' +
      ",\"tweet_count\":\"" + tweet_count + "\"}";
  }
}
