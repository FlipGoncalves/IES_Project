package Project.app.Models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Tweet")
public class Tweet implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Field("Id")
	@Id
	private Integer id;
    @Field("description")
    private String description;
    @Field("trends")
    private Set<String> trends;
    @Field("person")
    private String person;

	public Tweet(Integer id, String description, Set<String> trends, String person) {
        this.description = description;
        this.trends = trends;
        this.person = person;
	}

	public Tweet() {
	}

	// @OneToMany(targetEntity = Quote.class,fetch = FetchType.EAGER, mappedBy = "movie", cascade = CascadeType.ALL)
	// public Set<Quote> getQuotes() {
	// 	return quotes;
	// }

	// public void setQuotes(Set<Quote> quotes) {
	// 	this.quotes = quotes;
	// }

    @GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	public Integer getId() {
		return id;
	}

    @GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	public void setId(Integer id) {
		this.id = id;
	}
    
    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

	public Set<String> getTrends() {
        return trends;
    }

    public void setTrends(Set<String> trends) {
        this.trends = trends;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + description + ", trends=" + trends + "]";
	}

	// public void addQuote(Quote qt) {
	// 	if (qt != null)
	// 		quotes.add(qt);
	// }

}