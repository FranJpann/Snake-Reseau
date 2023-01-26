package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
			ResultSet resultat = statement.executeQuery("SELECT id, pseudo FROM users WHERE pseudo='"+ pseudo + "' && password=SHA1('"+ password +"');");
			
			
			if(resultat.next() == true) {
				user.setId(resultat.getInt("id"));
				user.setPseudo(resultat.getString("pseudo"));
			}
			else throw new DaoException("User is not in Database");
			
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
			ResultSet resultat = statement.executeQuery("SELECT id, pseudo FROM users WHERE id='"+id+"';");
			
			if(resultat.next() == false) throw new DaoException("User is not in database");
			else {
				user.setId(resultat.getInt("id"));
				user.setPseudo(resultat.getString("pseudo"));
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
			ResultSet resultat = statement.executeQuery("SELECT id, pseudo FROM users;");
			
			while(resultat.next()) {
				long id = resultat.getInt("id");
				String pseudo = resultat.getString("pseudo");
				
				User user = new User();
				user.setId(id);
				user.setPseudo(pseudo);
				
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
				long id = resultat.getInt("id");
				String pseudo = resultat.getString("pseudo");
				
				User user = new User();
				user.setId(id);
				user.setPseudo(pseudo);
				
				users.add(user);
			}
		} catch(SQLException e) {
		}
		
		return users;
	}
	
	public void addUser(User user) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnection();
			
			preparedStatement = connexion.prepareStatement("INSERT INTO users(pseudo, password) VALUES(?, SHA1(?));");
			preparedStatement.setString(1, user.getPseudo());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUser(User user) {
		try {
			Connection connexion = daoFactory.getConnection();
			
			PreparedStatement preparedStatement = connexion.prepareStatement("DELETE FROM users WHERE pseudo=? AND password=SHA1(?);");
			preparedStatement.setString(1, user.getPseudo());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(User user, String pseudo) {
		try {
			Connection connexion = daoFactory.getConnection();
			
			PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE users SET pseudo=? WHERE pseudo=? AND password=SHA1(?);");
			preparedStatement.setString(2, user.getPseudo());
			preparedStatement.setString(3,user.getPassword());
			preparedStatement.setString(1,pseudo);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePassword(User user,String passwd) {
		
		try {
			Connection connexion = daoFactory.getConnection();
			
			PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE users SET password=SHA1(?) WHERE pseudo=? AND password=SHA1(?);");
			preparedStatement.setString(2, user.getPseudo());
			preparedStatement.setString(3,user.getPassword());
			preparedStatement.setString(1,passwd);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
