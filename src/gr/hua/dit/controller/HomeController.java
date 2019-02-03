package gr.hua.dit.controller;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import gr.hua.dit.entity.User;
import gr.hua.dit.request.EmployeeRequestHandler;
import gr.hua.dit.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String showHomePage(Principal principal) {

		String principalInfo = principal.toString();

		if (principalInfo.contains("ROLE_ADMIN")) {
			return "redirect:/admin/editUsers";
		} else if (principalInfo.contains("ROLE_EMPLOYEE")) {

			return "redirect:/employee/employeeHome";
		} else if (principalInfo.contains("ROLE_STUDENT")) {

			return "redirect:/student/studentHome";
		} else if (principalInfo.contains("ROLE_FOREMAN")) {
			return "redirect:/foreman/home";
		}

		// Άν δεν υπάρχει κάποιος ρόλος
		System.err.println("Μη υποστηριζόμενος ρόλος σφάλμα στην " + this.getClass());

		return null;
	}

	@GetMapping("/login")
	public String loginPage(Model model) {

		return "bootstrap-login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getAllStudents(HttpServletRequest request) {

		EmployeeRequestHandler employeeRequestHandler = new EmployeeRequestHandler();

		String username = employeeRequestHandler.getSringifiedHttpResponse(request);


		JSONObject jsonObject = new JSONObject(username);

		User user = userService.findUserByUsername(jsonObject.getString("user"));

		JSONObject response = new JSONObject();

		if (user != null) {

			response.put("answer", user.getUsername());
		}

		return response.toString();
	}

}
