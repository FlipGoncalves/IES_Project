package TwitterBot.model;

import javax.persistence.Id;
import java.io.Serializable;


public class Tweet implements Serializable {
  private String tag;

  private String ts;

  private String description;


  @Id
  private long id;
<<<<<<< HEAD
  //private ArrayList<Quote> quotes = new ArrayList<>();

=======
  
>>>>>>> TwitterBot
  public Tweet( String tag, String ts, String description ) {
    this.tag = tag;
    this.ts = ts;
    this.description = description;
  }


<<<<<<< HEAD
    */


=======
  
>>>>>>> TwitterBot
  public long getId() {
    return id;
  }

  public void setId( long id ) {
    this.id = id;
  }


  public String getTag() {
    return tag;
  }

  public void setTag( String tag ) {
    this.tag = tag;
  }


  public String getTs() {
    return ts;
  }

  public void setTs( String ts ) {
    this.ts = ts;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription( String description ) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "{ tag: " + tag + ", ts : " + ts + ", description : " + description + ", id : " + id + " }";
  }
<<<<<<< HEAD

}
=======
  

}
>>>>>>> TwitterBot
