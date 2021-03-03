package ru.geekbrains.controller;

import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {

    @Inject
    private UserRepository userRepository;

    private User user;

    private List<User> users;

    // предзагрузка списка элементов сущности, чтобы не обращаться несколько раз к базе при загрузке страницы
    public void getData(ComponentSystemEvent cse) {
        this.users = userRepository.findAll();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String createUser() {
        this.user = new User();
        return "/user_form.xhtml?faces-redirect=true";
    }

    public List<User> getAllUsers() {
        return users; // возвращаем предварительно загруженный список элементов
    }

    public String editUser(User user) {
        this.user = user;
        return "/user_form.xhtml?faces-redirect=true";
    }

    public void deleteUser(User user) {
        userRepository.deleteById(user.getId());
    }

    public String saveUser() {
        userRepository.saveOrUpdate(user);
        return "/user.xhtml?faces-redirect=true";
    }
}
