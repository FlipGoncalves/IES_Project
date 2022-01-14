package TwitterBot.model.SearchTweets;
import com.google.gson.annotations.SerializedName;

// Hashtags of each tweet returned in search/recent
public class Hashtag {
  int end;
  int start;
  String tag;

  @SerializedName("end")
  public int getEnd() {
    return this.end;
  }

  public void setEnd( int end ) {
    this.end = end;
  }

  @SerializedName("start")
  public int getStart() {
    return this.start;
  }

  public void setStart( int start ) {
    this.start = start;
  }

  @SerializedName("tag")
  public String getTag() {
    return this.tag;
  }

  public void setTag( String tag ) {
    this.tag = tag;
  }
}
