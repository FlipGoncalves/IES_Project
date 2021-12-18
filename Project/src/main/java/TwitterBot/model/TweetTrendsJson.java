package TwitterBot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TweetTrendsJson {
  @SerializedName("name")
  @Expose
  String name;
  @SerializedName("promoted_content")
  @Expose
  String promoted_content;
  @SerializedName("query")
  @Expose
  String query;
  @SerializedName("tweet_volume")
  @Expose
  Long tweet_volume;
  @SerializedName("url")
  @Expose
  String url;
  
  public TweetTrendsJson( String name, String promoted_content, String query, Long tweet_volume, String url ) {
    this.name = name;
    this.promoted_content = promoted_content;
    this.query = query;
    this.tweet_volume = tweet_volume;
    this.url = url;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName( String name ) {
    this.name = name;
  }
  
  public String getPromoted_content() {
    return promoted_content;
  }
  
  public void setPromoted_content( String promoted_content ) {
    this.promoted_content = promoted_content;
  }
  
  public String getQuery() {
    return query;
  }
  
  public void setQuery( String query ) {
    this.query = query;
  }
  
  public Long getTweet_volume() {
    return tweet_volume;
  }
  
  public void setTweet_volume( Long tweet_volume ) {
    this.tweet_volume = tweet_volume;
  }
  
  public String getUrl() {
    return url;
  }
  
  public void setUrl( String url ) {
    this.url = url;
  }
  
  @Override public String toString() {
    return "Tweet{" +
      "name='" + name + '\'' +
      ", promoted_content='" + promoted_content + '\'' +
      ", query='" + query + '\'' +
      ", tweet_volume=" + tweet_volume +
      ", url='" + url + '\'' +
      '}';
  }
}
