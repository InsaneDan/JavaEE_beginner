package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
public class ProductRepository implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public List<Product> findAll() {
        logger.info("findAll");
        return em.createNamedQuery("findAllProducts", Product.class).getResultList();
    }

    public Product findById(Long id) {
        logger.info("findById");
        return em.find(Product.class, id);
    }

    public Long countAll() {
        logger.info("countAll");
        return em.createNamedQuery("countAllProducts", Long.class).getSingleResult();
    }

    public void saveOrUpdate(Product product) {
        logger.info("saveOrUpdate");
        if (product.getId() == null) {
            em.persist(product);
        }
        em.merge(product);
    }

    public void deleteById(Long id) {
        logger.info("deleteById");
        em.createNamedQuery("deleteProductById").setParameter("id", id).executeUpdate();
    }

}
