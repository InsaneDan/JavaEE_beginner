package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;
import ru.geekbrains.service.role.RoleRepr;
import ru.geekbrains.service.role.RoleService;
import ru.geekbrains.service.user.UserRepr;
import ru.geekbrains.service.user.UserService;

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
    private UserService userService;
    @EJB
    private RoleService roleService;

    private UserRepr user;

    private List<RoleRepr> roles;

    private List<UserRepr> users;

    public void getData(ComponentSystemEvent cse) {
        this.roles = roleService.getAllRoles();
        this.users = userService.getAllUsers();
    }

    public UserRepr getUser() {
        return user;
    }

    public void setUser(UserRepr user) {
        this.user = user;
    }

    public String createUser() {
        this.user = new UserRepr();
        return "/admin/user_form.xhtml?faces-redirect=true";
    }

    public List<UserRepr> getAllUsers() {
        return users;
    }

    public String editUser(UserRepr user) {
        this.user = user;
        return "/admin/user_form.xhtml?faces-redirect=true";
    }

    public String saveUser() {
        userService.saveOrUpdate(user);
        return "/admin/user.xhtml?faces-redirect=true";
    }

    public String logout() {
        //HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        httpSession.invalidate();
        return "/product.xhtml?faces-redirect=true";
    }
}
