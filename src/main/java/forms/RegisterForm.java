package forms;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.User;
import dao.UtilisateurDao;

public class RegisterForm {
	/* Objet métier pour faire les vérifications du formulaire d'enregistrement */
	
	public void verifierId(HttpServletRequest request, UtilisateurDao utilisateurs) throws RegisterFormException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String passwordConfirmation = request.getParameter("passwordConfirmation");
		
		ArrayList<User> listUtilisateurs = (ArrayList<User>) utilisateurs.getUsers();
		
		for(User user : listUtilisateurs)
			if(user.getPseudo().equals(login)) throw new RegisterFormException("Pseudo déjà existant");
		
		if(login.length() > 10) throw new RegisterFormException("Login trop long, supérieur à 10 caractères.");
		else if(!password.equals(passwordConfirmation)) throw new RegisterFormException("Les mots de passes ne correspondent pas.");
	}
}
