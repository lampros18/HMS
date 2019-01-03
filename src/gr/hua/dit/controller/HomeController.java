package gr.hua.dit.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String showHomePage(Principal httpSe) {		
		
		String principalInfo=httpSe.toString();
		
		if(principalInfo.contains("ROLE_ADMIN")) {
			return "/admin/adminHome";
		}else if(principalInfo.contains("ROLE_EMPLOYEE")) {
			
			return "/employee/employeeHome";
		}
		else if(principalInfo.contains("ROLE_STUDENT")) {
			
			return "/student/studentHome";
		}
		
		
		//Άν δεν υπάρχει κάποιος ρόλος
		System.err.println("Μη υποστηριζόμενος ρόλος σφάλμα στην "+this.getClass());
		
		return null;
	}
	
	@GetMapping("/commonLogin")
	public String loginPage(Model model) {
		
		return "bootstrap-login";
	}
}
