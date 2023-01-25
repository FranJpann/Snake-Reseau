package forms;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.User;
import dao.UtilisateurDao;

public class ConnectionForm {
	/* Objet métier pour faire les vérifications du formulaire de connexion */
	
	private String log;
	
	public void verifierId(HttpServletRequest request, UtilisateurDao utilisateurs) throws ConnectionFormException {
		String login = request.getParameter("log");
		String passwd = request.getParameter("passwd");
		
		ArrayList<User> listUtilisateurs = (ArrayList<User>) utilisateurs.getUsers();
		boolean goodConnectionParam = false;
		
		for(User user : listUtilisateurs)
			if(user.getPseudo().equals(login) && user.getPassword().equals(passwd)) goodConnectionParam = true;
		
		if(!goodConnectionParam) throw new ConnectionFormException("Mauvais identifiant ou mot de passe.");
		else { log = login; }
	}

	public String getLog() {
		return log;
	}
}
