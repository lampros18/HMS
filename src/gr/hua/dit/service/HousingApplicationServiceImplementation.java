package gr.hua.dit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.dit.dao.HousingApplicationDAOImplementation;
import gr.hua.dit.entity.HousingApplication;

@Service
public class HousingApplicationServiceImplementation implements HousingApplicationService {
	@Autowired
	HousingApplicationDAOImplementation haDAO;
	
	@Override
	@Transactional
	public List<HousingApplication> getAllHousingApplicationsOrderedDesc() {
		return haDAO.getAllHousingApplicationsOrderedDesc();
	}
	
}
