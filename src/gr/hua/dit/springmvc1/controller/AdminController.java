package gr.hua.dit.springmvc1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.hua.dit.entity.Student;

@Controller
public class AdminController {
	
//	int id = Integer.parseInt(request.getParameter("id"));
//	String email = request.getParameter("email");
//	String name = request.getParameter("name");
//	String surname = request.getParameter("surname");
//	String password = request.getParameter("password");
//	String birthdate = request.getParameter("birthdate");
//	int yearOfEnrollment = Integer.parseInt(request.getParameter("yearOfenrollement"));
//	boolean isPostgraduate = request.getParameter("isPostgraduate") == "1" ? true : false;
//	String department = request.getParameter("department");
//	String phone = request.getParameter("phone");
//	String address = request.getParameter("address");
//	int currentSemester = Integer.parseInt(request.getParameter("currentSemester"));
//	//createdAt
//	//updateAt
//	int employeeId = Integer.parseInt(request.getParameter("employeeId"));
//	
//	//Create the student object
//	Student student = new Student(id,  email,  name,  surname,  birthdate,  department,  phone,
//			 address,  "2018-01-01 10:10:10",  "null",  yearOfEnrollment,  isPostgraduate
//			);
//	
	//Store to database with hibernate
	
	
	
	@RequestMapping(value = "addStudent", method = RequestMethod.POST)
	public @ResponseBody
	String addStudentMethod(@RequestParam(value = "id", required = true) String parse) {
	    System.out.println("parse"+parse);
	    //value from parse=test
	return "Success";
	}
}
