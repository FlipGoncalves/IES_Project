package TwitterBot.model;

import com.google.gson.annotations.SerializedName;

public class Hashtag{
    @SerializedName("end")
    public int getEnd() { 
		 return this.end; } 
    public void setEnd(int end) { 
		 this.end = end; } 
    int end;
    @SerializedName("start")
    public int getStart() { 
		 return this.start; } 
    public void setStart(int start) { 
		 this.start = start; } 
    int start;
    @SerializedName("tag")
    public String getTag() { 
		 return this.tag; } 
    public void setTag(String tag) { 
		 this.tag = tag; } 
    String tag;
}
