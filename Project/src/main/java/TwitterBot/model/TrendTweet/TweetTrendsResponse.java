package TwitterBot.model.TrendTweet;
import TwitterBot.model.SearchTweets.Locations;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TweetTrendsResponse {
  @Expose
  @SerializedName("as_of")
  String as_of;
  @Expose
  @SerializedName("created_at")
  String created_at;
  @Expose
  @SerializedName("locations")
  List<Locations> locations;
  @Expose
  @SerializedName("trends")
  List<TweetTrendsJson> trends;


  public String getAs_of() {
    return as_of;
  }

  public void setAs_of( String as_of ) {
    this.as_of = as_of;
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at( String created_at ) {
    this.created_at = created_at;
  }

  public List<Locations> getLocations() {
    return locations;
  }

  public void setLocations( List<Locations> locations ) {
    this.locations = locations;
  }

  public List<TweetTrendsJson> getTrends() {
    return trends;
  }

  public void setTrends( List<TweetTrendsJson> trends ) {
    this.trends = trends;
  }
}
