package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class ProductRepository {

//    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();
//    private final AtomicLong identity = new AtomicLong(0);
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
                        "Description of product 1", new BigDecimal(100)));
                saveOrUpdate(new Product(2L, "Product  2",
                        "Description of product 2", new BigDecimal(200)));
                saveOrUpdate(new Product(3L, "Product  3",
                        "Description of product 3", new BigDecimal(300)));
                ut.commit();
            } catch (Exception ex) {
                logger.error("", ex);
                ut.rollback();
            }
        }

    }

    public List<Product> findAll() {
//        return new ArrayList<>(productMap.values()); // без JPA
        return em.createNamedQuery("findAll", Product.class).getResultList();
    }

    public Product findById(Long id) {
//        return productMap.get(id); // без JPA
        return em.find(Product.class, id);
    }

    public Long countAll() {
        return em.createNamedQuery("countAll", Long.class).getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
//            Long id = identity.incrementAndGet(); // без JPA
//            product.setId(id);
            em.persist(product);
        }
//        productMap.put(product.getId(), product); // без JPA
        em.merge(product);
    }

    @Transactional
    public void deleteById(Long id) {
//        productMap.remove(id); // без JPA
        em.createNamedQuery("deleteById").setParameter("id", id).executeUpdate();
    }
}
