package gr.hua.dit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.entity.HousingApplication;
import gr.hua.dit.entity.Student;
import gr.hua.dit.entity.User;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Student> getStudents() {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Student> query = currentSession.createQuery("from Student", Student.class);

		// execute the query and get the results list
		List<Student> students = query.getResultList();
		
		// return the results
		return students;
	}
	
	
	@Override
    public Student getStudentById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return (Student)currentSession.get(Student.class, id);
    }

	@Override
	public Student findStudentByUsername(String username) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Student> query = currentSession.createQuery("from Student where username=:username", Student.class);
		query.setParameter("username", username);
		// execute the query and get the results list
		List<Student> students = query.getResultList();
		
		// return the results
		if(students.size() > 0)
			return students.get(0);
		else
			return null;
	}
	

	@Override
	public int insertStudent(Student student) {
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			currentSession.saveOrUpdate(student);
			return 0;
		} catch (Exception e) {
			return -1;
		}
	}

}
