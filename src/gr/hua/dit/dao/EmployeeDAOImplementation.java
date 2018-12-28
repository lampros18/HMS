package gr.hua.dit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.entity.Employee;

@Repository
public class EmployeeDAOImplementation implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int insertEmployee(Employee employee) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		
		
		currentSession.merge(employee);
		currentSession.save(employee);

		
		return 0;
	}

}
