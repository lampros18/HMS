package gr.hua.dit.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.xdevapi.JsonArray;

import gr.hua.dit.entity.Authorities;
import gr.hua.dit.entity.Student;
import gr.hua.dit.entity.User;
import gr.hua.dit.request.EmployeeRequestHandler;
import gr.hua.dit.security.Crypto;
import gr.hua.dit.service.StudentService;
import gr.hua.dit.service.UserService;

@Controller
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private UserService userService;

	@Autowired
	private Crypto crypto;

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

			Student student2 = new Student(jsonObject);

//			if(user!=null) {
//				
//				System.out.println("\n\n");
//				System.out.println(user);
//				System.out.println("\n\n");
//			}else {
			// Δημιουργία User

			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			user = new User(jsonObject.getString("email"), bCryptPasswordEncoder.encode("pass"), "1");

			Authorities authorities = new Authorities("ROLE_STUDENT");

			user.addAuthority(authorities);

//				
//				userService.createUser(user);
//				

			Student student = new Student(jsonObject);
//				
//				
//				
//				
			student.setUser(user);
//				
			studentService.insertStudent(student);
//				
//				
//			}

//			student.setUser(user);
//			System.out.println(student.getUser().getUsername());
//			System.out.println(studentService.insertStudent(student));
			
			JSONObject response=new JSONObject();
			response.put("result", 200);
			
			response.put("username", crypto.decrypt(student.getUser().getUsername()));

			return response.toString();
		}
		
		return "{result:407}";

	}

	@RequestMapping(value = "getAllStudents", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getAllStudents(HttpServletRequest request) {

		List<Student> students = studentService.getStudents();

		JSONArray jsonv = new JSONArray();

		for (Student student : students) {

			jsonv.put(student.getJsonFromStudent());
		}

		if (jsonv.length() > 0) {
			return jsonv.toString();
		}

//			Student student = new Student(jsonObject);
//			student.setUser(user);
//			System.out.println(student.getUser().getUsername());
//			System.out.println(studentService.insertStudent(student));

		return "{}";
	}

	@RequestMapping(value = "updateStudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateStudent(HttpServletRequest request) {

		try {

			EmployeeRequestHandler employeeRequestHandler = new EmployeeRequestHandler();

			JSONObject jsonObject = new JSONObject(employeeRequestHandler.getSringifiedHttpResponse(request));


			Student student = studentService.findStudentByUsername(jsonObject.getString("email"));

			student.setPhone(crypto.encrypt(jsonObject.getString("phone")));

			student.setAddress(crypto.encrypt(jsonObject.getString("address")));

			studentService.insertStudent(student);
			
			JSONObject response=new JSONObject();
			
			response.put("result", 200);
			
			response.put("email", crypto.decrypt(student.getUser().getUsername()));
			
			return response.toString();

		} catch (Exception exp) {

			return "{result:407}";
		}

	}

}
