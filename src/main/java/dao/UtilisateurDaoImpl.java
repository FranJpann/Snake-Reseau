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
    
    public boolean isUserInDB(String pseudo, String password) {
    	//Connexion à la base
    	Connection connexion = null;
    	Statement statement = null;
    	ResultSet resultat = null;
    	
    	Boolean isInDB = false;
    	
    	try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT id, pseudo FROM users WHERE pseudo='"+ pseudo + "' && password=MD5('"+ password +"');");
			
			
			if(resultat.next() == true) {
				isInDB = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return isInDB;
    }
    
    public List<User> getUsers(){
		List<User> users = new ArrayList<User>();
		
		//Connexion à la base
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT id, pseudo FROM users;");
			
			while(resultat.next()) {
				long id = resultat.getInt("id");
				String pseudo = resultat.getString("pseudo");
				
				User user = new User();
				user.setId(id);
				user.setPseudo(pseudo);
				
				users.add(user);
			}
		} catch(SQLException e) {
		} finally {
			try {
				if(resultat != null) resultat.close();
				if(statement != null) statement.close();
				if(connexion != null) connexion.close();
			} catch(SQLException ignore) {
			}
		}
		return users;
	}
    
    public List<User> getLastUsers(){
		List<User> users = new ArrayList<User>();
		
		//Connexion à la base
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM users ORDER BY id DESC LIMIT 5;");
			
			while(resultat.next()) {
				long id = resultat.getInt("id");
				String pseudo = resultat.getString("pseudo");
				
				User user = new User();
				user.setId(id);
				user.setPseudo(pseudo);
				
				users.add(user);
			}
		} catch(SQLException e) {
		} finally {
			try {
				if(resultat != null) resultat.close();
				if(statement != null) statement.close();
				if(connexion != null) connexion.close();
			} catch(SQLException ignore) {
			}
		}
		return users;
	}
	
	public void addUser(User user) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnection();
			
			preparedStatement = connexion.prepareStatement("INSERT INTO users(pseudo, password) VALUES(?, MD5(?));");
			preparedStatement.setString(1, user.getPseudo());
			preparedStatement.setString(2, user.getPassword());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
