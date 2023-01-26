package dao;

import java.util.List;

import beans.User;

public interface UtilisateurDao {
	
	User getUserByPseudoPassword(String pseudo, String password) throws DaoException;
	User getUserByID(long id) throws DaoException;
	List<User> getUsers();
	List<User> getLastUsers();
	
	void addUser(User user, String password);
	void deleteUser(User user);
	
	/*	 updateUser -> pour modifier seulement le pseudo, mettre null à la place de password	*/
	public void updateUser(User user, String pseudo, String password);
}
