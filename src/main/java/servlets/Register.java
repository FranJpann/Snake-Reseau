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
import forms.RegisterForm;
import forms.RegisterFormException;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UtilisateurDao utilisateurDao;
	
	public void init() throws ServletException {
		this.utilisateurDao = ((DaoFactory) this.getServletContext().getAttribute("DaoFactory")).getUtilisateurDao();
    }
       
    public Register() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("currentUser") == null) this.getServletContext().getRequestDispatcher("/WEB-INF/registerPage.jsp").forward(request, response);
		else response.sendRedirect( request.getContextPath() );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegisterForm registerForm = new RegisterForm();
		
		try {
			registerForm.verifierId(request, utilisateurDao);
			
			User user = new User();
			user.setPseudo(request.getParameter("login"));
			
			utilisateurDao.addUser(user, request.getParameter("password"));
			this.getServletContext().getRequestDispatcher("/WEB-INF/loginPage.jsp").forward(request, response);
		} catch (RegisterFormException e) {
			request.setAttribute("errorRegisterForm", e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/registerPage.jsp").forward(request, response);
		}
	}
}
