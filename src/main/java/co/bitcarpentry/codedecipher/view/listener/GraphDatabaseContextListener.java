package co.bitcarpentry.codedecipher.view.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import co.bitcarpentry.codedecipher.logic.GraphDatabaseFacade;

/**
 * Application Lifecycle Listener implementation class GraphDatabaseContextListener
 *
 */
public class GraphDatabaseContextListener implements ServletContextListener {

	private GraphDatabaseFacade graphDatabaseFacade;
	
    /**
     * Default constructor. 
     */
    public GraphDatabaseContextListener() {
        super();
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         this.graphDatabaseFacade.shutdown();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         this.graphDatabaseFacade = GraphDatabaseFacade.getInstance();
    }
	
}
