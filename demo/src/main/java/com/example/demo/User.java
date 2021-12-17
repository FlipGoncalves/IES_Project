package com.example.demo;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable{

	private Long usernameID;

	private String username;

	private String password;

	private List<String> interests;


	//@OneToMany(targetEntity = Quote.class, fetch= FetchType.EAGER, mappedBy = "movie", cascade = CascadeType.ALL)
	//private List<Quote> quotes;

	public User() {
	}

	public User(String username, String password) {
        this.username = username;
		this.password = password;
	}

	public Long getUsernameID() {
		return usernameID;
	}

	public void setUsernameID(Long usernameID) {
		this.usernameID = usernameID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

	@Override
    public String toString() {
        return "User [id=" + usernameID + ", Username=" + username + ", Password=" + password + ", Interests=" + interests + "]";
    }
}
