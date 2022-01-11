package Project.app.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.*;

@Entity
@Document(collection = "User")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Field("id")
	private Integer id;

    @Field("username")
	private String username;

	@Field("password")
	private String password;

	@Field("interests")
	private List<String> interests;

	private String morada;
	private String data_nascimento;


	//@OneToMany(targetEntity = Quote.class, fetch= FetchType.EAGER, mappedBy = "movie", cascade = CascadeType.ALL)
	//private List<Quote> quotes;

	public User() {
	}

	public User(String username, String password) {
        this.username=username;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}

	public String getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	@Override
    public String toString() {
        return "User [id=" + id + ", Username=" + username + ", Password=" + password + ", Interests=" + interests + "]";
    }
}
