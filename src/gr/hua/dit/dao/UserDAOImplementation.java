package gr.hua.dit.dao;

import java.util.List;


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

	@Override
	public List<User> getUsers() {
		Session session = sessionFactory.getCurrentSession();
		List<User> users = (List<User>)session.createQuery("from User").getResultList();
		return users;
	}

}
