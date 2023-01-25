package forms;

import javax.servlet.http.HttpServletRequest;
import dao.UtilisateurDao;

public class ConnectionForm {
	/* Objet métier pour faire les vérifications du formulaire de connexion */
	
	private String log;
	
	public void verifierId(HttpServletRequest request, UtilisateurDao utilisateurs) throws ConnectionFormException {
		String login = request.getParameter("log");
		String passwd = request.getParameter("passwd");
		
		if(!utilisateurs.isUserInDB(login, passwd)) throw new ConnectionFormException("Mauvais identifiant ou mot de passe.");
		else { log = login; }
	}

	public String getLog() {
		return log;
	}
}
