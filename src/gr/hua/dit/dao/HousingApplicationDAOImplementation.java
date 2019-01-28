package gr.hua.dit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import gr.hua.dit.entity.HousingApplication;

@Repository
public class HousingApplicationDAOImplementation implements HousingApplicationDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<HousingApplication> getAllHousingApplicationsOrderedDesc() {
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query
		Query<HousingApplication> query = currentSession.createQuery("from HousingApplication where createdAt >= (select gv.value from GeneralVariables gv where gv.key = :key) order by grade desc", HousingApplication.class);
		query.setParameter("key", "starting_date");
		
		// execute the query and get the results list
		return (List<HousingApplication>)query.getResultList();
	}

}
