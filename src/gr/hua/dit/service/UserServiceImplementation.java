package gr.hua.dit.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gr.hua.dit.dao.UserDAO;
import gr.hua.dit.entity.User;

@Service
public class UserServiceImplementation implements UserService {
	
	//Dependency injection
	@Autowired
	UserDAO userDAO;

	@Override
	@Transactional
	public void createUser(User user) {
		userDAO.insertUser(user);
	}
	
	@Override
	@Transactional
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	@Override
	@Transactional
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	@Transactional
	public void removeUser(int id) {
		userDAO.removeUser(id);
	}
	
	
	@Override
	@Transactional
	public User findUserByUsername(String username) {
		return userDAO.findUserByUsername(username);
	}
	
	@Override
	@Transactional
	public User findUserById(int id) {
		return userDAO.findUserById(id);
	}

	@Override
	@Transactional
	public int updateUsername(int id, String username) {
		return userDAO.updateUsername(id, username);
	}

	@Override
	@Transactional
	public void clearAuthorities(User user) {
		userDAO.clearAuthorities(user);
	}
}
