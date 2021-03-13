package ru.geekbrains.service.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Category;

import java.io.Serializable;

// DTO
public class CategoryRepr implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepr.class);

    private Long id;

    private String name;

    private String description;

    public CategoryRepr() {
        logger.info("Default constructor");
    }

    public CategoryRepr(Category category) {
        logger.info("Constructor with parameters");
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();

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

}
