package gr.hua.dit.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import gr.hua.dit.entity.Authorities;
import gr.hua.dit.entity.User;
import gr.hua.dit.security.Crypto;
import gr.hua.dit.service.UserService;

@SessionAttributes({"username", "authorities"})
@Controller
public class GeneralControlller {

	@Autowired
	private Crypto crypto;
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("authorities")
	public List<Authorities> getAuthorities(Principal principal) {
		User user = userService.findUserByUsername(principal.getName());
		return user.getAuthorities();
	}
	
	@ModelAttribute("username")
	public String getUsername(Principal principal) {
		if (crypto.isEncrypted(principal.getName()))
			return crypto.decrypt(principal.getName());
		else
			return principal.getName();
	}
	
	@GetMapping("/about")
	public String aboutPage(Model model) {
		return "about";
	}
	
	@GetMapping("/contact")
	public String contactPage(Model model) {
		return "contact";
	}
	
}
