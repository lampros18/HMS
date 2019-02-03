package gr.hua.dit.dao;

import java.util.HashMap;

public interface GeneralVariablesDAO {

	String ECOLOGY_DEPARTMENT_DB_KEY = "max_ecology";
	String GEOGRAPHY_DEPARTMENT_DB_KEY = "max_geo";
	String INFORMATICS_DEPARTMENT_DB_KEY = "max_it";
	String TOURISM_DEPARTMENT_DB_KEY = "max_tourism";
	String NUTRIRION_DEPARTMENT_DB_KEY = "max_nutrition";
	String STARTING_DATE_DB_KEY = "starting_date";
	String ENDING_DATE_DB_KEY = "ending_date";

	HashMap<String, String> getGeneralVariables();

	int setApplicationsLimit(String department, int limit);


	int getApplicationsLimit(String department);

	HashMap<String, String> getDateVariables();

	int setStartingDate(String startingDate);

	int setEndingDate(String endingDate);
}
