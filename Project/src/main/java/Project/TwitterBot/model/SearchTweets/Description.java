package Project.TwitterBot.model.SearchTweets;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// List of hashtags
public class Description {
  List<Hashtag> hashtags;

  @SerializedName("hashtags")
  public List<Hashtag> getHashtags() {
    return this.hashtags;
  }

  public void setHashtags( List<Hashtag> hashtags ) {
    this.hashtags = hashtags;
  }
}
