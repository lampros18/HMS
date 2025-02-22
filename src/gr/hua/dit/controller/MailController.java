package gr.hua.dit.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.hua.dit.entity.Employee;
import gr.hua.dit.entity.HousingApplication;
import gr.hua.dit.mail.MailService;
import gr.hua.dit.service.EmployeeService;
import gr.hua.dit.service.GeneralVariablesService;
import gr.hua.dit.service.HousingApplicationService;

@Controller
@RequestMapping("mail")
public class MailController {

	@Autowired
	private MailService mailService;

	@Autowired
	private HousingApplicationService housingApplicationService;

	@Autowired
	private GeneralVariablesService generalVariablesService;

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "sendMailStudent", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String sendMailStudent() {
		System.out.println("[LOG] sendMailStudent");
		List<HousingApplication> housingApplications = housingApplicationService.getAllHousingApplicationsOrderedDesc();
		int itCounter = 0, geoCounter = 0, ecoCounter = 0, nutritionCounter = 0, tourismCounter = 0;
		HashMap<String, String> gv = generalVariablesService.getGeneralVariables();
		String noMessage = "Σας ενημερώνουμε πως σύμφωνα με την κατάταξή σας ΔΕ δικαιούστε δωρεάν στέγαση. Για οποιαδήποτε απορία μπορείτε να απαντήσετε στο alexkalog@alwaysdata.net.";
		String yesMessage = "Σας ενημερώνουμε πως σύμφωνα με την κατάταξή σας δικαιούστε δωρεάν στέγαση. Για οποιαδήποτε απορία μπορείτε να απαντήσετε στο alexkalog@alwaysdata.net.";
		String epidomaMessage = "Σας ενημερώνουμε πως σύμφωνα με την κατάταξή σας δικαιούστε επίδομα στέγασης. Για οποιαδήποτε απορία μπορείτε να απαντήσετε στο alexkalog@alwaysdata.net.";
		String message = "";
		String maxName = "";
		int counter = 0;
		for (HousingApplication hs : housingApplications) {
			switch (hs.getStudent().getDepartment()) {
			case "Informatics and telematics":
				maxName = "max_it";
				counter = itCounter;
				itCounter++;
				break;
			case "Home economics and ecology":
				maxName = "max_ecology";
				counter = ecoCounter;
				ecoCounter++;
				break;
			case "Geography":
				maxName = "max_geo";
				counter = geoCounter;
				geoCounter++;
				break;
			case "International master of sustainable tourism development":
				maxName = "max_tourism";
				counter = tourismCounter;
				tourismCounter++;
				break;
			case "Nutrition and dietics":
				maxName = "max_nutrition";
				counter = nutritionCounter;
				nutritionCounter++;
				break;
			}

			try {
				if (counter < Integer.parseInt(gv.get(maxName)))
					message = yesMessage;
				else if (counter < Integer.parseInt(gv.get(maxName)) + 100)
					message = epidomaMessage;
				else
					message = noMessage;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				mailService.sendMail("HMS@alwaysdata.net", hs.getStudent().getUser().getUsername(),
						"Αποτέλεσμα αίτησης στέγασης", message);
			} catch (Exception e) {
				System.out.println("wrong email format");
			}
		}
		return "";

	}

	@RequestMapping(value = "sendMailEmployee", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String sendMailEmployee() {
		System.out.println("[LOG] sendMailEmployee");
		int size = housingApplicationService.getAllUnverifiedHousingApplications().size();

		String message = "";
		if (size == 0) {
			message = "Η περίοδος υποβολής δηλώσεων έχει λήξει. Δεν υπάρχουν περαιτέρω αιτήσεις στέγασης για έλεγχο.";
			sendMailStudent();
		} else if (size == 1)
			message = "Η περίοδος υποβολής δηλώσεων έχει λήξει. Απομένει " + size + " αίτηση στέγασης για έλεγχο.";
		else
			message = "Η περίοδος υποβολής δηλώσεων έχει λήξει. Απομένουν " + size + " αιτήσεις στέγασης για έλεγχο.";
		for (Employee employee : employeeService.getAllEmployees())
			try {
				mailService.sendMail("HMS@alwaysdata.net", employee.getUser().getUsername(),
						"Ενημέρωση αιτήσεων στέγασης", message);
			} catch (Exception e) {
				System.out.println("wrong email format");
			}
		return "";
	}

	@RequestMapping(value = "sendMailContact", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String sendMailContact(HttpServletRequest request, Principal principal) {
		String message = "Message from: " + principal.getName() + "\n\n" + request.getParameter("message");
		JSONObject json = new JSONObject();
		try {
			mailService.sendMail("alexkalog@alwaysdata.net", "alexkalog@alwaysdata.net", "Internal contact form",
					message);
			json.put("status", "success");
			return json.toString();
		} catch (Exception e) {
			json.put("status", "error");
			return json.toString();
		}

	}
}
