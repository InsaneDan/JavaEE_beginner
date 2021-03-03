package ru.geekbrains.shop.plain;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/cart/plain")
public class Cart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("pageHeader", "КОРЗИНА ЗАКАЗОВ");
        req.setAttribute("pageHeaderLvl", "h2");
        getServletContext().getRequestDispatcher("/page_header").include(req, resp);

        resp.getWriter().println("<hr/>");
        resp.getWriter().println("<p>Здесь будет информация заказанных товарах (Cart)</p>");

        getServletContext().getRequestDispatcher("/footer.html").include(req, resp);

    }
}
