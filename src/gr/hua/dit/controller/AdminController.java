package gr.hua.dit.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.hua.dit.entity.Employee;
import gr.hua.dit.entity.EmployeeAuthority;
import gr.hua.dit.fileManager.FileManager;
import gr.hua.dit.request.EmployeeRequestHandler;
import gr.hua.dit.service.EmployeeService;

@Controller
@RequestMapping("admin")
public class AdminController {

	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String showHomePage() {
		return "adminHome";
	}

	@RequestMapping(value = "createEmployee", method = RequestMethod.GET)
	public String getCreateStudentForm() {
		return "employeeForm";
	}

	@RequestMapping(value = "createEmployee", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String createStudent(HttpServletRequest request) {
		// 1- Extract all the necessary data from the request and return a hash map
		EmployeeRequestHandler employeeRequestHandler = new EmployeeRequestHandler();
		 JSONObject data = employeeRequestHandler.getDataFromRequest(request);
		 if(data != null) {
				// 2- Wrte the username and the password before the password will be hashed
				FileManager fm = new FileManager();
				fm.writeCredentialsFile(data.getString("email"), data.getString("password"));
				
				// 3- Encode the raw string to a hashed one
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				data.put("password", bCryptPasswordEncoder.encode(data.getString("password")));
				
				// 4 - Persist to database
				Employee employee = new Employee(data);
				if(data.getString("authority_admin").equals("1")) {
					employee.addAuthority(new EmployeeAuthority(data.getString("email"), "ROLE_ADMIN"));
				}
				if(data.getString("authority_foreman").equals("1")) {
					employee.addAuthority(new EmployeeAuthority(data.getString("email"), "ROLE_FOREMAN"));
				}
				if(data.getString("authority_employee").equals("1")) {
					employee.addAuthority(new EmployeeAuthority(data.getString("email"), "ROLE_EMPLOYEE"));
				}
				System.out.println(employee.toString());
				employeeService.createEmployee(employee);
				
				
				return employee.toString();
		 }
		

		return null;
	}

	
}
