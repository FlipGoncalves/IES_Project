package Project.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import Project.app.Models.*;
import Project.app.Repositories.*;
import Project.app.Service.*;

import java.util.*;

@Controller
public class ViewController {

	@Autowired
	private UserRepository user_rep;
	@Autowired
    private ApiService service;

	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("login", new User());
		return "login";
	}

	@PostMapping("/login")
	public String loginSubmit(@ModelAttribute User us, Model model) {
		// verifica se existe user com username e password iguais
		System.out.println(us);
		if (user_rep.findByUsername(us.getUsername()) != null) {
			System.out.println(user_rep.findByUsername(us.getUsername()));
			// hashed password
			String pass = us.getPassword();
			int hash = 7;
			for (int i = 0; i < pass.length(); i++) {
				hash = hash*31 + pass.charAt(i);
			}
			System.out.println(hash);

			if (user_rep.findByUsername(us.getUsername()).getPassword().equals(Integer.toString(hash))) {

				// get user data from db
				User user = user_rep.findByUsername(us.getUsername());

				model.addAttribute("User", user);
				List<String> interests = user.getInterests();

				// get tweets from db
				ArrayList<Datum> array = new ArrayList<Datum>();
				List<Datum> tweets = service.getAllTweets();
				List<Datum> out = new ArrayList<Datum>();
				// int count = 0;
				for(int i = tweets.size() - 1; i > 0; i--) {
					// if (count == 10)
					// 	break;
					String trend = tweets.get(i).getQuery();
					System.out.println(tweets.get(i));
					if (interests.contains(trend)) {
						out.add(tweets.get(i));
						// count += 1;
					}
					out.add(tweets.get(i));
				}
				array.addAll(out);
				Map<String, ArrayList<Datum>> mp = new HashMap<>();
				mp.put("Tweet", array);
				model.addAllAttributes(mp);

				// get data from db -> {title1: {name1: data1, name2: data2, name3: data3}, {title2: {name1: data1, name2: data2}}}
				Map<String, Map<String, Long>> data = new HashMap<>();
				Map<String, Long> graphData = new TreeMap<>();
				List<String> titles = new ArrayList<String>();

				// for all trends
				List<TweetCount> graph = service.getAllTweetCount();
				Map<String, Long> graphData1 = new TreeMap<>();
				for(int i = 0; i < graph.size() && i < 6; i++) {
					graphData1.put(graph.get(i).getQuery(), graph.get(i).getTweet_count());
				}
				data.put("Twitter Count for all Interests", graphData1);

				// for user interests
				long count2 = 0;
				for (String trend: interests) {
					// get count for trend
					graphData.put(trend, count2);
					count2 += 100;
				}
				data.put("Twitter Count for each Interest", graphData);

				System.out.println(data);
				List<Map<String, Long>> sendData = new ArrayList<Map<String,Long>>();
				for (String title: data.keySet()) {
					System.out.println(data.get(title));
					titles.add(title);
					sendData.add(data.get(title));
				}
				model.addAttribute("charData", sendData);
				model.addAttribute("titles", titles);

				return "home";
			}
		}
		return "index";
	}

	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("register", new User());
		// envia user para a base de dados, ou seja envia para o rest controller

		// get trends from database
		ArrayList<String> array = new ArrayList<String>();
		List<TweetTrendsJson> trends = service.getAllTweetTrend();

		for(int i = 0; i < trends.size(); i++) {
			array.add(trends.get(i).getName());
		}

		Map<String, ArrayList<String>> mp = new HashMap<>();
		mp.put("interests_data", array);
		model.addAllAttributes(mp);

		return "register";
	}

	@PostMapping("/register")
	public String registerSubmit(@ModelAttribute User us, Model model) {
		System.out.println(us);

		// hashed password
		String pass = us.getPassword();
		int hash = 7;
		for (int i = 0; i < pass.length(); i++) {
			hash = hash*31 + pass.charAt(i);
		}
		System.out.println(hash);
		us.setPassword(Integer.toString(hash));

		if (user_rep.findByUsername(us.getUsername()) != null) {
			System.out.println(user_rep.findByUsername(us.getUsername()));
			return "index";
		}

		model.addAttribute("User", us);
		user_rep.save(us);

		return "index";
	}

	@GetMapping("/home")
	public String home(@ModelAttribute User us, Model model) {
		System.out.println("home");
		return "home";
	}

	@PostMapping("/home")
	public String homeSubmit(@ModelAttribute User us, Model model) {
		System.out.println("home " + us);
		return "home";
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}
}
