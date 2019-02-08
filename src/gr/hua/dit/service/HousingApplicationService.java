package gr.hua.dit.service;

import java.util.List;
import gr.hua.dit.entity.HousingApplication;

public interface HousingApplicationService {
	List<HousingApplication> getAllHousingApplicationsOrderedDesc();
	List<HousingApplication> getAllUnverifiedHousingApplications();
	HousingApplication getHousingApplicationById(int id);
	int verifyHousingApplication(int id, int verified);
}
