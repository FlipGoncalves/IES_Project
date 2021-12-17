package TwitterBot.model;


import lombok.Data;
import lombok.*;
import java.io.Serializable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;
import java.lang.Math;


public class Tweet implements Serializable{
    private String tag;

    private String ts;

    private String description;
    

    @Id
    private long id;
    //private ArrayList<Quote> quotes = new ArrayList<>();

    public Tweet(String tag,String ts, String description){
        this.tag=tag;
        this.ts=ts;
        this.description=description;
    }

    /* public void add(Quote q){
        quotes.add(q);
    }

    */


    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id=id;
    }


    public String getTag(){
        return tag;
    }
    public void setTag(String tag){
        this.tag=tag;
    }


    public String getTs(){
        return ts;
    }
    public void setTs(String ts){
        this.ts=ts;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }

    @Override
    public String toString(){
        return "{ tag: " + tag + ", ts : " + ts + ", description : " + description + ", id : " + id +  " }"; 
    }
    
}