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

				// get data from db
				model.addAttribute("User", user_rep.findByUsername(us.getUsername()));

				// get tweets from db
				ArrayList<Tweet> array = new ArrayList<Tweet>();
				// change to the right get
				List<Tweet> tweets = service.getAllTweets();
				array.addAll(tweets);
				Map<String, ArrayList<Tweet>> mp = new HashMap<>();
				mp.put("Tweet", array);
				model.addAllAttributes(mp);

				mp.clear();
				// get data from db
				// change to the right data
				List<Map<String, Integer>> data = new ArrayList<Map<String,Integer>>();
				Map<String, List<Map<String, Integer>>> map = new HashMap<>();
				Map<String, Integer> graphData = new TreeMap<>();
				graphData.put("2016", 147);
				graphData.put("2017", 1256);
				graphData.put("2018", 3856);
				graphData.put("2019", 19807);
				data.add(graphData);

				Map<String, Integer> graph = new TreeMap<>();
				graph.put("2020", 3);
				graph.put("2021", 2);
				graph.put("2022", 4);
				graph.put("2023", 1);
				data.add(graph);

				Map<String, Integer> graph1 = new TreeMap<>();
				graph1.put("2020", 3);
				graph1.put("2021", 2);
				graph1.put("2022", 4);
				graph1.put("2023", 1);
				data.add(graph1);

				map.put("charData", data);
				System.out.println(map);
				//model.addAttribute("chartData", graphData);
				model.addAllAttributes(map);

				return "home";
			}
		}
		return "index";
	}

	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("register", new User());
		// envia user para a base de dados, ou seja envia para o rest controller
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

		return "home";	
	}

	@GetMapping("/home")
	public String home(Model model) {
		return "home";
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/account")
	public String account(@ModelAttribute User us, Model model) {
		model.addAttribute("Edit", us);
		return "account";
	}

	@PostMapping("/account")
	public String accountForm(@ModelAttribute User us, Model model) {
		model.addAttribute("User", us);
		return "home";
	}
}