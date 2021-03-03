package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Named
@ApplicationScoped
public class ProductRepository implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {

            try {
                ut.begin();
                saveOrUpdate(new Product(1L, "Product  1",
                        "Description of product 1", new BigDecimal(100),
                        getCategoryForProduct(1L)));
                saveOrUpdate(new Product(2L, "Product  2",
                        "Description of product 2", new BigDecimal(200),
                        getCategoryForProduct(2L)));
                saveOrUpdate(new Product(3L, "Product  3",
                        "Description of product 3", new BigDecimal(300),
                        getCategoryForProduct(1L)));
                saveOrUpdate(new Product(4L, "Product  4",
                        "Description of product 4", new BigDecimal(400),
                        getCategoryForProduct(2L)));
                saveOrUpdate(new Product(5L, "Product  5",
                        "Description of product 5", new BigDecimal(555),
                        getCategoryForProduct(3L)));
                ut.commit();
            } catch (Exception ex) {
                logger.error("", ex);
                ut.rollback();
            }
        }
    }

    // метод для получения категории по её id из сохраненных в БД
    public Category getCategoryForProduct(Long id) {
        return em.createQuery("FROM Category c WHERE c.id = " + id, Category.class).getSingleResult();
    }

    public List<Product> findAll() {
/*
        // через именованные запросы
        return em.createNamedQuery("findAllProducts", Product.class).getResultList();
*/
        // через запрос JPQL
        return em.createQuery("FROM Product p ORDER BY productName", Product.class).getResultList();
    }

    /*@TransactionAttribute
    public List<Product> getAllProducts() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> from = query.from(Product.class);
        from.fetch("categories", JoinType.LEFT);
        query.select(from).distinct(true);
        return em.createQuery(query).getResultList();
    }*/

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public Long countAll() {
        return em.createNamedQuery("countAllProducts", Long.class).getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        }
        em.merge(product);
    }

    @Transactional
    public void deleteById(Long id) { em.createNamedQuery("deleteProductById").setParameter("id", id).executeUpdate(); }

}
