package gr.hua.dit.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import gr.hua.dit.entity.HousingApplication;

public interface HousingApplicationService {
	List<HousingApplication> getAllHousingApplicationsOrderedDesc();
}
