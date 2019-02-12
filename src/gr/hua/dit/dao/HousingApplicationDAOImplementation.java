package gr.hua.dit.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.entity.HousingApplication;

@SuppressWarnings("deprecation")
@Repository
public class HousingApplicationDAOImplementation implements HousingApplicationDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<HousingApplication> getAllHousingApplicationsOrderedDesc() {
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query
		Query<HousingApplication> query = currentSession.createQuery(
				"from HousingApplication where createdAt >= (select gv.value from GeneralVariables gv where gv.key = :key) order by grade desc",
				HousingApplication.class);
		query.setParameter("key", "starting_date");

		// execute the query and get the results list
		return (List<HousingApplication>) query.getResultList();
	}

	@Override
	public List<HousingApplication> getAllUnverifiedHousingApplications() {
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query
		Query<HousingApplication> query = currentSession.createQuery(
				"from HousingApplication where createdAt >= (select gv.value from GeneralVariables gv where gv.key = :key) and verified = :verified",
				HousingApplication.class);
		query.setParameter("key", "starting_date");
		query.setParameter("verified", 0);

		// execute the query and get the results list
		return (List<HousingApplication>) query.getResultList();
	}

	@Override
	public HousingApplication getHousingApplicationById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			return currentSession.find(HousingApplication.class, id);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	@Override
	public int verifyHousingApplication(int id, int verified) {
		Session curSession = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query = curSession.createQuery("update HousingApplication ha set ha.verified =: value where ha.id =: id");
		query.setParameter("value", verified);
		query.setParameter("id",id);
		try {
			//Update verified column
			query.executeUpdate();
			
			if(verified == 1) {
				
				HousingApplication ha = curSession.find(HousingApplication.class, id);
				
				int grade = 0;

				if (ha.getPersonalIncome() == 0 && ha.getUnemployedParents() == 2)
					grade = 1000;
				else {
					if (ha.getFamilyIncome() < 10_000)
						grade += 100;
					else if (ha.getFamilyIncome() < 15_000)
						grade += 30;

					grade += ha.getNumberOfStudentSiblings() * 20;

					Calendar calendar = Calendar.getInstance();

					if (calendar.get(Calendar.YEAR) - ha.getStudent().getYearOfEnrollment() > 4)
						grade = -1;
				}
				query = curSession.createQuery("update HousingApplication ha set ha.grade =: value where ha.id =: id");
				query.setParameter("value", grade);
				query.setParameter("id",id);
				return query.executeUpdate();
			}
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}
	
	@Override
	public boolean checkRemaining() {
		if(getAllUnverifiedHousingApplications().size() == 0){
			return true;
		}
		return false;
	}

}
