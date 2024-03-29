package api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import beans.User;
import dao.DaoException;
import dao.DaoFactory;
import dao.UtilisateurDao;

@WebServlet("/api/user")
public class ApiUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UtilisateurDao utilisateurDao;
	
	public void init() throws ServletException {
		this.utilisateurDao = ((DaoFactory) this.getServletContext().getAttribute("DaoFactory")).getUtilisateurDao();
    }
	
	private void sendJson(HttpServletResponse response, Object obj) throws IOException {
		response.setContentType("application/json");
		
		JSONObject jsonObj = new JSONObject(obj);
		PrintWriter out = response.getWriter();
		out.print(jsonObj);
		out.flush();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = (String) request.getParameter("pseudo");
		String motdepasse = (String) request.getParameter("motdepasse");
		
		try {
			User user = utilisateurDao.getUserByPseudoPassword(pseudo, motdepasse);
			sendJson(response, user);
		} catch (DaoException e) {
			System.out.println("Requête -> "+e.getMessage());
		}
	}

}
