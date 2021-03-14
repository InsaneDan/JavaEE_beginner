package ru.geekbrains.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @EJB
    private UserRepository userRepository;

    @Override
    public List<UserRepr> findAll() {
        logger.info("findAll");
        return userRepository.findAll().stream()
                .map(UserRepr::new)
                .collect(Collectors.toList());
    }

    private UserRepr buildUserRepr(User user) {
        logger.info("buildUserRepr");
        UserRepr repr = new UserRepr();
        repr.setId(user.getId());
        repr.setFirstName(user.getFirstName());
        repr.setLastName(user.getLastName());
        repr.setEmail(user.getEmail());
        repr.setLogin(user.getLogin());
        repr.setPassword(user.getPassword());
        return repr;
    }

    @Override
    public UserRepr findById(Long id) {
        logger.info("findById");
        User user = userRepository.findById(id);
        if (user != null) {
            return buildUserRepr(user);
        }
        return null;
    }

    @Override
    public Long countAll() {
        logger.info("countAll");
        return userRepository.countAll();
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(UserRepr user) {
        logger.info("saveOrUpdate");
        userRepository.saveOrUpdate(new User(user));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        logger.info("deleteById");
        userRepository.deleteById(id);
    }
}
