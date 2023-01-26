package forms;

import javax.servlet.http.HttpServletRequest;

import beans.User;
import dao.DaoException;
import dao.UtilisateurDao;

public class ConnectionForm {
	/* Objet métier pour faire les vérifications du formulaire de connexion */
	
	private User user;
	
	public void verifierId(HttpServletRequest request, UtilisateurDao utilisateurs) throws ConnectionFormException {
		String login = request.getParameter("log");
		String passwd = request.getParameter("passwd");
		
		try {
			user = utilisateurs.getUserByPseudoPassword(login, passwd);
		}
		catch(DaoException e) {
			throw new ConnectionFormException("Mauvais identifiant ou mot de passe.");
		}
	}

	public User getUser() {
		return user;
	}
}
