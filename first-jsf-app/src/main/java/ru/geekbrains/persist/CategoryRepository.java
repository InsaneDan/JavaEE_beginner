package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.io.Serializable;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryRepository implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {
            try {
                ut.begin();

                saveOrUpdate(new Category(null, "Category  1",
                        "Description of category 1"));
                saveOrUpdate(new Category(null, "Category  2",
                        "Description of category 2"));
                saveOrUpdate(new Category(null, "Category  3",
                        "Description of category 3"));

                ut.commit();
            } catch (Exception ex) {
                logger.error("", ex);
                ut.rollback();
            }
        }
    }

    public List<Category> findAll() {
        return em.createNamedQuery("findAllCategories", Category.class).getResultList();
    }

    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

    public Long countAll() {
        return em.createNamedQuery("countAllCategories", Long.class).getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(Category category) {
        if (category.getId() == null) {
            em.persist(category);
        }
        em.merge(category);
    }

    @Transactional
    public void deleteById(Long id) {
        em.createNamedQuery("deleteCategoryById").setParameter("id", id).executeUpdate();
    }
}
