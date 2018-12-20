package gr.hua.dit.springmvc1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gr.hua.dit.dao.StudentDAO;
import gr.hua.dit.dao.StudentDAOImpl;
import gr.hua.dit.entity.Student;



@Controller
public class HomeController {
	
	@Autowired
	private StudentDAO studentsService;
	
	
	@RequestMapping("/")
	public String listStudents(Model model) { 
		
		List<Student> students = studentsService.getStudents();
		
		// add the customers to the model
		model.addAttribute("students", students);
		
		// add page title
		model.addAttribute("pageTitle", "List Students");
		
		return "students";
	}

}
