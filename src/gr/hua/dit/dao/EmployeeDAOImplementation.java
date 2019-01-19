package gr.hua.dit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.entity.Employee;
import gr.hua.dit.entity.Student;

@Repository
public class EmployeeDAOImplementation implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int insertEmployee(Employee employee) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		

		currentSession.persist(employee);

		
		return 0;
	}

	@Override
	public void insertStudent(Student student) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.persist(student);
		
	}




}
