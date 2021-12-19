package TwitterBot.model;

import com.google.gson.annotations.SerializedName;

public class Entities{
    @SerializedName("description")
    public Description getDescription() { 
		 return this.description; } 
    public void setDescription(Description description) { 
		 this.description = description; } 
    Description description;
    @SerializedName("url")
    public Url getUrl() { 
		 return this.url; } 
    public void setUrl(Url url) { 
		 this.url = url; } 
    Url url;
}
