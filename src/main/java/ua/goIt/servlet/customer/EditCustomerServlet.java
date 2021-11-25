package ua.goIt.servlet.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goIt.model.Company;
import ua.goIt.model.Customer;
import ua.goIt.services.HandleBodyUtil;
import ua.goIt.services.webService.CompanyWebService;
import ua.goIt.services.webService.CustomerWebService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/customer/edit/*")
public class EditCustomerServlet extends HttpServlet {
    private CustomerWebService customerWebService;

    @Override
    public void init() throws ServletException {
        customerWebService = (CustomerWebService)getServletContext().getAttribute("customerWebService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] requestURI = req.getRequestURI().split("/");
        String id = requestURI[requestURI.length-1];
        Optional<Customer> cmt = customerWebService.findById(Long.parseLong(id)).stream().map(Customer.class::cast).findFirst();
        if (cmt.isPresent()) {
            Customer customer = cmt.get();
            req.setAttribute("customer", customer);
            req.getRequestDispatcher("/customer.jsp").forward(req, resp);
        }
        resp.sendRedirect("/customers");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), Customer.class)
                .ifPresent(customer -> customerWebService.update(customer));
        resp.sendRedirect("/customers");
    }
}
