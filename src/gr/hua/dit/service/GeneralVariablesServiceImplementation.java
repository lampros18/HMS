package gr.hua.dit.service;

import java.util.HashMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.dit.dao.GeneralVariablesDAO;

@Service
public class GeneralVariablesServiceImplementation implements GeneralVariablesService {

	@Autowired 
	GeneralVariablesDAO gvdao;
	
	@Override
	@Transactional
	public HashMap<String, String> getGeneralVariables() {
		return gvdao.getGeneralVariables();
	}

	@Override
	@Transactional
	public int setApplicationsLimit(String department, int limit) {
		return gvdao.setApplicationsLimit(department, limit);
	}

	@Override
	@Transactional
	public int setDates(String startingDate, String endingDate) {
		return gvdao.setDates(startingDate, endingDate);
	}

	@Override
	@Transactional
	public int getApplicationsLimit(String department) {
		return gvdao.getApplicationsLimit(department);
	}

	@Override
	public HashMap<String, String> getDateVariables() {
		return gvdao.getDateVariables();
	}

}
