package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ShopBean;
import beans.User;
import dao.DaoFactory;
import dao.UtilisateurDao;
import forms.BuySkinForm;

@WebServlet("/Shop")
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UtilisateurDao utilisateurDao;
	private ShopBean shop;
    
    public void init() throws ServletException {
        this.utilisateurDao = ((DaoFactory) this.getServletContext().getAttribute("DaoFactory")).getUtilisateurDao();
        shop = new ShopBean();
    }
	
    public Shop() {
        super();
    }
    
    public HashMap<String, Integer> getBuyableSkins(ArrayList<String> userSkins, HashMap<String, Integer> shopSkins){
    	HashMap<String, Integer> temp = new HashMap<>();
    	for(String skin: shopSkins.keySet()) {
    		if(!userSkins.contains(skin))
    			temp.put(skin, shopSkins.get(skin));
    	}
    	return temp;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User sessionUser = (User) session.getAttribute("currentUser");
		
		if(sessionUser != null) {
			request.setAttribute("credit", utilisateurDao.getCredit(sessionUser.getPseudo()));
			request.setAttribute("skinsUser", utilisateurDao.getSkins(sessionUser.getPseudo()));
			request.setAttribute("skinsBuyable", getBuyableSkins(utilisateurDao.getSkins(sessionUser.getPseudo()), shop.getSkins()));
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/shop.jsp").forward(request, response);
		}
		else response.sendRedirect( request.getContextPath() );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User sessionUser = (User) session.getAttribute("currentUser");
		
		int creditFromShop = shop.getSkins().get(request.getParameter("buttonBuySkin"));
		System.out.println(creditFromShop);
		
		if(BuySkinForm.canBuySkin(creditFromShop , utilisateurDao.getCredit(sessionUser.getPseudo()))) {
			utilisateurDao.addCredit(sessionUser.getPseudo(), -creditFromShop);
			utilisateurDao.addSkinToCollection(sessionUser, request.getParameter("buttonBuySkin"));
		}
		else System.out.println("nn");
			
		doGet(request, response);
	}

}
