package ru.darvell.gb.spring.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.darvell.gb.spring.models.Product;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class ProductListServlet implements Servlet {

    private static Logger logger = LoggerFactory.getLogger(ProductListServlet.class);

    private ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        for (Product p : generateProducts()) servletResponse.getWriter().println("<h1>" + p + "<h1>");
    }

    private List<Product> generateProducts() {
        List<Product> products = new LinkedList<>();
        products.add(new Product(1, "IBM 090: Hollerith Type I Tabulator", BigDecimal.valueOf(10000)));
        products.add(new Product(2, "IBM 091: Hollerith Type III Tabulator", BigDecimal.valueOf(2000)));
        products.add(new Product(3, "IBM 092: Electric Tabulating Machine", BigDecimal.valueOf(40000.99)));
        products.add(new Product(4, "IBM 093: Automatic Control Tabulator", BigDecimal.valueOf(1000)));
        products.add(new Product(5, "Hollerith Type 3-S Tabulator", BigDecimal.valueOf(500)));
        products.add(new Product(6, "IBM 211: Accounting Machine", BigDecimal.valueOf(10000)));
        products.add(new Product(7, "IBM 212: Accounting Machine", BigDecimal.valueOf(10500)));
        products.add(new Product(8, "IBM 297: Numerical Accounting Machine", BigDecimal.valueOf(10550)));
        products.add(new Product(9, "IBM 298: Numerical Accounting Machine", BigDecimal.valueOf(10000.11)));
        products.add(new Product(10, "IBM 401: Tabulator", BigDecimal.valueOf(9000)));
        return products;
    }

    @Override
    public String getServletInfo() {
        return "ProductListServlet";
    }

    @Override
    public void destroy() {
        logger.info("Servlet {} destroyed", getServletInfo());
    }
}
