package listeners;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import dao.DaoFactory;

@WebListener
public class InitialisationDaoFactory implements ServletContextListener {

	private DaoFactory daoFactory;
	
    public void contextInitialized(ServletContextEvent arg0)  { 
    	ServletContext servletContext = arg0.getServletContext();
    	
		this.daoFactory = DaoFactory.getInstance();
		servletContext.setAttribute( "DaoFactory" , this.daoFactory );
    }
    
    public void contextDestroyed(ServletContextEvent arg0)  { 
    }
}
