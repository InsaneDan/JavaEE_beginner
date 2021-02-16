package ru.geekbrains.shop;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/http-servlet/product")
public class Product extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("pageHeader", "ОПИСАНИЕ ТОВАРА");
        req.setAttribute("pageHeaderLvl", "h2");
        getServletContext().getRequestDispatcher("/page_header").include(req, resp);

        resp.getWriter().println("<p>Здесь будет информация о товаре (product)</p>");

        getServletContext().getRequestDispatcher("/footer.html").include(req, resp);
    }
}