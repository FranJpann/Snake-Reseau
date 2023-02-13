package dao;

import java.util.ArrayList;
import java.util.List;

import beans.GameHistory;
import beans.User;

public interface UtilisateurDao {
	
	/*	Getters	*/
	
	public User getUserByPseudoPassword(String pseudo, String password) throws DaoException;
	public User getUserByPseudo(String pseudo) throws DaoException;
	public User getUserByID(long id) throws DaoException;
	public List<User> getUsers();
	public List<User> getLastUsers();
	public ArrayList<String> getSkins(String pseudo);
	public ArrayList<GameHistory> getHistory(String pseudo);
	public long getNumberOfGames(String pseudo);
	public int getHighestScore(String pseudo);
	public ArrayList<User> getSomeBestPlayers();
	
	/*	Setters	*/
	
	public void addUser(User user, String password);
	public void deleteUser(User user);
	/*	 updateUser -> pour modifier seulement le pseudo, mettre null à la place de password	*/
	public void updateUser(User user, String pseudo, String password);
	
	public void addGame(String looser, String winner, int bestScore, int looserScore);
	public void setSkin(User user, String skin);
	public void addSkinToCollection(User user, String skin);
	
	public int getCredit(String pseudo);
	public void addCredit(String pseudo, int credit);
}
