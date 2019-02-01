package gr.hua.dit.dao;

import java.util.HashMap;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.entity.GeneralVariables;

@SuppressWarnings("deprecation")
@Repository
public class GeneralVariablesDAOImplementation implements GeneralVariablesDAO {

	@Autowired
	SessionFactory session;
	
	@Override
	public HashMap<String, String> getGeneralVariables() {
		Session curSession = session.getCurrentSession();
		Query<GeneralVariables> query = curSession.createQuery("from GeneralVariables", GeneralVariables.class);
		
		HashMap<String, String> map = new HashMap<>();
		for(GeneralVariables gv : query.getResultList()) {
			map.put(gv.getKey(), gv.getValue());
		}
		
		return map;
	}

}
