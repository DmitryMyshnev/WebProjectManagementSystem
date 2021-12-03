package ua.goIt.servlet.company;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.model.Company;
import ua.goIt.services.webService.CompanyWebService;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet("/companies")
public class CompanyServlet extends HttpServlet {
    public static final Logger LOGGER = LogManager.getLogger(CompanyServlet.class);
    private CompanyWebService companyWebService;
    @Override
    public void init() throws ServletException {
        companyWebService = (CompanyWebService) getServletContext().getAttribute("companyWebService");

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object[] company = companyWebService.getAll().stream().map(Company.class::cast).sorted(Comparator.comparing(Company::getId)).toArray();
        req.setAttribute("companies", company);
        req.getRequestDispatcher("/companies.jsp").include(req, resp);
    }
}
