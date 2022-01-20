package Project.TwitterBot.model.SearchTweets;
import com.google.gson.annotations.SerializedName;

// meta information about search/recent query
public class Meta {
  String newest_id;
  String oldest_id;
  int result_count;

  @SerializedName("newest_id")
  public String getNewest_id() {
    return this.newest_id;
  }

  public void setNewest_id( String newest_id ) {
    this.newest_id = newest_id;
  }

  @SerializedName("oldest_id")
  public String getOldest_id() {
    return this.oldest_id;
  }

  public void setOldest_id( String oldest_id ) {
    this.oldest_id = oldest_id;
  }

  @SerializedName("result_count")
  public int getResult_count() {
    return this.result_count;
  }

  public void setResult_count( int result_count ) {
    this.result_count = result_count;
  }
}
