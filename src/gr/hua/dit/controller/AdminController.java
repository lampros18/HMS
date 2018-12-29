package gr.hua.dit.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.hua.dit.entity.Authorities;
import gr.hua.dit.entity.User;
import gr.hua.dit.fileManager.FileManager;
import gr.hua.dit.mail.MailServiceProvider;
//import gr.hua.dit.mail.MailServiceProvider;
import gr.hua.dit.request.EmployeeRequestHandler;
import gr.hua.dit.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private UserService userService;


	// Κεντική σελίδα διαχειριστή
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String showHomePage() {
		return "adminHome";
	}

	// Δημιουργία υπαλλήλου στην εφαρμογή
	@RequestMapping(value = "createUser", method = RequestMethod.GET)
	public String getCreateStudentForm() {
		return "admin/createUser";
	}

	@RequestMapping(value = "createUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String createEmployee(HttpServletRequest request) {
		// 1- Extract all the necessary data from the request and return a hash map
		EmployeeRequestHandler employeeRequestHandler = new EmployeeRequestHandler();
		String data = employeeRequestHandler.getSringifiedHttpResponse(request);

		try {
			JSONObject json = new JSONObject(data);

			if (json.length() != 0) {

				Principal principal = request.getUserPrincipal();
				json.put("createdBy", principal.getName());

				String unhashedPassword = json.getString("password");
				// 3- Encode the raw string to a hashed one
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				json.put("password", bCryptPasswordEncoder.encode(json.getString("password")));

				// 4 - Persist to database

				User user = new User(json);
				if (json.getString("authority_admin").equals("1")) {
					user.addAuthority(new Authorities("ROLE_ADMIN"));
				}
				if (json.getString("authority_foreman").equals("1")) {
					user.addAuthority(new Authorities("ROLE_FOREMAN"));
				}
				if (json.getString("authority_employee").equals("1")) {
					user.addAuthority(new Authorities("ROLE_EMPLOYEE"));
				}
				if (json.getString("authority_student").equals("1")) {
					user.addAuthority(new Authorities("ROLE_STUDENT"));
				}
				JSONObject result = new JSONObject();
				try {
					userService.createUser(user);
					FileManager fm = new FileManager();
					fm.writeCredentialsFile(json.getString("email"), unhashedPassword);
					
					MailServiceProvider msp = new MailServiceProvider();
					msp.sendEmail(json.getString("email"), "Housing managment system credentials", json.getString("email"), unhashedPassword);
					
					result.put("status", "200");
					result.put("result", "The user has been successfully created");
				} catch (Exception e) {
					result.put("status", "500");
					result.put("result",
							"We encountered an internal error. Please contact with the system administartor");
					e.printStackTrace();
				}

				// Now
//			SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
//			json.put("createdAt", dateFormatGmt.format(new Date()));
//			Employee employee = new Employee(json);
//			employeeService.createEmployee(employee);

				return result.toString();
			} else {
				return error();
			}
		} catch (JSONException e) {
			return error();
		}
	}

	private String error() {
		JSONObject result = new JSONObject();
		result.put("status", "500");
		result.put("result", "We encountered an internal error. Please contact with the system administartor");
		return result.toString();
	}
	
	@RequestMapping(value = "listUsers", method = RequestMethod.GET)
	public String getUsers() {
		for(User user : userService.getUsers()) {
			System.out.println(user.getUsername());
		}
		return null;
	}
}
