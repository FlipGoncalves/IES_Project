package TwitterBot.model.TrendTweet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Trends")
@Entity
public class TweetTrendsJson {

  @Id
  String id;

  @SerializedName("name")
  @Expose
  @Field("name")
  String name;

  @SerializedName("promoted_content")
  @Expose
  @Field("promoted_content")
  String promoted_content;

  @SerializedName("query")
  @Field("query")
  @Expose
  String query;

  @Field("tweet_volume")
  @SerializedName("tweet_volume")
  @Expose
  Long tweet_volume;

  @Field("url")
  @SerializedName("url")
  @Expose
  String url;

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
