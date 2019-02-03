package gr.hua.dit.service;

import java.util.HashMap;

import gr.hua.dit.dao.GeneralVariablesDAO;

public interface GeneralVariablesService {
	
	String STARTING_DATE_DB_KEY = GeneralVariablesDAO.STARTING_DATE_DB_KEY;
	String ENDING_DATE_DB_KEY = GeneralVariablesDAO.ENDING_DATE_DB_KEY;
	
	HashMap<String, String> getGeneralVariables();
	
	int setApplicationsLimit(String department, int limit);

	int getApplicationsLimit(String department);

	HashMap<String, String> getDateVariables();

	int setEndingDate(String endingDate);

	int setStartingDate(String startingDate);
}
