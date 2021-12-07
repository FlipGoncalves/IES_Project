package Project.app.Models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection = "tweet")
public class Tweet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    private String description;
	// @OneToMany(targetEntity = Quote.class,fetch = FetchType.EAGER, mappedBy = "movie", cascade = CascadeType.ALL)
	// private Set<Quote> quotes;
    private Set<String> trends;
    private String person;

	public Tweet(Integer id, String description, Set<String> trends, String person) {
		this.id = id;
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

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
    @Column(name = "person", nullable = false)
    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    @Column(name = "trends", nullable = false)
	public Set<String> getTrends() {
        return trends;
    }

    public void setTrends(Set<String> trends) {
        this.trends = trends;
    }

    @Column(name = "description", nullable = false)
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