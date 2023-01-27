package filters;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import dao.DaoFactory;

public class CheckAccessDatabase implements Filter {


    public CheckAccessDatabase() {
    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		DaoFactory factory = (DaoFactory) request.getServletContext().getAttribute("DaoFactory");

		try {
			factory.getConnection();
			chain.doFilter(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.getServletContext().getRequestDispatcher("/WEB-INF/databaseNoAccess.jsp").forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
