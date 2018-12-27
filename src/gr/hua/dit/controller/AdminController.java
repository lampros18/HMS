package gr.hua.dit.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

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
		 employeeRequestHandler.getDataFromRequest(request);
		
		// 2- Wrte the username and the password before the password will be hashed
//		FileManager fm = new FileManager();
//		fm.writeCredentialsFile(data.get("email"), data.get("password"));
		
		// 3- Encode the raw string to a hashed one
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//		data.put("password", bCryptPasswordEncoder.encode(data.get("password")));
		
		// 4 - Persist to database
//		Employee employee = new Employee(data);
//		employee.addAuthority(new EmployeeAuthority(data.get("email"), data.get("authority")));
//		employeeService.createEmployee(employee);
//		return employee.toString();
		return null;
	}

	
}
