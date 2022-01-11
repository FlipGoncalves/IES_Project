package TwitterBot.model.SearchTweets;


import com.google.gson.annotations.SerializedName;

// urls which are linked to the tweet
public class Url2 {
  // twitter link
  String display_url;
  int end;
  // same link
  String expanded_url;
  int start;
  // twweet link
  String url;

  @SerializedName("display_url")
  public String getDisplay_url() {
    return this.display_url;
  }

  public void setDisplay_url( String display_url ) {
    this.display_url = display_url;
  }

  @SerializedName("end")
  public int getEnd() {
    return this.end;
  }

  public void setEnd( int end ) {
    this.end = end;
  }

  @SerializedName("expanded_url")
  public String getExpanded_url() {
    return this.expanded_url;
  }

  public void setExpanded_url( String expanded_url ) {
    this.expanded_url = expanded_url;
  }

  @SerializedName("start")
  public int getStart() {
    return this.start;
  }

  public void setStart( int start ) {
    this.start = start;
  }

  @SerializedName("url")
  public String getUrl() {
    return this.url;
  }

  public void setUrl( String url ) {
    this.url = url;
  }
}
