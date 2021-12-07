package Project.app;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.*;


@Entity
@Table(name = "User")
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UsernameID", nullable = false)
	private Long usernameID;


    @Column(name = "Username", nullable = false)
	private String username;

	@Column(name = "Password", nullable = false)
	private String password;

	@Column(name = "Likes", nullable = true)
	private List<String> likes;


	//@OneToMany(targetEntity = Quote.class, fetch= FetchType.EAGER, mappedBy = "movie", cascade = CascadeType.ALL)
	//private List<Quote> quotes;

	public User() {
	}

	public User(String username, String password) {
        this.username=username;
		this.password = password;
	}
	

	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}

	public void addLikes(String like) {
        this.likes.add(like);
	}

	@Override
    public String toString() {
        return "User [id=" + usernameID + ", Username=" + username
       + "]";
    }
}
