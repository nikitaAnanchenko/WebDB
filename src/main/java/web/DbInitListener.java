package web;

import org.postgresql.ds.PGPoolingDataSource;
import dao.PositionsDAO;
import dao.SellerDAO;
import dao.BookDAO;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class DbInitListener implements ServletContextListener {

    public DbInitListener() {
    }


    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Initializing data source connection");

        PGPoolingDataSource poolingDataSource = new PGPoolingDataSource();
        poolingDataSource.setDataSourceName("Lab 5 data source");

        poolingDataSource.setServerName("localhost");
        poolingDataSource.setDatabaseName("BookShop");
        poolingDataSource.setUser("postgres");
        poolingDataSource.setPassword("admin");
        poolingDataSource.setMaxConnections(8);
        poolingDataSource.setInitialConnections(1);

        BookDAO bookDAO = new BookDAO(poolingDataSource);
        PositionsDAO positionDAO = new PositionsDAO(poolingDataSource);
        SellerDAO sellerDAO = new SellerDAO(poolingDataSource);

        sce.getServletContext().setAttribute("bookDAO", bookDAO);
        sce.getServletContext().setAttribute("positionDAO", positionDAO);
        sce.getServletContext().setAttribute("sellerDAO", sellerDAO);

        sce.getServletContext().setAttribute("datasource", poolingDataSource);

        sce.getServletContext().log("Initialized all DAOs");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().log("Closing connections pool");

        PGPoolingDataSource poolingDataSource = (PGPoolingDataSource) sce.getServletContext().getAttribute("datasource");
        poolingDataSource.close();

    }
}

