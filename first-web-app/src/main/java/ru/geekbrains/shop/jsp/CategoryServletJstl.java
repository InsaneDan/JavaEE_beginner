package ru.geekbrains.shop.jsp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/catalog/*")
public class CategoryServletJstl extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServletJstl.class);

    private CategoryRepository categoryRepository;

    @Override
    public void init() throws ServletException {
        this.categoryRepository = (CategoryRepository) getServletContext().getAttribute("categoryRepository");
        if (categoryRepository == null) {
            throw new ServletException("CategoryRepository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(req.getPathInfo());
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("categories", categoryRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/catalog.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/edit")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                resp.sendError(400);
                return;
            }
            Category category = categoryRepository.findById(id);
            if (category == null) {
                resp.setStatus(404);
                return;
            }
            req.setAttribute("category", category);
            getServletContext().getRequestDispatcher("/WEB-INF/catalog_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals(("/new"))) {
            req.setAttribute("category", new Category());
            getServletContext().getRequestDispatcher("/WEB-INF/catalog_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/delete")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                resp.sendError(400);
                return;
            }
            categoryRepository.deleteById(id);
            resp.sendRedirect(getServletContext().getContextPath() + "/catalog");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            String strId = req.getParameter("id");
            if (strId != null && !strId.isBlank()) {
                id = Long.parseLong(req.getParameter("id"));
            }
        } catch (NumberFormatException ex) {
            resp.setStatus(400);
            resp.sendError(400);
            return;
        }
        Category category = new Category(id, req.getParameter("name"), req.getParameter("description"));
        categoryRepository.saveOrUpdate(category);
        resp.sendRedirect(getServletContext().getContextPath() + "/catalog");
    }
}
