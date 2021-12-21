package TwitterBot.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Url {
  
  List<Url2> urls;
  
  @SerializedName("urls")
  public List<Url2> getUrls() {
    return this.urls;
  }
  
  public void setUrls( List<Url2> urls ) {
    this.urls = urls;
  }
}
