package ua.goIt.servlet.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ua.goIt.DbStatement;
import ua.goIt.services.consoleService.DeveloperService;
import ua.goIt.services.webService.CompanyWebService;
import ua.goIt.services.webService.CustomerWebService;
import ua.goIt.services.webService.DeveloperWebService;
import ua.goIt.services.webService.ProjectWebService;


@WebListener
public class LoadListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DbStatement.createConnection();
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("developerService", DeveloperWebService.getInstance());
        servletContext.setAttribute("companyWebService", CompanyWebService.getInstance());
        servletContext.setAttribute("customerWebService", CustomerWebService.getInstance());
        servletContext.setAttribute("projectWebService", ProjectWebService.getInstance());
    }
}
