package ru.geekbrains.persist;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "FROM Product ORDER BY productName"),
        @NamedQuery(name = "countAllProducts", query = "SELECT COUNT(*) FROM Product"),
        @NamedQuery(name = "deleteProductById", query = "DELETE FROM Product p WHERE p.id = :id")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(length = 1024, nullable = false)
    private String productDescription;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=10, fraction=2)
    private BigDecimal price;

    @ManyToOne
    private Category category;
//    @Column(nullable = false)
//    private Long categoryId;

    public Product() {
    }

    public Product(Long id, String productName, String productDescription, BigDecimal price) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
//        this.categoryId = categoryId;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String name) {
        this.productName = name;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String description) {
        this.productDescription = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

//    public Long getCategoryId() { return categoryId; }
//
//    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
