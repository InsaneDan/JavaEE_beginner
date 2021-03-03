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
import java.io.Serializable;
import java.util.List;

@Named
@ApplicationScoped
public class UserRepository implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {
            try {
                ut.begin();

                saveOrUpdate(new User(1L, "Jhon", "Doe",
                        "jhon_doe@mail.com", "jhondoeLogin", "jhondoePsw"));
                saveOrUpdate(new User(2L, "Asdf", "Smith",
                        "asdf_smith@mail.com", "asdfSmithLogin", "asdfSmithPsw"));

                ut.commit();
            } catch (Exception ex) {
                logger.error("", ex);
                ut.rollback();
            }
        }
    }

    public List<User> findAll() {
        return em.createNamedQuery("findAllUsers", User.class).getResultList();
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public Long countAll() {
        return em.createNamedQuery("countAllUsers", Long.class).getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(User user) {
        if (user.getId() == null) {
            em.persist(user);
        }
        em.merge(user);
    }

    @Transactional
    public void deleteById(Long id) {
        em.createNamedQuery("deleteUserById").setParameter("id", id).executeUpdate();
    }
}
