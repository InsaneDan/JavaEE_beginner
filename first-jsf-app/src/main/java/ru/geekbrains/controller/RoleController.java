package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Role;
import ru.geekbrains.persist.RoleRepository;


import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class RoleController implements Serializable {

    @EJB
    private RoleRepository roleRepository;

    private Role role;

    private List<Role> roles;

    public void getData(ComponentSystemEvent cse) {
        this.roles = roleRepository.getAllRoles();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String createRole() {
        this.role = new Role();
        return "/admin/role_form.xhtml?faces-redirect=true";
    }

    public List<Role> getAllRoles() {
        return roles;
    }

    public String editRole(Role role) {
        this.role = role;
        return "/admin/role_form.xhtml?faces-redirect=true";
    }

    public String saveRole() {
//        roleRepository.saveOrUpdate(role);
        return "/admin/role.xhtml?faces-redirect=true";
    }

}
