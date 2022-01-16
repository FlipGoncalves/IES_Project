package Project.TwitterBot.model.SearchTweets;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// json response from twitter api
// this is the root class of the json which is returned

public class TweetSearchResponse {
  List<Datum> data;
  Includes includes;
  Meta meta;

  @SerializedName("data")
  public List<Datum> getData() {
    return this.data;
  }

  public void setData( List<Datum> data ) {
    this.data = data;
  }

  @SerializedName("includes")
  public Includes getIncludes() {
    return this.includes;
  }

  public void setIncludes( Includes includes ) {
    this.includes = includes;
  }

  @SerializedName("meta")
  public Meta getMeta() {
    return this.meta;
  }

  public void setMeta( Meta meta ) {
    this.meta = meta;
  }
}
