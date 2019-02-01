package gr.hua.dit.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.hua.dit.service.GeneralVariablesService;

@Controller
@RequestMapping("foreman")
public class ForemanController {

	@Autowired
	GeneralVariablesService gvService;

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String showHomePage() {
		return "foreman/home";
	}

	@RequestMapping(value = "getDateVariables", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getDateVaribles(HttpServletRequest request) {

		JSONObject json = new JSONObject();
		HashMap<String, String> dateVariables = gvService.getDateVariables();

		if (dateVariables.containsKey(GeneralVariablesService.STARTING_DATE_DB_KEY))
			json.put("starting_date", dateVariables.get(GeneralVariablesService.STARTING_DATE_DB_KEY));
		else
			json.put("starting_date", "undefined");

		if (dateVariables.containsKey(GeneralVariablesService.STARTING_DATE_DB_KEY))
			json.put("ending_date", dateVariables.get(GeneralVariablesService.STARTING_DATE_DB_KEY));
		else
			json.put("ending_date", "undefined");

		return json.toString();
	}

	@RequestMapping(value = "setDateVariables", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String setDateVaribles(HttpServletRequest request) {
		String startingDate = request.getParameter("starting_date");
		String endingDate = request.getParameter("ending_date");
		int result = gvService.setDates(startingDate, endingDate);
		JSONObject json = new JSONObject();
		if (result == 0)
			json.put("status", "success");
		else
			json.put("status", "failure");
		return json.toString();
	}

	@RequestMapping(value = "setApplicationsLimit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String setApplicationsLimit(HttpServletRequest request) {
		String department = request.getParameter("department");
		JSONObject json = new JSONObject();
		int limit = -1;
		try {
			limit = Integer.parseInt(request.getParameter("limit"));
		} catch (Exception e) {
			json.put("status", "failure");
			return json.toString();
		}
		if (limit < 0) {
			json.put("status", "failure");
			return json.toString();
		}
		int result = gvService.setApplicationsLimit(department, limit);
		if (result == 0)
			json.put("status", "success");
		else
			json.put("status", "failure");
		return json.toString();
	}

	@RequestMapping(value = "getApplicationsLimit", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getApplicationsLimit(HttpServletRequest request) {
		String department = request.getParameter("department");
		JSONObject json = new JSONObject();

		int res = gvService.getApplicationsLimit(department);
		if (res >= 0) {
			json.put("status", "success");
			json.put("limit", res);
		} else
			json.put("status", "failure");
		return json.toString();
	}

}
