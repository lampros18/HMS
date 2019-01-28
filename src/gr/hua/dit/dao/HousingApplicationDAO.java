package gr.hua.dit.dao;

import java.util.List;

import gr.hua.dit.entity.HousingApplication;

public interface HousingApplicationDAO {
	List<HousingApplication> getAllHousingApplicationsOrderedDesc(); 
}
