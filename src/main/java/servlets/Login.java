package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import dao.DaoFactory;
import dao.UtilisateurDao;
import forms.ConnectionForm;
import forms.ConnectionFormException;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UtilisateurDao utilisateurDao;

    public Login() {
        super();
    }
    
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("currentUser") == null) this.getServletContext().getRequestDispatcher("/WEB-INF/loginPage.jsp").forward(request, response);
		else response.sendRedirect( request.getContextPath() );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ConnectionForm connectionForm = new ConnectionForm();
		try {
			connectionForm.verifierId(request, utilisateurDao);
			
			//	Ajout dans un objet User
			User currentUser = new User();
			currentUser.setPseudo(connectionForm.getLog());
			
			//	Ajout de User dans une variable de session
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", currentUser);
			
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			
		}catch(ConnectionFormException e) {
			request.setAttribute("errorConnectionForm", e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/loginPage.jsp").forward(request, response);
		}
	}
}
