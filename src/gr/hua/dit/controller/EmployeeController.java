package gr.hua.dit.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.hua.dit.dao.EmployeeDAOImplementation;
import gr.hua.dit.entity.Student;
import gr.hua.dit.request.EmployeeRequestHandler;
import gr.hua.dit.security.Crypto;
@Controller
@RequestMapping("employee")
public class EmployeeController {
	
	@Autowired
	Crypto chiper;
	
	@Autowired
	private EmployeeDAOImplementation daoImplementation;

	@RequestMapping(value="employeeHome", method=RequestMethod.GET)
	public String showHomePage() {
		return "employee/employeeHome";
	}
	
	
	@RequestMapping(value = "employeeHome/createStudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String createEmployee(HttpServletRequest request,Principal principal) {	
		
		
		
		EmployeeRequestHandler employeeRequestHandler = new EmployeeRequestHandler();
			
		JSONObject jsonObject=new JSONObject(employeeRequestHandler.getSringifiedHttpResponse(request));
		
		String ecryptedPassword=BCrypt.hashpw("pass", BCrypt.gensalt());
		
		if(Student.validStudent(jsonObject)) {
			
			 Student student=new Student(jsonObject.getString("email"),-1,
					 jsonObject.getString("name"),jsonObject.getString("surname"),
					 ecryptedPassword,jsonObject.getString("birthDate"),
					 jsonObject.getString("department"),jsonObject.getString("phone"),
					 jsonObject.getString("address"),"2005-05-13 07:15:31","2005-05-13 07:15:31",
					 1,principal.getName(),jsonObject.getBoolean("postGraduate"),
					 jsonObject.getInt("yearOfEnrollment"));
			 
			 System.out.println(student);
			 
		//	 daoImplementation.insertStudent(student);
					 
			
		}
		
	
		
	    Student student=new Student(jsonObject.getString("email"),1,"fdsf","fdsfs","fdsfds","fdsfds","2108040921",
	    		"fdsfds","","","",1,"2005-05-13 07:15:31",true,1945);
	    
	   System.out.println(chiper.decrypt("OLgdVKbEolTOhEFaasOERlf0cdZc8lNOUvBjxGXjKgk="));
	    
	   System.out.println(student.validStudent(jsonObject));
		
		return "{}";
	
	}
}
