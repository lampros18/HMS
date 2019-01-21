package gr.hua.dit.controller;
 
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.dit.entity.HousingApplication;
import gr.hua.dit.entity.Student;
import gr.hua.dit.entity.User;
import gr.hua.dit.service.StudentService;
import gr.hua.dit.service.UserService;
 
 
@RestController
@RequestMapping("api")
public class APIController {
     
    @Autowired
    private UserService userService;
   
    @Autowired
    private StudentService studentService;
   
    @PostMapping("authenticate")
    @ResponseBody
    public String Authenticate(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.findUserByUsername(username);
        if( user==null || (!user.isStudent()) ) {
            JSONObject json = new JSONObject();
            json.put("status", "fail");
            return json.toString();
        }
        JSONObject json = new JSONObject();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(password, user.getPassword())) {
            json.put("status", "success");
            json.put("id", String.valueOf(user.getId()));
        }
        else {
            json.put("status", "fail");
        }
        return json.toString();
    }
   
    @PostMapping("getTelephone")
    @ResponseBody
    public String GetTelephone(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Student student = studentService.getStudentById(id);
        if(student==null) {
            JSONObject json = new JSONObject();
            json.put("status", "fail");
            return json.toString();
        }
       
        JSONObject json = new JSONObject();
        json.put("status", "success");
        json.put("telephone", student.getPhone());
        json.put("address", student.getAddress());
       
        return json.toString();
    }
    
    @RequestMapping(value = "submitHousingApplication", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String submitHousingApplication(HttpServletRequest request) {
    	 String username = request.getParameter("username");
    	 double personalIncome = Double.parseDouble(request.getParameter("yourincome"));
    	 double familyIncome = Double.parseDouble(request.getParameter("familyincome"));
    	 int unemployedParents = Integer.parseInt(request.getParameter("unemployedparents"));
    	 int studyingSiblings = Integer.parseInt(request.getParameter("studyingsiblings"));
    	 int check = request.getParameter("check").equals("true") ? 1 : 0;
    	 
    	 SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		 dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
 		
 		 
 		Student student = studentService.findStudentByUsername(username);
    	
    	 int grade = 0;
    	 
    	 if(personalIncome == 0 && unemployedParents == 2)
    		 grade = 1000;
    	 else
    	 {
    		if(familyIncome < 10_000)
    			grade += 100;
    		else if(familyIncome < 15_000)
    			grade += 30;
    		
    		grade+=studyingSiblings*20;
    		
    		Calendar calendar = Calendar.getInstance();
    		
    		 
    		
    		if( calendar.get(Calendar.YEAR) - student.getYearOfEnrollment() > 4 )
    			grade = -1;
    	 }
    	 
    	 student
    	.addHousingApplication
    	    (
    	    		new HousingApplication(
    	    				personalIncome,
    	    				unemployedParents, 
    	    				familyIncome,
    	    				studyingSiblings,
    	    				check,
    	    				grade,
    	    				dateFormatGmt.format(new Date()),
    	    				dateFormatGmt.format(new Date())
    	    		)
    	    );
    	 
    	 
    	 JSONObject json = new JSONObject();
    	 if( studentService.insertStudent(student) == 0)
    		 json.put("status", "success");
    	 else
    		 json.put("status", "fail");
    	 return json.toString();
    }
    
    @RequestMapping(value = "getContactInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getContactInfo(HttpServletRequest request) {
    	
    	String username = request.getParameter("username");
    	
    	Student student = studentService.findStudentByUsername(username);
    	
    	JSONObject json = new JSONObject();
    	
    	if(student == null)
    		json.put("status", "fail");
    	else {
    		json.put("status", "success");
    		json.put("telephone", student.getPhone());
    		json.put("address", student.getAddress());
    	}
    	System.out.println(json.toString());
        return json.toString();
    }
    

}