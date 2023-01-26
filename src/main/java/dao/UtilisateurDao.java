package dao;

import java.util.List;

import beans.User;

public interface UtilisateurDao {
	void addUser(User user);
	
	User getUserByPseudoPassword(String pseudo, String password) throws DaoException;
	User getUserByID(long id) throws DaoException;
	List<User> getUsers();
	List<User> getLastUsers();
	
	void deleteUser(User user);
	
	public void updateUser(User user, String pseudo);
	public void updatePassword(User user, String password);
}
