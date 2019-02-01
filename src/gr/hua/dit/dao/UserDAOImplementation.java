package gr.hua.dit.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.entity.User;

@SuppressWarnings("deprecation")
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

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(User.class, "user");
		c.createAlias("user.authorities", "authorities");
		return c.list();
	}

	@Override
	public void removeUser(int id) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.find(User.class, id);
		session.remove(user);
	}

	@Override
	public User findUserByUsername(String username) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<User> query = currentSession.createQuery("from User where username=:username", User.class);
		query.setParameter("username", username);
		// execute the query and get the results list
		List<User> users = query.getResultList();
		
		// return the results
		if(users.size() > 0)
			return users.get(0);
		else
			return null;
	}
	

}
