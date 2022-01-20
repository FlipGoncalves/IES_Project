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
			if (user_rep.findByUsername(us.getUsername()).getPassword().equals(us.getPassword())) {

				// get user data from db
				User user = user_rep.findByUsername(us.getUsername());
				model.addAttribute("User", user);
				List<String> interests = user.getInterests();

				// get tweets from db
				ArrayList<Tweet> array = new ArrayList<Tweet>();
				List<Tweet> tweets = service.getAllTweets();
				List<Tweet> out = new ArrayList<Tweet>();
				int count = 0;
				for(int i = tweets.size() - 1; i > 0; i--) {
					if (count == 10)
						break;
					Set<String> trends_tweet = tweets.get(i).getTrends();
					for (String trend: trends_tweet) {
						if (interests.contains(trend)) {
							out.add(tweets.get(i));
							count += 1;
						}
					}
				}
				array.addAll(out);
				Map<String, ArrayList<Tweet>> mp = new HashMap<>();
				mp.put("Tweet", array);
				model.addAllAttributes(mp);

				// get data from db -> {title1: {name1: data1, name2: data2, name3: data3}, {title2: {name1: data1, name2: data2}}}
				Map<String, Map<String, Integer>> data = new HashMap<>();
				Map<String, Integer> graphData = new TreeMap<>();
				List<String> titles = new ArrayList<String>();

				// for all trends
				Map<String, Integer> graphData1 = new TreeMap<>();
				count = 10;
				for(int i = 0; i < 6; i++) {
					graphData1.put("Interest_" + i, count);
					count += 100;
				}
				data.put("Twitter COunt for all Interests", graphData1);

				// for user interests
				count = 0;
				for (String trend: interests) {
					// get count for trend
					graphData.put(trend, count);
					count += 100;
				}
				data.put("Twitter Count for each Interest", graphData);

				System.out.println(data);
				List<Map<String, Integer>> sendData = new ArrayList<Map<String,Integer>>();
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
		// trends.size()
		int count = 10;
		for(int i = 0; i < count; i++) {
			array.add("Interest_" + i);
		}
		Map<String, ArrayList<String>> mp = new HashMap<>();
		mp.put("interests_data", array);
		model.addAllAttributes(mp);

		return "register";
	}

	@PostMapping("/register")
	public String registerSubmit(@ModelAttribute User us, Model model) {
		System.out.println(us);

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
