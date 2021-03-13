package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.service.product.ProductRepr;
import ru.geekbrains.service.product.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @EJB
    private CategoryRepository categoryRepository;

    @EJB
    private ProductService productService;

    private ProductRepr product;

    private List<ProductRepr> products;

    private List<Category> categories;

    public void getData(ComponentSystemEvent cse) {
        logger.info("getData for categoryRepository");
        categories = categoryRepository.findAll();
        logger.info("getData for productService");
        products = productService.findAll();
    }

    public ProductRepr getProduct() {
        logger.info("getProduct");
        return product;
    }

    public void setProduct(ProductRepr product) {
        logger.info("setProduct");
        this.product = product;
    }

    public String createProduct() {
        logger.info("createProduct");
        this.product = new ProductRepr();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public List<ProductRepr> getAllProducts() {
        logger.info("getAllProducts");
        return products;
    }

    public String editProduct(ProductRepr product) {
        logger.info("editProduct");
        this.product = product;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(ProductRepr product) {
        logger.info("deleteProduct");
        productService.deleteById(product.getId());
    }

    public String saveProduct() {
        logger.info("saveProduct");
        productService.saveOrUpdate(product);
        return "/product.xhtml?faces-redirect=true";
    }

    public List<Category> getCategories() {
        logger.info("getCategories");
        return categories;
    }

    public void setCategories(List<Category> categories) {
        logger.info("setCategories");
        this.categories = categories;
    }

}
