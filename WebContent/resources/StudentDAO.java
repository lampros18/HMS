package gr.hua.dit.hibernate;

import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import gr.hua.dit.entity.Student;

public class StudentDAO {

	SessionFactory factory;
	
	Session session;

	public int insertStudent(Student student) {
		// 1 -> Session factory for the student class
		factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// 2 -> Create session
		session = factory.getCurrentSession();
		try {
		 // start a transaction
        session.beginTransaction();
        
        // save the student object
        session.save(student);
        
        // commit transaction
        session.getTransaction().commit(); 
        
		} catch (IllegalStateException  e ) {
			return 0;
		} catch (RollbackException e) {
			session.getTransaction().rollback();
			return -1;
		}finally {
			session.close();
		}
		return 1;
	}

}
