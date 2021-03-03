package ru.geekbrains.persist;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@NamedQueries({
        @NamedQuery(name = "findAllCategories", query = "FROM Category ORDER BY categoryName"),
        @NamedQuery(name = "countAllCategories", query = "SELECT COUNT(*) FROM Category"),
        @NamedQuery(name = "deleteCategoryById", query = "DELETE FROM Category c WHERE c.id = :id")
})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String categoryName;

    @Column(length = 1024)
    private String categoryDescription;

    @OneToMany(targetEntity=Product.class, cascade=CascadeType.PERSIST, mappedBy="category")
    public List<Product> products;

    public Category() {}

    public Category(Long id, String categoryName, String categoryDescription) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String name) {
        this.categoryName = name;
    }

    public String getCategoryDescription() { return categoryDescription; }

    public void setCategoryDescription(String description) { this.categoryDescription = description; }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
