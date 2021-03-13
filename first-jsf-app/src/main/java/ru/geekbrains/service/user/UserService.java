package ru.geekbrains.service.user;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserService {

    List<UserRepr> findAll();

    UserRepr findById(Long id);

    Long countAll();

    void saveOrUpdate(UserRepr user);

    void deleteById(Long id);
}
