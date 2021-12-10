package Project.app.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.*;

@Document(collection = "User")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Field("id")
	private Long usernameID;

    @Field("username")
	private String username;

	@Field("password")
	private String password;

	@Field("interests")
	private List<String> interests;


	//@OneToMany(targetEntity = Quote.class, fetch= FetchType.EAGER, mappedBy = "movie", cascade = CascadeType.ALL)
	//private List<Quote> quotes;

	public User() {
	}

	public User(String username, String password) {
        this.username=username;
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
        return "User [id=" + usernameID + ", Username=" + username + "]";
    }
}
