package TwitterBot.model; 
import com.google.gson.annotations.SerializedName;

import java.util.List; 
public class TweetSearchResponse{
    @SerializedName("data")
    public List<Datum> getData() { 
		 return this.data; } 
    public void setData(List<Datum> data) { 
		 this.data = data; } 
    List<Datum> data;
    @SerializedName("includes")
    public Includes getIncludes() { 
		 return this.includes; } 
    public void setIncludes(Includes includes) { 
		 this.includes = includes; } 
    Includes includes;
    @SerializedName("meta")
    public Meta getMeta() { 
		 return this.meta; } 
    public void setMeta(Meta meta) { 
		 this.meta = meta; } 
    Meta meta;
}
