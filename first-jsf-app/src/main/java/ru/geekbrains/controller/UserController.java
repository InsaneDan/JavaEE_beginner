package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Inject
    private HttpSession httpSession;

    @EJB
    private UserRepository userRepository;

    private User user;

    private List<User> users;

    public void getData(ComponentSystemEvent cse) {
        this.users = userRepository.getAllUsers();
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

    public String saveUser() {
        userRepository.saveOrUpdate(user);
        return "/user.xhtml?faces-redirect=true";
    }


    public String logout() {
        //HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        httpSession.invalidate();
        return "/product.xhtml?faces-redirect=true";
    }
}
