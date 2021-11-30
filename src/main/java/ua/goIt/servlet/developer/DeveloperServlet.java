package ua.goIt.servlet.developer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goIt.model.Developer;
import ua.goIt.model.DeveloperVsSkill;
import ua.goIt.model.Skill;
import ua.goIt.services.consoleService.DeveloperService;
import ua.goIt.services.webService.DeveloperWebService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/developers")
public class DeveloperServlet extends HttpServlet {
    private DeveloperWebService developerWebService;

    @Override
    public void init() throws ServletException {
        developerWebService = (DeveloperWebService) getServletContext().getAttribute("developerService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object[] dev = developerWebService.getAll().stream().map(Developer.class::cast).sorted(Comparator.comparing(Developer::getId)).toArray();
        req.setAttribute("developers", dev);
        req.getRequestDispatcher("developers.jsp").include(req, resp);
    }
}
