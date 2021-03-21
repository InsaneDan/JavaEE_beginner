package ru.geekbrains.persist;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
@SecurityDomain("servlet-security-quickstart")
@PermitAll
public class ProductRepository implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public List<Product> findAll() {
        return em.createNamedQuery("findAllProducts", Product.class).getResultList();
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findByName(String name) {
        return em.createNamedQuery("findProductsByName", Product.class)
                .setParameter("name", name).getResultList();
    }

    public List<Product> findByCategoryId(Long categoryId) {
        return em.createNamedQuery("findProductsByCategoryId", Product.class)
                .setParameter("categoryId", categoryId).getResultList();
    }

    public Long countAll() {
        return em.createNamedQuery("countAllProducts", Long.class).getSingleResult();
    }

    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            logger.info("saveOrUpdate - execute EM.PERSIST(product) - start");
            em.persist(product);
            logger.info("saveOrUpdate - execute EM.PERSIST(product) - end");
        }
        logger.info("saveOrUpdate - execute EM.MERGE(product) - start");
        em.merge(product);
        logger.info("saveOrUpdate - execute EM.MERGE(product) - end");
    }

    @RolesAllowed("admin")
    public void deleteById(Long id) {
        em.createNamedQuery("deleteProductById").setParameter("id", id).executeUpdate();
    }

}
