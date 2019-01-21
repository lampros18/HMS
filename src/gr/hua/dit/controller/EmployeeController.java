package gr.hua.dit.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.hua.dit.entity.Student;
import gr.hua.dit.entity.User;
import gr.hua.dit.request.EmployeeRequestHandler;
import gr.hua.dit.service.StudentService;
import gr.hua.dit.service.UserService;

@Controller
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "employeeHome", method = RequestMethod.GET)
	public String showHomePage() {
		return "employee/employeeHome";
	}

	@RequestMapping(value = "createStudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String createEmployee(HttpServletRequest request, Principal principal) {

		EmployeeRequestHandler employeeRequestHandler = new EmployeeRequestHandler();

		JSONObject jsonObject = new JSONObject(employeeRequestHandler.getSringifiedHttpResponse(request));
		jsonObject.put("createdBy", principal.getName());
//		String ecryptedPassword=BCrypt.hashpw("pass", BCrypt.gensalt());

		if (Student.validStudent(jsonObject)) {
			User user = userService.findUserByUsername(jsonObject.getString("email"));
			Student student = new Student(jsonObject);
			student.setUser(user);
			System.out.println(studentService.insertStudent(student));
		}

		return "{}";

	}
}
