package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

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
		} catch(SQLException e) {
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
		} catch(SQLException e) {
		}
		
		return users;
	}
	
	public void addUser(User user, String password) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnection();
			
			preparedStatement = connexion.prepareStatement("INSERT INTO users(pseudo, password,skin) VALUES(?, SHA1(?),?);");
			preparedStatement.setString(1, user.getPseudo());
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, "green");
			preparedStatement.executeUpdate();
			
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getSkins(User user) {
		ArrayList<String> arrayOfStr = new ArrayList<String>();
		
		try {
			Connection connexion = daoFactory.getConnection();
			Statement statement = connexion.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT skins FROM users WHERE pseudo='"+user.getPseudo()+"';");
			
			if(resultat.next()) {
				String json = resultat.getString("skins");
				JSONObject jsonObject = new JSONObject(json);
				
				for(String str : JSONObject.getNames(jsonObject)){
					arrayOfStr.add(jsonObject.getString(str));
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return arrayOfStr;
	}
}
