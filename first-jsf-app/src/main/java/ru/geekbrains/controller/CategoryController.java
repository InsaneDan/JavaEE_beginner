package ru.geekbrains.controller;

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

    @Inject
    private CategoryRepository categoryRepository;

    private Category category;

    private List<Category> categories;

    // предзагрузка списка элементов сущности, чтобы не обращаться несколько раз к базе при загрузке страницы
    public void getData(ComponentSystemEvent cse) {
        this.categories = categoryRepository.findAll();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String createCategory() {
        this.category = new Category();
        return "/catalog_form.xhtml?faces-redirect=true";
    }

    public List<Category> getAllCategories() {
        return categories; // возвращаем предварительно загруженный список элементов
    }

    public String editCategory(Category category) {
        this.category = category;
        return "/catalog_form.xhtml?faces-redirect=true";
    }

    public void deleteCategory(Category category) {
        categoryRepository.deleteById(category.getId());
    }

    public String saveCategory() {
        categoryRepository.saveOrUpdate(category);
        return "/catalog.xhtml?faces-redirect=true";
    }
}
