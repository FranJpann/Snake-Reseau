package dao;

import java.util.List;

import beans.User;

public interface UtilisateurDao {
	void addUser(User user);
	boolean isUserInDB(String pseudo, String password);
	List<User> getUsers();
	List<User> getLastUsers();
}
