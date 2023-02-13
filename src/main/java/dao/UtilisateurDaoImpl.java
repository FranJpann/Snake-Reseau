package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import beans.GameHistory;
import beans.User;

public class UtilisateurDaoImpl implements UtilisateurDao {
	private DaoFactory daoFactory;

    UtilisateurDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public User getUserByPseudoPassword(String pseudo, String password) throws DaoException {
    	User user = new User();
    	
    	try {
			Connection connexion = daoFactory.getConnection();
			Statement statement = connexion.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT id, pseudo, skin FROM users WHERE pseudo='"+ pseudo + "' && password=SHA1('"+ password +"');");
			
			
			if(resultat.next() == true) {
				user.setId(resultat.getInt("id"));
				user.setPseudo(resultat.getString("pseudo"));
				user.setSkin(resultat.getString("skin"));
			}
			else throw new DaoException("User or password wrong in Database -> "+pseudo);
			
			
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return user;
    }
    
    public User getUserByPseudo(String pseudo) throws DaoException{
		User user = new User();
		
    	try {
			Connection connexion = daoFactory.getConnection();
			Statement statement = connexion.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT id, pseudo, skin FROM users WHERE pseudo='"+pseudo+"';");
			
			if(resultat.next() == false) throw new DaoException("User is not in database");
			else {
				user.setId(resultat.getInt("id"));
				user.setPseudo(resultat.getString("pseudo"));
				user.setSkin(resultat.getString("skin"));
			}
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return user;
    }
    
    public User getUserByID(long id) throws DaoException{
		User user = new User();
		
    	try {
			Connection connexion = daoFactory.getConnection();
			Statement statement = connexion.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT id, pseudo, skin FROM users WHERE id='"+id+"';");
			
			if(resultat.next() == false) throw new DaoException("User is not in database");
			else {
				user.setId(resultat.getInt("id"));
				user.setPseudo(resultat.getString("pseudo"));
				user.setSkin(resultat.getString("skin"));
			}
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return user;
    }
    
    public List<User> getUsers(){
		List<User> users = new ArrayList<User>();
		
		try {
			Connection connexion = daoFactory.getConnection();
			Statement statement = connexion.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT id, pseudo, skin FROM users;");
			
			while(resultat.next()) {
				
				User user = new User();
				user.setId(resultat.getInt("id"));
				user.setPseudo(resultat.getString("pseudo"));
				user.setSkin(resultat.getString("skin"));
				
				users.add(user);
			}
			
			connexion.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
    
    public List<User> getLastUsers(){
		List<User> users = new ArrayList<User>();
		
		try {
			Connection connexion = daoFactory.getConnection();
			Statement statement = connexion.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT * FROM users ORDER BY id DESC LIMIT 5;");
			
			while(resultat.next()) {
				
				User user = new User();
				user.setId(resultat.getInt("id"));
				user.setPseudo(resultat.getString("pseudo"));
				user.setSkin(resultat.getString("skin"));
				
				users.add(user);
			}
			
			connexion.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public void addUser(User user, String password) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnection();
			
			preparedStatement = connexion.prepareStatement("INSERT INTO users(pseudo, password, skin, skins, credit) VALUES(?, SHA1(?), ?, ?, ?);");
			preparedStatement.setString(1, user.getPseudo());
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, "green");
			preparedStatement.setString(4, "{ \"1\" : \"green\" }");
			preparedStatement.setInt(4, 0);
			preparedStatement.executeUpdate();
			
			connexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUser(User user) {
		try {
			Connection connexion = daoFactory.getConnection();
			
			PreparedStatement preparedStatement = connexion.prepareStatement("DELETE FROM users WHERE pseudo=? ;");
			preparedStatement.setString(1, user.getPseudo());
			preparedStatement.executeUpdate();
			connexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(User user, String pseudo, String password) {
		try {
			Connection connexion = daoFactory.getConnection();
			PreparedStatement preparedStatement;
			
			if(password == null) {
				preparedStatement = connexion.prepareStatement("UPDATE users SET pseudo=? WHERE id=?;");
				preparedStatement.setString(1, pseudo);
				preparedStatement.setInt(2, (int) user.getId());
			}
			else {
				preparedStatement = connexion.prepareStatement("UPDATE users SET pseudo=? , password=SHA1(?) WHERE id=?;");
				preparedStatement.setString(1, pseudo);
				preparedStatement.setString(2, password);
				preparedStatement.setInt(3, (int) user.getId());
			}
			
			preparedStatement.executeUpdate();
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getSkins(String pseudo) {
		ArrayList<String> arrayOfStr = new ArrayList<>();
		
		try {
			Connection connexion = daoFactory.getConnection();
			Statement statement = connexion.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT skins FROM users WHERE pseudo='"+pseudo+"';");
			
			if(resultat.next()) {
				String json = resultat.getString("skins");
				JSONObject jsonObject = new JSONObject(json);
				
				for(String str : JSONObject.getNames(jsonObject)){
					arrayOfStr.add(jsonObject.getString(str));
				}
			}
			connexion.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return arrayOfStr;
	}
	
	public ArrayList<GameHistory> getHistory(String pseudo){
		ArrayList<GameHistory> gameHistory = new ArrayList<>();
		
		try {
			Connection connexion = daoFactory.getConnection();
			Statement statement = connexion.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT * FROM games WHERE winner='"+pseudo+"' OR looser='"+pseudo+"';");
			
			while(resultat.next()) {
				try {
					gameHistory.add( new GameHistory(
							resultat.getInt("number"),
							getUserByPseudo(resultat.getString("looser")),
							getUserByPseudo(resultat.getString("winner")),
							resultat.getInt("bestScore"),
							resultat.getInt("looserScore")
							));
				} catch (DaoException e) {
					e.printStackTrace();
				}
			}
			connexion.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return gameHistory;
	}
	
	public long getNumberOfGames(String pseudo) {
		
		ArrayList<GameHistory> gamesHistory = getHistory(pseudo);
		return gamesHistory.size();
	}
	
	public int getHighestScore(String pseudo) {
		try {
			Connection connexion = daoFactory.getConnection();
			Statement statement = connexion.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT MAX(bestScore) AS bestScore FROM games WHERE winner='"+pseudo+"';");
			
			if(resultat.next()) {
				return resultat.getInt("bestScore");
			}
			connexion.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<User> getSomeBestPlayers(){
		ArrayList<User> list = new ArrayList<>();
		
		try {
			Connection connexion = daoFactory.getConnection();
			Statement statement = connexion.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT DISTINCT(winner) as winners FROM (SELECT * FROM games ORDER BY bestScore) AS scores LIMIT 5;");
			
			while(resultat.next()) {
				User user;
				try {
					user = getUserByPseudo(resultat.getString("winners"));
					list.add(user);
				} catch (DaoException e) {
					e.printStackTrace();
				}
			}
			connexion.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addGame(String looser, String winner, int bestScore, int looserScore) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
			
		try {
			connexion = daoFactory.getConnection();
				
			preparedStatement = connexion.prepareStatement("INSERT INTO games(looser, winner, bestScore, looserScore) VALUES(?, ?, ?, ?);");
			preparedStatement.setString(1, looser);
			preparedStatement.setString(2, winner);
			preparedStatement.setInt(3, bestScore);
			preparedStatement.setInt(4, looserScore);
			preparedStatement.executeUpdate();
				
			connexion.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setSkin(User user, String skin) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
			
		try {
			connexion = daoFactory.getConnection();
				
			preparedStatement = connexion.prepareStatement("UPDATE users SET skin=? WHERE pseudo=?;");
			preparedStatement.setString(1, skin);
			preparedStatement.setString(2, user.getPseudo());
			preparedStatement.executeUpdate();
				
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addSkinToCollection(User user, String skin) {
		ArrayList<String> skins = getSkins(user.getPseudo());
		skins.add(skin);
		
		JSONObject jsonObj = new JSONObject();
		int c = 0;
		
		for(String s: skins) {
			c++;
			jsonObj.put(Integer.toString(c), s);
		}
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
			
		try {
			connexion = daoFactory.getConnection();
				
			preparedStatement = connexion.prepareStatement("UPDATE users SET skins=? WHERE id=?;");
			preparedStatement.setString(1, jsonObj.toString());
			preparedStatement.setInt(2, (int) user.getId());
			preparedStatement.executeUpdate();
				
			connexion.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addCredit(String pseudo, int credit) {
		
		int actualCredit = getCredit(pseudo);
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnection();
			
			preparedStatement = connexion.prepareStatement("UPDATE users SET credit=? WHERE pseudo=?;");
			preparedStatement.setInt(1, actualCredit + credit);
			preparedStatement.setString(2, pseudo);
			preparedStatement.executeUpdate();
			
			connexion.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public int getCredit(String pseudo) {
		int credit = 0;
		
		try {
			Connection connexion = daoFactory.getConnection();
			Statement statement = connexion.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT credit FROM users WHERE pseudo='"+pseudo+"';");
			
			if(resultat.next()) {
				credit = resultat.getInt("credit");
			}
			connexion.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return credit;
	}
}
