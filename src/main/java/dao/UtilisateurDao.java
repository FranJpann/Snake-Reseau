package dao;

import java.util.List;

import beans.User;

public interface UtilisateurDao {
	void addUser(User user);
	List<User> getUsers();
}
