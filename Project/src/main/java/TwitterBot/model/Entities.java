package TwitterBot.model;

import com.google.gson.annotations.SerializedName;

// Entities is also a part of search/recent json
// Contains details about text that has a special meaning in a Tweet.
// To return this field, add tweet.fields=entities in the request's query parameter
public class Entities {
  Description description;
  Url url;
  
  @SerializedName("description")
  public Description getDescription() {
    return this.description;
  }
  
  public void setDescription( Description description ) {
    this.description = description;
  }
  
  @SerializedName("url")
  public Url getUrl() {
    return this.url;
  }
  
  public void setUrl( Url url ) {
    this.url = url;
  }
}
