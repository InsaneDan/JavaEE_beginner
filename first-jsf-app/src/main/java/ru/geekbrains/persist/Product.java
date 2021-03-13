package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.service.ProductRepr;
import ru.geekbrains.service.ProductServiceImpl;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "FROM Product ORDER BY id"),
        @NamedQuery(name = "countAllProducts", query = "SELECT COUNT(*) FROM Product"),
        @NamedQuery(name = "deleteProductById", query = "DELETE FROM Product p WHERE p.id = :id")
})
public class Product {

    private static final Logger logger = LoggerFactory.getLogger(Product.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1024, nullable = false)
    private String description;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=10, fraction=2)
    private BigDecimal price;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "id")
    private Category category;

    public Product() {
        logger.info("Default constructor");
    }

    public Product(Long id, String name, String description, BigDecimal price) {
        logger.info("Constructor with parameters");
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(ProductRepr productRepr, Category category) {
        this(productRepr.getId(), productRepr.getName(), productRepr.getDescription(), productRepr.getPrice());
        this.category = category;
        logger.info("Constructor with productRepr");
    }

    public Long getId() { return id; }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        logger.info("getCategory");
        return category;
    }

    public void setCategory(Category category) {
        logger.info("setCategory");
        this.category = category;
    }
}
