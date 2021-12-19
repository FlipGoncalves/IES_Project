package TwitterBot.model;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Datum{
    @SerializedName("author_id")
    public String getAuthor_id() { 
		 return this.author_id; } 
    public void setAuthor_id(String author_id) { 
		 this.author_id = author_id; } 
    String author_id;
    @SerializedName("conversation_id")
    public String getConversation_id() { 
		 return this.conversation_id; } 
    public void setConversation_id(String conversation_id) { 
		 this.conversation_id = conversation_id; } 
    String conversation_id;
    @SerializedName("created_at")
    public Date getCreated_at() { 
		 return this.created_at; } 
    public void setCreated_at(Date created_at) { 
		 this.created_at = created_at; } 
    Date created_at;
    @SerializedName("id")
    public String getId() { 
		 return this.id; } 
    public void setId(String id) { 
		 this.id = id; } 
    String id;
    @SerializedName("lang")
    public String getLang() { 
		 return this.lang; } 
    public void setLang(String lang) { 
		 this.lang = lang; } 
    String lang;
    @SerializedName("text")
    public String getText() { 
		 return this.text; } 
    public void setText(String text) { 
		 this.text = text; } 
    String text;
}
