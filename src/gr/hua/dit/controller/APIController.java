package gr.hua.dit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.dit.entity.User;
import gr.hua.dit.service.UserService;




@RestController
@RequestMapping("api")
public class APIController {
	
	
	@Autowired
	private UserService userService;
	
	@PostMapping("authenticate")
	@ResponseBody
	public String Authenticate(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = userService.findUserByUsername(username);
		
		
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(password, user.getPassword()) ? "true" : "false";
	}
}
