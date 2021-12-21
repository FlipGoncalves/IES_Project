package TwitterBot.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// Include is also a part of search/recent
public class Includes {
  List<User> users;
  
  @SerializedName("users")
  public List<User> getUsers() {
    return this.users;
  }
  
  public void setUsers( List<User> users ) {
    this.users = users;
  }
}
