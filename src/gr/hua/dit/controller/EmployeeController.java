package gr.hua.dit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gr.hua.dit.entity.Student;
import gr.hua.dit.service.StudentService;
@Controller
@RequestMapping("employee")
public class EmployeeController {
	
	@Autowired
	private StudentService studentService;

	@RequestMapping(value="employeeHome", method=RequestMethod.GET)
	public String showHomePage() {
		return "employee/employeeHome";
	}
	
	@RequestMapping(value="listStudents", method=RequestMethod.GET)
	public String listStudents(Model model) {
		
		
		List<Student> students = studentService.getStudents();

		// add the customers to the model
		model.addAttribute("students", students);

		// add page title
		model.addAttribute("pageTitle", "List Students");

		return "students";
	}
}
