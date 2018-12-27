package gr.hua.dit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/student")
public class StudentController {
	
	@RequestMapping(value="home", method=RequestMethod.GET)
	public String showHomePage() {
		return "studentHome";
	}
}
