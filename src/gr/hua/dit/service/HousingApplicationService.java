package gr.hua.dit.service;

import java.util.List;
import gr.hua.dit.entity.HousingApplication;

public interface HousingApplicationService {
	List<HousingApplication> getAllHousingApplicationsOrderedDesc();
}
