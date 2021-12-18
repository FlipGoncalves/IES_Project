package TwitterBot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Locations {
  @Expose
  @SerializedName( "name" )
  String name;
  
  @Expose
  @SerializedName( "woeid" )
  String woeid;
  
  public String getName() {
    return name;
  }
  
  public void setName( String name ) {
    this.name = name;
  }
  
  public String getWoeid() {
    return woeid;
  }
  
  public void setWoeid( String woeid ) {
    this.woeid = woeid;
  }
}
