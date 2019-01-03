package gr.hua.dit.dao;

import java.util.List;

import gr.hua.dit.entity.User;

public interface UserDAO {
	void insertUser(User user);
	List<User> getUsers();
}
