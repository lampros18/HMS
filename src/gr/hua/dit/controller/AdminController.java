package gr.hua.dit.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import gr.hua.dit.entity.Authorities;
import gr.hua.dit.entity.Employee;
import gr.hua.dit.entity.Student;
import gr.hua.dit.entity.User;
import gr.hua.dit.fileManager.FileManager;
import gr.hua.dit.mail.MailService;
//import gr.hua.dit.mail.MailServiceProvider;
import gr.hua.dit.request.EmployeeRequestHandler;
import gr.hua.dit.security.Crypto;
import gr.hua.dit.service.EmployeeService;
import gr.hua.dit.service.StudentService;
import gr.hua.dit.service.UserService;

@Controller
@SessionAttributes({"username", "authorities"})
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private Crypto crypto;

	@ModelAttribute("username")
	public String getUsername(Principal principal) {
		if (crypto.isEncrypted(principal.getName()))
			return crypto.decrypt(principal.getName());
		else
			return principal.getName();
	}

	@ModelAttribute("authorities")
	public List<Authorities> getAuthorities(Principal principal) {
		User user = userService.findUserByUsername(principal.getName());
		return user.getAuthorities();
	}

	// Κεντική σελίδα διαχειριστή
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String showHomePage() {
		return "admin/adminHome";
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
					if (json.getString("authority_foreman").equals("1")
							|| json.getString("authority_employee").equals("1")) {
						Employee employee = new Employee();
						employee.setUser(user);
						employeeService.createEmployee(employee);
					} else if (json.getString("authority_student").equals("1")) {
						Student student = new Student();
						student.setUser(user);
						studentService.insertStudent(student);

					} else {
						throw new Exception();
					}

					FileManager fm = new FileManager();
					fm.writeCredentialsFile(json.getString("email"), unhashedPassword);

					if (json.getString("email").contains("@"))
						mailService.sendMail("support@hms.com", json.getString("email"),
								"Housing managment system credentials", "Your password is: " + unhashedPassword );

					result.put("status", "200");
					result.put("result", "The user has been successfully created");
				} catch (Exception e) {
					result.put("status", "500");
					result.put("result",
							"We encountered an internal error. Please contact with the system administartor");
					e.printStackTrace();
				}

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
		for (User user : userService.getUsers()) {
			System.out.println("username : " + user.getUsername());
			for (Authorities authorities : user.getAuthorities()) {
				System.out.println("authority : " + authorities.getAuthority());
			}
		}
		return "null";
	}

	@RequestMapping(value = "editUsers", method = RequestMethod.GET)
	public String editUsers() {
		return "admin/editUsers";
	}

	@RequestMapping(value = "editUsers", method = RequestMethod.POST)
	@ResponseBody
	public String getEditUsers() {
		JSONObject json = new JSONObject();
		JSONArray data = new JSONArray();
		HashSet<User> userSet = new HashSet<>();

		JSONObject jsonUser;
		for (User user : userService.getUsers()) {
			jsonUser = new JSONObject();
			jsonUser.put("id", user.getId());
			jsonUser.put("email", user.getUsername());
			jsonUser.put("password", user.getPassword());
			jsonUser.put("enabled", Integer.parseInt(user.getEnabled()));

			boolean flagu = false;
			boolean flage = false;
			boolean flagf = false;
			boolean flags = false;

			for (Authorities authorities : user.getAuthorities()) {
				if (authorities.getAuthority().equals("ROLE_ADMIN")) {
					jsonUser.put("admin", 1);
					flagu = true;
				} else {
					if (!flagu) {
						jsonUser.put("admin", 0);
					}
				}

				if (authorities.getAuthority().equals("ROLE_EMPLOYEE")) {
					jsonUser.put("employee", 1);
					flage = true;
				} else {
					if (!flage) {
						jsonUser.put("employee", 0);
					}
				}

				if (authorities.getAuthority().equals("ROLE_FOREMAN")) {
					jsonUser.put("foreman", 1);
					flagf = true;
				} else {
					if (!flagf) {
						jsonUser.put("foreman", 0);
					}
				}
				if (authorities.getAuthority().equals("ROLE_STUDENT")) {
					jsonUser.put("student", 1);
					flags = true;
				} else {
					if (!flags) {
						jsonUser.put("student", 0);
					}
				}

			}
			if (userSet.add(user))
				data.put(jsonUser);

		} // end of json user
		json.put("users", data);
		userSet = null;
		return json.toString();
	}

	@RequestMapping(value = "editUserRow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String editUserRow(HttpServletRequest request) {
		EmployeeRequestHandler employeeRequestHandler = new EmployeeRequestHandler();
		String data = employeeRequestHandler.getSringifiedHttpResponse(request);

		JSONObject result = new JSONObject(data);
		try {
			JSONObject json = new JSONObject(data);
			User user;
			user = userService.findUserById(json.getInt("id"));
			//user.setUsername(json.getString("username"));

			if (!user.isStudent()) {
				if (json.getInt("adminAuthority") == 0 && json.getInt("foremanAuthority") == 0
						&& json.getInt("employeeAuthority") == 0) {
					// Impossible, do not update the authorities
				} else {
					userService.clearAuthorities(user);
					user = userService.findUserById(json.getInt("id"));
					user.clearAuthorities();
					if (json.getInt("adminAuthority") == 1) {
						user.addAuthority(new Authorities("ROLE_ADMIN"));
					}
					
					if (json.getInt("foremanAuthority") == 1) {
						user.addAuthority(new Authorities("ROLE_FOREMAN"));
					}
					
					if (json.getInt("employeeAuthority") == 1) {
						user.addAuthority(new Authorities("ROLE_EMPLOYEE"));
					}

				}
			}
			
			user.setEnabled(json.getString("enabled"));
			if (!json.getString("password").equals("")) {
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				user.setPassword(bCryptPasswordEncoder.encode(json.getString("password")));
			}

			//userService.createUser(user);
			userService.updateUser(user);
			for(Authorities auth : user.getAuthorities()) {
				System.out.println(auth.getAuthority());
			}
			userService.updateUsername(user.getId(), json.getString("username"));
			
			
			result.put("status", "200");
			
			result.put("result", "User updated successfully!");
			return result.toString();
		} catch (Exception e) {
			result.put("status", "500");
			result.put("result", "We encountered an internal error. Please contact with the system administartor");
			e.printStackTrace();
			return result.toString();
		}
	}

	@RequestMapping(value = "deleteUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteUser(HttpServletRequest request) {

		EmployeeRequestHandler employeeRequestHandler = new EmployeeRequestHandler();
		String data = employeeRequestHandler.getSringifiedHttpResponse(request);

		JSONObject result = new JSONObject();
		try {
			JSONObject json = new JSONObject(data);
			userService.removeUser(json.getInt("id"));
			result.put("status", "200");
			result.put("result", "The user has been successfully deleted");
			return result.toString();
		} catch (Exception e) {
			result.put("status", "500");
			result.put("result", "We encountered an internal error. Please contact with the system administartor");
			e.printStackTrace();
			return result.toString();
		}

	}

	@RequestMapping(value = "editEmail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String editEmail(HttpServletRequest request) {

		EmployeeRequestHandler employeeRequestHandler = new EmployeeRequestHandler();
		String data = employeeRequestHandler.getSringifiedHttpResponse(request);

		JSONObject result = new JSONObject();
		JSONObject json = new JSONObject(data);
		if (userService.updateUsername(json.getInt("id"), json.getString("username")) == 0) {
			result.put("status", "200");
			result.put("result", "The user's email has been successfully updated");
			return result.toString();
		} else {
			result.put("status", "500");
			result.put("result", "We encountered an internal error. Please contact with the system administartor");
			return result.toString();
		}
	}

}
