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

}
