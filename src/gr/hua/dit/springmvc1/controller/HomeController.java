package gr.hua.dit.springmvc1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import gr.hua.dit.entity.Student;
import gr.hua.dit.service.StudentService;

@Controller
public class HomeController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/")
	public String listStudents(Model model) {

	//	List<Student> students = studentService.getStudents();

		// add the customers to the model
	//	model.addAttribute("students", students);

		// add page title
	//	model.addAttribute("pageTitle", "List Students");

		return "simpleStudentForm";
	}

}
