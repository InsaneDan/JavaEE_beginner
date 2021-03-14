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

    public List<Product> findAll(Long ... categoryId) {
        logger.info("findAll");
        if (categoryId != null) {

            logger.info("findAll");
        }
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
            logger.info("saveOrUpdate - execute EM.PERSIST(product) - start");
            em.persist(product);
            logger.info("saveOrUpdate - execute EM.PERSIST(product) - end");
        }
        logger.info("saveOrUpdate - execute EM.MERGE(product) - start");
        em.merge(product);
        logger.info("saveOrUpdate - execute EM.MERGE(product) - end");
    }

    public void deleteById(Long id) {
        logger.info("deleteById");
        em.createNamedQuery("deleteProductById").setParameter("id", id).executeUpdate();
    }

}
