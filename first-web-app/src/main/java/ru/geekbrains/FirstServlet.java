package ru.geekbrains;

import javax.servlet.*;
import java.io.IOException;
//import java.util.concurrent.atomic.AtomicInteger;

public class FirstServlet implements Servlet {

    private transient ServletConfig config;

//    private final AtomicInteger requestCounter = new AtomicInteger();

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        req.setAttribute("pageHeader", "First-Servlet");  // текст заголовка
        req.setAttribute("pageHeaderLvl", "h1");                    // уровень заголовка
        getServletConfig().getServletContext().getRequestDispatcher("/page_header").include(req, res);

        res.getWriter().println("<h1>Hello from Servlet!!!</h1>");
    }

    @Override
    public String getServletInfo() {
        return "FirstServlet";
    }

    @Override
    public void destroy() {

    }
}
