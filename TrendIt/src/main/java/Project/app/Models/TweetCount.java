package Project.app.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "tweetcount")
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
 
  @Expose
  @SerializedName( "query" )
  String query;


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
      ",\"tweet_count\":\"" + tweet_count + "\" " +
      ",\"query\":\"" + query + "\" " +
      "}";
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }
}


