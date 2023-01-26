package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.DaoException;
import dao.DaoFactory;
import dao.UtilisateurDao;

public class UserPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UtilisateurDao utilisateurDao;
	
	public void init() throws ServletException {
		this.utilisateurDao = ((DaoFactory) this.getServletContext().getAttribute("DaoFactory")).getUtilisateurDao();
    }
       
    public UserPage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong((String) request.getParameter("id"));
		
		try {
			User user = utilisateurDao.getUserByID(id);
			request.setAttribute("userRequest", user);
			this.getServletContext().getRequestDispatcher("/WEB-INF/userPage.jsp").forward(request, response);
		}catch(DaoException e) {
			response.sendRedirect(request.getContextPath());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
