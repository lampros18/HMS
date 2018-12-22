package gr.hua.dit.springmvc1.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gr.hua.dit.entity.Student;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@PostMapping(value="/createStudent")
	public String doCreate(Model model,@RequestParam("email") String email ,
			@RequestParam("name") String name) throws UnsupportedEncodingException {
		
			model.addAttribute("name", name);
			model.addAttribute("email",email);
			
			String str=URLEncoder.encode(email, "UTF-8");
			str = URLDecoder.decode(email, "UTF-8"); 
			
			System.out.println(str);
//			System.out.println(name);
		
			
			
			return "studentActions";
		}
}


