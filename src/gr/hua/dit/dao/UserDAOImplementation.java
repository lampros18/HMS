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
		currentSession.saveOrUpdate(user);
	}
	
	@Override
	public void updateUser(User user) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.merge(user);
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
	
	@Override
	public User findUserById(int id) {
		Session curSession = sessionFactory.getCurrentSession();
		return curSession.find(User.class, id);
	}
	
	@Override
	public int updateUsername(int id, String username) {
		Session curSession = sessionFactory.getCurrentSession();
		//curSession.flush();
		
		User user = curSession.find(User.class, id);
		
		user.setUsername(username);
		
	
		try {
			curSession.merge(user);
			return 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}
	
	@Override
	public void clearAuthorities(User user) {
		Session session = sessionFactory.getCurrentSession();
		try {
		  String hql = "delete from Authorities where username =: username";
		  @SuppressWarnings("rawtypes")
		Query query = session.createQuery(hql);
		  query.setParameter("username", user.getUsername());
		  System.out.println(query.executeUpdate());
		

		
		} catch (Throwable t) {
		
		  throw t;
		}
		
	}
	

}
