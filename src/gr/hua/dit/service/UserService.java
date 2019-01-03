package gr.hua.dit.service;

import java.util.List;

import gr.hua.dit.entity.User;

public interface UserService {
	public void createUser(User user);
	public List<User> getUsers();
}
