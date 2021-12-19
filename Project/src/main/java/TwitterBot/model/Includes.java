package TwitterBot.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
public class Includes{
    @SerializedName("users")
    public List<User> getUsers() { 
		 return this.users; } 
    public void setUsers(List<User> users) { 
		 this.users = users; } 
    List<User> users;
}
