package ru.geekbrains.listener;

import ru.geekbrains.persist.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // репозиторий продуктов
        ProductRepository productRepository = new ProductRepository();

        productRepository.saveOrUpdate(new Product(null, "Product  1",
                "Description of product 1", new BigDecimal(100)));
        productRepository.saveOrUpdate(new Product(null, "Product  2",
                "Description of product 2", new BigDecimal(200)));
        productRepository.saveOrUpdate(new Product(null, "Product  3",
                "Description of product 3", new BigDecimal(200)));

        sce.getServletContext().setAttribute("productRepository", productRepository);

        // репозиторий категорий
        CategoryRepository categoryRepository = new CategoryRepository();

        categoryRepository.saveOrUpdate(new Category(null, "Category  1",
                "Description of category 1"));
        categoryRepository.saveOrUpdate(new Category(null, "Category  2",
                "Description of category 2"));
        categoryRepository.saveOrUpdate(new Category(null, "Category  3",
                "Description of category 3"));

        sce.getServletContext().setAttribute("categoryRepository", categoryRepository);

        // репозиторий пользователей
        UserRepository userRepository = new UserRepository();

        userRepository.saveOrUpdate(new User(null, "Jhon", "Doe",
                "jhon_doe@mail.com", "jhondoeLogin", "jhondoePsw"));
        userRepository.saveOrUpdate(new User(null, "Agent", "Smith",
                "agent_smith@mail.com", "agentSmithLogin", "agentSmithPsw"));

        sce.getServletContext().setAttribute("userRepository", userRepository);
    }
}
