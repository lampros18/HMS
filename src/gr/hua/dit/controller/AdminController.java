package gr.hua.dit.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.hua.dit.entity.Authorities;
import gr.hua.dit.entity.Employee;
import gr.hua.dit.entity.User;
import gr.hua.dit.fileManager.FileManager;
import gr.hua.dit.request.EmployeeRequestHandler;
import gr.hua.dit.service.EmployeeService;
import gr.hua.dit.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {

	
	
	@Autowired
	private UserService userService;
	
	//Κεντική σελίδα διαχειριστή
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String showHomePage() {
		return "adminHome";
	}

	//Δημιουργία υπαλλήλου στην εφαρμογή 
	@RequestMapping(value = "createEmployee", method = RequestMethod.GET)
	public String getCreateStudentForm() {
		return "admin/createEmployee";
	}

	@RequestMapping(value = "createEmployee", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String createEmployee(HttpServletRequest request) {
		// 1- Extract all the necessary data from the request and return a hash map
		EmployeeRequestHandler employeeRequestHandler = new EmployeeRequestHandler();
		String data = employeeRequestHandler.getSringifiedHttpResponse(request);
		 JSONObject json = new JSONObject(data);
		 Principal principal = request.getUserPrincipal();
		 json.put("createdBy", principal.getName());
		 
		 if(json != null) {
				// 2- Write the username and the password before the password will be hashed
				FileManager fm = new FileManager();
				fm.writeCredentialsFile(json.getString("email"), json.getString("password"));
				
				// 3- Encode the raw string to a hashed one
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				json.put("password", bCryptPasswordEncoder.encode(json.getString("password")));
				
				// 4 - Persist to database
//				Employee employee = new Employee(json);
//				if(json.getString("authority_admin").equals("1")) {
//					employee.addAuthority(new EmployeeAuthority(json.getString("email"), "ROLE_ADMIN"));
//				}
//				if(json.getString("authority_foreman").equals("1")) {
//					employee.addAuthority(new EmployeeAuthority(json.getString("email"), "ROLE_FOREMAN"));
//				}
//				if(json.getString("authority_employee").equals("1")) {
//					employee.addAuthority(new EmployeeAuthority(json.getString("email"), "ROLE_EMPLOYEE"));
//				}
				
//				employeeService.createEmployee(employee);
				User user = new User(json);
				if(json.getString("authority_admin").equals("1")) {
					user.addAuthority(new Authorities("ROLE_ADMIN"));
				}
				if(json.getString("authority_foreman").equals("1")) {
					user.addAuthority(new Authorities("ROLE_FOREMAN"));
				}
				if(json.getString("authority_employee").equals("1")) {
					user.addAuthority(new Authorities("ROLE_EMPLOYEE"));
				}
				userService.createUser(user);
				return user.toString();
		 }
		 return null;
		
	
	}
	
}
