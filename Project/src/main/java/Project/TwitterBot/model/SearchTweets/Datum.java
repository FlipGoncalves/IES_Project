package Project.TwitterBot.model.SearchTweets;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

// data which in included inside the Twitter api json response for endpoint search/recent
public class Datum {
  String author_id;
  String conversation_id;
  Date created_at;
  String id;
  String lang;
  String text;

  @SerializedName("author_id")
  public String getAuthor_id() {
    return this.author_id;
  }

  public void setAuthor_id( String author_id ) {
    this.author_id = author_id;
  }

  @SerializedName("conversation_id")
  public String getConversation_id() {
    return this.conversation_id;
  }

  public void setConversation_id( String conversation_id ) {
    this.conversation_id = conversation_id;
  }

  @SerializedName("created_at")
  public Date getCreated_at() {
    return this.created_at;
  }

  public void setCreated_at( Date created_at ) {
    this.created_at = created_at;
  }

  @SerializedName("id")
  public String getId() {
    return this.id;
  }

  public void setId( String id ) {
    this.id = id;
  }

  @SerializedName("lang")
  public String getLang() {
    return this.lang;
  }

  public void setLang( String lang ) {
    this.lang = lang;
  }

  @SerializedName("text")
  public String getText() {
    return this.text;
  }

  public void setText( String text ) {
    this.text = text;
  }

  @Override public String toString() {
    return "{" +
      "\"author_id\":\"" + author_id + '\"' +
      ",\"conversation_id\":\"" + conversation_id + '\"' +
      ",\"created_at\":\"" + created_at +
      ",\"id\":\"" + id + '\"' +
      ",\"lang\":\"" + lang + '\"' +
      ",\"text\":\"" + text + '\"' +
      '}';
  }
}
