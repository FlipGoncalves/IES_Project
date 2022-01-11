package Project.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import Project.app.Models.*;
import Project.app.Repositories.*;

import java.util.*;

@Controller
public class ViewController {

	@Autowired
	private UserRepository user_rep;

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
				System.out.println(us.getPassword());
				model.addAttribute("User", user_rep.findByUsername(us.getUsername()));
				Tweet t = new Tweet();
				t.setDescription("tweet1");
				Tweet t1 = new Tweet();
				t1.setDescription("tweet2");
				ArrayList<Tweet> array = new ArrayList<Tweet>();
				array.add(t);
				array.add(t1);
				Map<String, ArrayList<Tweet>> mp = new HashMap<>();
				mp.put("Tweet", array);
				model.addAllAttributes(mp);
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