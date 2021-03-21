package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
public class UserRepository implements Serializable {

    private final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @PersistenceContext(unitName = "ds")
    protected EntityManager em;

    public UserRepository() {
    }

    @TransactionAttribute
    public User saveOrUpdate(User user) {
        if (user.getId() == null) {
            em.persist(user);
            return user;
        }
        return em.merge(user);
    }

    @TransactionAttribute
    public void delete(int id) {
        logger.info("Deleting user");

        try {
            User attached = findById(id);
            if (attached != null) {
                em.remove(attached);
            }
        } catch (Exception ex) {
            logger.error("Error with entity class", ex);
            throw new IllegalStateException(ex);
        }
    }

    public User findById(int id) {
        return em.find(User.class, id);
    }

    @TransactionAttribute
    public List<User> getAllUsers() {
        return em.createQuery("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles", User.class).getResultList();
    }

    public long countAll() {
        return em.createQuery("select count(*) from User", Long.class)
                .getSingleResult();
    }
}
