package gr.hua.dit.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.itextpdf.text.DocumentException;

import gr.hua.dit.entity.Authorities;
import gr.hua.dit.entity.HousingApplication;
import gr.hua.dit.entity.Student;
import gr.hua.dit.entity.User;
import gr.hua.dit.pdf.PDFMaker;
import gr.hua.dit.request.EmployeeRequestHandler;
import gr.hua.dit.security.Crypto;
import gr.hua.dit.service.GeneralVariablesService;
import gr.hua.dit.service.HousingApplicationService;
import gr.hua.dit.service.StudentService;
import gr.hua.dit.service.UserService;

@SessionAttributes({"username", "authorities"})
@Controller
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private UserService userService;

	@Autowired
	private HousingApplicationService housingApplicationService;

	@Autowired
	private GeneralVariablesService generalVariablesService;

	@Autowired
	private Crypto crypto;

	@RequestMapping(value = "employeeHome", method = RequestMethod.GET)
	public String showHomePage() {
		return "employee/employeeHome";
	}

	@RequestMapping(value = "createStudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String createEmployee(HttpServletRequest request, Principal principal) {

		EmployeeRequestHandler employeeRequestHandler = new EmployeeRequestHandler();

		JSONObject jsonObject = new JSONObject(employeeRequestHandler.getSringifiedHttpResponse(request));
		
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
	

		if (Student.validStudent(jsonObject)) {
			try {
			Student student = studentService.findStudentByUsername(jsonObject.getString("email"));
			student.setName(jsonObject.getString("name"));
			student.setSurname(jsonObject.getString("surname"));
			student.setBirthdate(jsonObject.getString("birthDate"));
			student.setYearOfEnrollment(Integer.parseInt(jsonObject.getString("yearOfEnrollment")));
			student.setPostgraduate(jsonObject.getBoolean("postGraduate"));
			student.setPhone(jsonObject.getString("phone"));
			student.setDepartment(jsonObject.getString("department"));
			student.setAddress(jsonObject.getString("address"));
			
			if( student.getCreatedAt() == null )
				student.setCreatedAt(dateFormatGmt.format(new Date()));
			student.setUpdatedAt(dateFormatGmt.format(new Date()));
			student.setCreatedBy(principal.getName());
			if(studentService.insertStudent(student) == 0 ) {
				JSONObject json = new JSONObject();
				json.put("result", 200).toString();
				json.put("username", student.getUser().getUsername());
				return json.toString();
			}
			else
				return new JSONObject().put("result", 500).toString();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return new JSONObject().put("result", 501).toString();
			}
			

		}

		return new JSONObject().put("result", 502).toString();

	}

	@RequestMapping(value = "getAllStudents", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getAllStudents(HttpServletRequest request) {

		JSONArray json = new JSONArray();
		JSONObject studentJSON;
		List<Student> students = studentService.getStudents();
		
		if( students != null )
		{
			for( Student student : students ) {
//				if(student.getCreatedAt() == null && student.getCreatedBy() == null)
				studentJSON = new JSONObject();
				studentJSON.put("username", student.getUser().getUsername());
				studentJSON.put("name", student.getName());
				studentJSON.put("surname", student.getSurname());
				studentJSON.put("birthdate", student.getBirthdate());
				studentJSON.put("yearOfEnrollment", student.getYearOfEnrollment());
				studentJSON.put("isPostgraduate", student.isPostgraduate()?1:0);
				studentJSON.put("department", student.getDepartment());
				studentJSON.put("phone", student.getPhone());
				studentJSON.put("address", student.getAddress());
				studentJSON.put("hasProfile", (student.getCreatedAt() == null && student.getCreatedBy() == null) ? 0 : 1);
				json.put(studentJSON);
			}
			
			return json.toString();
		}

		return "[]";
	}

	@RequestMapping(value = "updateStudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateStudent(HttpServletRequest request) {

		try {

			EmployeeRequestHandler employeeRequestHandler = new EmployeeRequestHandler();

			JSONObject jsonObject = new JSONObject(employeeRequestHandler.getSringifiedHttpResponse(request));

			Student student = studentService.findStudentByUsername(jsonObject.getString("email"));

			student.setPhone(crypto.encrypt(jsonObject.getString("phone")));

			student.setAddress(crypto.encrypt(jsonObject.getString("address")));

			studentService.insertStudent(student);

			JSONObject response = new JSONObject();

			response.put("result", 200);

			response.put("email", crypto.decrypt(student.getUser().getUsername()));

			return response.toString();

		} catch (Exception exp) {

			return "{result:407}";
		}

	}
	

	@RequestMapping(value = "getHousingApplications", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getHousingApplications(HttpServletRequest request) {

		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		for (HousingApplication ha : housingApplicationService.getAllUnverifiedHousingApplications()) {
			JSONObject housingApplication = new JSONObject();
			housingApplication.put("id", ha.getId());
			housingApplication.put("username", ha.getStudent().getUser().getUsername());
			housingApplication.put("createdAt", ha.getCreated_at());
			jsonArray.put(housingApplication);
		}
		json.put("housingApplications", jsonArray);
		return json.toString();
	}

	@RequestMapping(value = "housingApplications", method = RequestMethod.GET)
	public String housingApplications(Model model) {
		return "employee/housingApplications";
	}

	@RequestMapping(value = "/applications/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPDF(@PathVariable(value = "id") int id) {

		// Retrieve the whole housing application from database
		HousingApplication ha = housingApplicationService.getHousingApplicationById(id);

		// generate PDF
		byte[] contents = null;
		try {
			contents = new PDFMaker().init(ha);
		} catch (IOException | URISyntaxException | DocumentException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "verify/{id}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String verifyHousingApplication(@PathVariable(value = "id") int id) {
		JSONObject json = new JSONObject();
		if (housingApplicationService.verifyHousingApplication(id, 1) == 1)
			json.put("status", "success");
		else
			json.put("status", "failure");

		Thread check = new Thread() {
			public void run() {
				Check();
			}
		};
		check.start();

		return json.toString();
	}

	@RequestMapping(value = "reject/{id}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String rejectHousingApplication(@PathVariable(value = "id") int id) {
		JSONObject json = new JSONObject();
		if (housingApplicationService.verifyHousingApplication(id, -1) == 1)
			json.put("status", "success");
		else
			json.put("status", "failure");

		Thread check = new Thread() {
			public void run() {
				Check();
			}
		};
		check.start();

		return json.toString();
	}

	private void Check() {
		if (housingApplicationService.checkRemaining() && generalVariablesService.checkRemaining()) { 
			// True means the
			// submission
			// period is
			// over and
			// there
			// are no other applications left
			String url = "http://127.0.0.1:8080/HMS/mail/sendMailStudent";
			URL obj = null;
			try {
				obj = new URL(url);
			} catch (MalformedURLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			HttpURLConnection con=null;
			try {
				con = (HttpURLConnection) obj.openConnection();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// optional default is GET
			try {
				con.setRequestMethod("GET");
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//add request header
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.81 Safari/537.36");

			try {
				con.getResponseCode();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@ModelAttribute("authorities")
	public List<Authorities> getAuthorities(Principal principal) {
		User user = userService.findUserByUsername(principal.getName());
		return user.getAuthorities();
	}
	
	@ModelAttribute("username")
	public String getUsername(Principal principal) {
		if (crypto.isEncrypted(principal.getName()))
			return crypto.decrypt(principal.getName());
		else
			return principal.getName();
	}

}
