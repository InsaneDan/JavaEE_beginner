package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.service.CategoryRepr;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@NamedQueries({
        @NamedQuery(name = "findAllCategories", query = "FROM Category ORDER BY name"),
//        @NamedQuery(name = "countAllCategories", query = "SELECT COUNT(*) FROM Category"),
        @NamedQuery(name = "deleteCategoryById", query = "DELETE FROM Category c WHERE c.id = :id")
})
public class Category {

    private static final Logger logger = LoggerFactory.getLogger(Category.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(length = 1024)
    private String description;

    @OneToMany(targetEntity=Product.class, cascade=CascadeType.PERSIST, mappedBy="category")
    public List<Product> products;

    public Category() {
        logger.info("Default constructor");
    }

    public Category(Long id, String name, String description) {
        logger.info("Constructor with parameters");
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category(CategoryRepr categoryRepr) {
        logger.info("Constructor with parameters");
        this.id = categoryRepr.getId();
        this.name = categoryRepr.getName();
        this.description = categoryRepr.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
