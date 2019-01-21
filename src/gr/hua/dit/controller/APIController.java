package gr.hua.dit.controller;
 
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
}