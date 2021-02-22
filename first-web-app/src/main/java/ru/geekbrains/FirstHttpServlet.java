package ru.geekbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(urlPatterns = {"", "/http-servlet/*"})
public class FirstHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // можно было бы создавать объект с параметрами заоловка, включая текст, уровень, размер, шрифт и др.
        // но судя по методичке к следующему уроку, подозреваю, что это не имеет большого смысла

        req.setAttribute("pageHeader", "Главная страница (first-http-servlet)");  // текст заголовка
        req.setAttribute("pageHeaderLvl", "h2");                    // уровень заголовка
        getServletContext().getRequestDispatcher("/page_header").include(req, resp);

        resp.getWriter().println("<p>contextPath: " + req.getContextPath() + "</p>");
        resp.getWriter().println("<p>servletPath: " + req.getServletPath() + "</p>");
        resp.getWriter().println("<p>pathInfo: " + req.getPathInfo() + "</p>");
        resp.getWriter().println("<p>queryString: " + req.getQueryString() + "</p>");
        resp.getWriter().println("<p>param1: " + req.getParameter("param1") + "</p>");
        resp.getWriter().println("<p>param2: " + req.getParameter("param2") + "</p>");
        resp.getWriter().println("<p>User-Agent header: " + req.getHeader("User-Agent") + "</p>");

//        resp.setHeader("Some-Header", "value");

        HttpSession session = req.getSession(true);
        Integer counter = (Integer) session.getAttribute("counter");
        if (counter == null) {
            counter = 1;
        } else {
            counter++;
        }
        session.setAttribute("counter", counter);
        resp.getWriter().println("<p>Счетчик запросов в сессии " + counter + "</p>");

        if (Arrays.stream(req.getCookies()).noneMatch(c -> c.getName().equals("My-Cookie"))) {
            Cookie cookie = new Cookie("My-Cookie", "Some value");
            resp.addCookie(cookie);
        }

        resp.getWriter().println("<hr/>");

//        getServletContext().getRequestDispatcher("/footer.html").include(req, resp);    // футер из html-файла
    }
}
