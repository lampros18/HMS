package gr.hua.dit.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("admin_login")
	public String adminLogin() {
		return "adminLogin";
	}
	
	@GetMapping("employee_login")
	public String employeeLogin() {
		return "employeeLogin";
	}
	
	@GetMapping("student_login")
	public String studentLogin() {
		return "studentLogin";
	}
	
	@GetMapping("admin_logout")
	public String Logout() {
		return "logout";
	}
}
