package TwitterBot.model; 
import com.google.gson.annotations.SerializedName;

public class Meta{
    @SerializedName("newest_id")
    public String getNewest_id() { 
		 return this.newest_id; } 
    public void setNewest_id(String newest_id) { 
		 this.newest_id = newest_id; } 
    String newest_id;
    @SerializedName("oldest_id")
    public String getOldest_id() { 
		 return this.oldest_id; } 
    public void setOldest_id(String oldest_id) { 
		 this.oldest_id = oldest_id; } 
    String oldest_id;
    @SerializedName("result_count")
    public int getResult_count() { 
		 return this.result_count; } 
    public void setResult_count(int result_count) { 
		 this.result_count = result_count; } 
    int result_count;
}
