package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.io.Serializable;
import java.util.List;

@Stateless
public class UserRepository implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public List<User> findAll() {
        logger.info("findAll");
        return em.createNamedQuery("findAllUsers", User.class).getResultList();
    }

    public User findById(Long id) {
        logger.info("findById");
        return em.find(User.class, id);
    }

    public Long countAll() {
        logger.info("countAll");
        return em.createNamedQuery("countAllUsers", Long.class).getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(User user) {
        logger.info("saveOrUpdate");
        if (user.getId() == null) {
            em.persist(user);
        }
        em.merge(user);
    }

    @Transactional
    public void deleteById(Long id) {
        logger.info("deleteById");
        em.createNamedQuery("deleteUserById").setParameter("id", id).executeUpdate();
    }
}
