package gr.hua.dit.service;

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
	
	

}
