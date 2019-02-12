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
	public int setStartingDate(String startingDate) {
		return gvdao.setStartingDate(startingDate);
	}
	
	@Override
	@Transactional
	public int setEndingDate(String endingDate) {
		return gvdao.setEndingDate(endingDate);
	}

	@Override
	@Transactional
	public int getApplicationsLimit(String department) {
		return gvdao.getApplicationsLimit(department);
	}

	@Override
	@Transactional
	public HashMap<String, String> getDateVariables() {
		return gvdao.getDateVariables();
	}

	@Override
	@Transactional
	public boolean checkRemaining() {
		return gvdao.checkRemaining();
	}

}
