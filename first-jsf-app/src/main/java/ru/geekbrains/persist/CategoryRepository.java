package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
public class CategoryRepository implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public List<Category> findAll() {
        logger.info("findAll");
        return em.createNamedQuery("findAllCategories", Category.class).getResultList();
    }

    public Category getReference(Long id) {
        logger.info("getReference");
        return em.getReference(Category.class, id);
    }

    public Category findById(Long id) {
        logger.info("findById");
        return em.find(Category.class, id);
    }

    public Long countAll() {
        logger.info("countAll");
        return em.createNamedQuery("countAllCategories", Long.class).getSingleResult();
    }

    public void saveOrUpdate(Category category) {
        logger.info("saveOrUpdate");
        if (category.getId() == null) {
            em.persist(category);
        }
        em.merge(category);
    }

    public void deleteById(Long id) {
        logger.info("deleteById");
        em.createNamedQuery("deleteCategoryById").setParameter("id", id).executeUpdate();
    }

}
