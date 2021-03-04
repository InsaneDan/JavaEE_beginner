package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CategoryController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Inject
    private CategoryRepository categoryRepository;

    private Category category;

    private List<Category> categories;

    public void getData(ComponentSystemEvent cse) {
        logger.info("getData");
        this.categories = categoryRepository.findAll();
    }

    public Category getCategory() {
        logger.info("getCategory");
        return category;
    }

    public void setCategory(Category category) {
        logger.info("setCategory");
        this.category = category;
    }

    public String createCategory() {
        logger.info("createCategory");
        this.category = new Category();
        return "/catalog_form.xhtml?faces-redirect=true";
    }

    public List<Category> getAllCategories() {
        logger.info("getAllCategories");
        return categories; // возвращаем предварительно загруженный список элементов
    }

    public String editCategory(Category category) {
        logger.info("editCategory");
        this.category = category;
        return "/catalog_form.xhtml?faces-redirect=true";
    }

    public void deleteCategory(Category category) {
        logger.info("deleteCategory");
        categoryRepository.deleteById(category.getId());
    }

    public String saveCategory() {
        logger.info("saveCategory");
        categoryRepository.saveOrUpdate(category);
        return "/catalog.xhtml?faces-redirect=true";
    }
}
