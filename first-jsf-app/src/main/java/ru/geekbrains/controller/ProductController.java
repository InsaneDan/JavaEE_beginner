package ru.geekbrains.controller;

import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @Inject
    private ProductRepository productRepository;

    private Product product;

    private List<Product> products;

    // предзагрузка списка элементов сущности, чтобы не обращаться несколько раз к базе при загрузке страницы
    public void getData(ComponentSystemEvent cse) {
//        this.products = productRepository.findAllNamedQuery();
//        this.products = productRepository.findAllJpql();
        this.products = productRepository.findAllProducts();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String createProduct() {
        this.product = new Product();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public List<Product> getAllProducts() {
        return products; // возвращаем предварительно загруженный список элементов
    }

    public String editProduct(Product product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Product product) {
        productRepository.deleteById(product.getId());
    }

    public String saveProduct() {
        productRepository.saveOrUpdate(product);
        return "/product.xhtml?faces-redirect=true";
    }

    // используем Method Binding
    public void changeCategory(ValueChangeEvent e) {
        String newValue = (String) e.getNewValue();
        Category newCategory = productRepository.getCategoryForProduct(Long.valueOf(newValue));
        product.setCategory(newCategory);
    }
}
