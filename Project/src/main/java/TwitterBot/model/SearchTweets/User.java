package TwitterBot.model.SearchTweets;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User{
    @SerializedName("created_at")
    public Date getCreated_at() {
		 return this.created_at; } 
    public void setCreated_at(Date created_at) { 
		 this.created_at = created_at; } 
    Date created_at;
    @SerializedName("entities")
    public Entities getEntities() {
		 return this.entities; } 
    public void setEntities(Entities entities) { 
		 this.entities = entities; } 
    Entities entities;
    @SerializedName("id")
    public String getId() { 
		 return this.id; } 
    public void setId(String id) { 
		 this.id = id; } 
    String id;
    @SerializedName("name")
    public String getName() { 
		 return this.name; } 
    public void setName(String name) { 
		 this.name = name; } 
    String name;
    @SerializedName("username")
    public String getUsername() { 
		 return this.username; } 
    public void setUsername(String username) { 
		 this.username = username; } 
    String username;
}
