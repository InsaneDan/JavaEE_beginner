package ru.geekbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/page_header")
public class PageHeaderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String pageHeaderLvl = (String) req.getAttribute("pageHeaderLvl");  // уровень заголовка (по сути - тэг)
        String header = (String) req.getAttribute("pageHeader");            // текст заголовка

        resp.getWriter().println("<" + pageHeaderLvl + ">" + header + "</" + pageHeaderLvl + ">"); // вывод заголовка

        resp.getWriter().println("<link rel=\"stylesheet\" type =\"text/css\" href=\"" + req.getContextPath() + "/style.css\">");

        // ссылки в виде таблицы
        resp.getWriter().println("<table><tr>");
        resp.getWriter().println("<td><a href='" + getServletContext().getContextPath() + "/http-servlet'>Main</a></td>");
        resp.getWriter().println("<td><a href='" + getServletContext().getContextPath() + "/catalog'>Catalog</a></td>");
        resp.getWriter().println("<td><a href='" + getServletContext().getContextPath() + "/product'>Product</a></td>");
        resp.getWriter().println("<td><a href='" + getServletContext().getContextPath() + "/cart'>Cart</a></td>");
        resp.getWriter().println("<td><a href='" + getServletContext().getContextPath() + "/order'>Order</a></td>");

        // неверная ссылка (error-page в web.xml привязан )
        resp.getWriter().println("<td><a href='" + getServletContext().getContextPath() + "/err404'>Неверная ссылка (Error page 404)</a></td>");

        //        доступа пока ни к чему нет, нечего запрещать
//        resp.getWriter().println("<td><a href='" + req.getContextPath() + "/err403'>Error page 403</a></td>");

        resp.getWriter().println("<hr/>");
        resp.getWriter().println("</tr></table>");


    }
}
