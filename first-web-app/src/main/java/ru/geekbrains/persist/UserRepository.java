package ru.geekbrains.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository {

    private final Map<Long, User> usersMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

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
