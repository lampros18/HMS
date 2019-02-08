package gr.hua.dit.controller;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.dit.entity.HousingApplication;
import gr.hua.dit.entity.Student;
import gr.hua.dit.entity.User;
import gr.hua.dit.service.GeneralVariablesService;
import gr.hua.dit.service.HousingApplicationService;
import gr.hua.dit.service.StudentService;
import gr.hua.dit.service.UserService;

@RestController
@RequestMapping("api")
public class APIController {

	@Autowired
	private UserService userService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private HousingApplicationService housingApplicationService;

	@Autowired
	private GeneralVariablesService generalVariablesService;

	@PostMapping("authenticate")
	@ResponseBody
	public String Authenticate(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userService.findUserByUsername(username);
		if (user == null || (!user.isStudent())) {
			JSONObject json = new JSONObject();
			json.put("status", "fail");
			return json.toString();
		}
		JSONObject json = new JSONObject();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (passwordEncoder.matches(password, user.getPassword())) {
			json.put("status", "success");
			json.put("id", String.valueOf(user.getId()));
		} else {
			json.put("status", "fail");
		}
		return json.toString();
	}

	@PostMapping("getTelephone")
	@ResponseBody
	public String GetTelephone(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));

		Student student = studentService.getStudentById(id);
		if (student == null) {
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
		byte[] data = Base64.getDecoder().decode(request.getParameter("file").replaceAll(" ", "+").getBytes());
		String type = request.getParameter("file_type");
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));

		Student student = studentService.findStudentByUsername(username);

		student.addHousingApplication(
				new HousingApplication(personalIncome, unemployedParents, familyIncome, studyingSiblings, check, 0,
						dateFormatGmt.format(new Date()), dateFormatGmt.format(new Date()), type, data));

		JSONObject json = new JSONObject();
		if (studentService.insertStudent(student) == 0)
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

		if (student == null)
			json.put("status", "fail");
		else {
			json.put("status", "success");
			json.put("telephone", student.getPhone());
			json.put("address", student.getAddress());
		}
		return json.toString();
	}

	@RequestMapping(value = "getStatus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getStatus(HttpServletRequest request) {

		String username = request.getParameter("username");

		List<HousingApplication> housingApplications = housingApplicationService.getAllHousingApplicationsOrderedDesc();

		Student student = studentService.findStudentByUsername(username);
		JSONObject json = new JSONObject();

		int i = 0;
		boolean found = false;

		for (HousingApplication hs : housingApplications) {
			if (hs.getStudent().getUser().getUsername().equals(student.getUser().getUsername())) {
				found = true;
				break;
			}
			i++;
		}

		if (found) {
			HashMap<String, String> gvMap = generalVariablesService.getGeneralVariables();
			int max = 0;
			if (housingApplications.get(i).getVerified() == 0) {
				json.put("status", "success");
				json.put("qualify", "unverified");
				return json.toString();
			}
			try {
				switch (student.getDepartment()) {
				case "Informatics and Telematics":
					max = Integer.parseInt(gvMap.get("max_it"));
					break;
				case "Nutrition and Dietetics":
					max = Integer.parseInt(gvMap.get("max_nutrition"));
					break;
				case "Home Economic and Ecology":
					max = Integer.parseInt(gvMap.get("max_ecology"));
					break;
				case "Geography":
					max = Integer.parseInt(gvMap.get("max_geo"));
					break;
				case "International Master of Sustainable Tourism Development":
					max = Integer.parseInt(gvMap.get("max_tourism"));
					break;
				}
				json.put("status", "success");
				json.put("rank", i + 1);
				if (housingApplications.get(i).getVerified() == 1)
					json.put("qualify", i + 1 <= max ? "true" : "full");
				else if (housingApplications.get(i).getVerified() == -1)
					json.put("qualify", "false");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				json.put("status", "fail");
			}
		} else {
			json.put("status", "fail");
		}

		return json.toString();
	}

	@RequestMapping(value = "canSubmit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String canSubmit(HttpServletRequest request) {
		HashMap<String, String> map = generalVariablesService.getGeneralVariables();
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));

		String currentDate = dateFormatGmt.format(new Date());
		String startingDate = map.get("starting_date");
		String endingDate = map.get("ending_date");
		JSONObject json = new JSONObject();

		if (startingDate == "" || startingDate == null || endingDate == "" || endingDate == null)
			json.put("status", "fail");
		else {

			if (startingDate.compareTo(endingDate) > 0) {
				json.put("status", "fail");
				return json.toString();
			}

			json.put("status", "success");
			if (currentDate.compareTo(startingDate) >= 0 && currentDate.compareTo(endingDate) <= 0)
				json.put("canSubmit", "true");
			else
				json.put("canSubmit", "false");
		}

		return json.toString();
	}

	@RequestMapping(value = "editProfile/{username:.+}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String editProfile(@PathVariable("username") String username, @RequestParam String telephone,
			@RequestParam String address) {
		Student student = studentService.findStudentByUsername(username);
		JSONObject json = new JSONObject();

		if (username == "" || username == null || telephone == "" || telephone == null || address == ""
				|| address == null || student == null) {
			json.put("status", "fail");
			return json.toString();
		}

		student.setPhone(telephone);
		student.setAddress(address);
		if (studentService.insertStudent(student) == 0)
			json.put("status", "success");

		return json.toString();
	}

}