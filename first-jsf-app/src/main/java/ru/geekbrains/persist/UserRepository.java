package ru.geekbrains.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class UserRepository implements Serializable {

    private final Map<Long, User> usersMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {
        this.saveOrUpdate(new User(null, "Jhon", "Doe",
                "jhon_doe@mail.com", "jhondoeLogin", "jhondoePsw"));
        this.saveOrUpdate(new User(null, "Asdf", "Smith",
                "asdf_smith@mail.com", "asdfSmithLogin", "asdfSmithPsw"));
    }

    public List<User> findAll() {
        return new ArrayList<>(usersMap.values());
    }

    public User findById(Long id) {
        return usersMap.get(id);
    }

    public void saveOrUpdate(User user) {
        if (user.getId() == null) {
            Long id = identity.incrementAndGet();
            user.setId(id);
        }
        usersMap.put(user.getId(), user);
    }

    public void deleteById(Long id) {
        usersMap.remove(id);
    }
}
