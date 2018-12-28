package gr.hua.dit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.entity.User;

@Repository
public class UserDAOImplementation implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insertUser(User user) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		

		currentSession.persist(user);

		
	}

}
