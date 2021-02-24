package ru.geekbrains.controller;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.inject.Named;
import java.io.Serializable;

//@Named
//@SessionScoped
@ManagedBean (value = "navigateController")
@RequestScoped
public class NavigateController implements Serializable {

    //для получения параметра pageId из запроса
    @ManagedProperty(value = "#{param.pageId}")
    private String pageId;

    public String showPage() {
        if(pageId == null) {
            return "/main.xhtml";
        }

        if(pageId.equals("catalog")) {
            return "/catalog.xhtml";
        }else if(pageId.equals("product")) {
            return "/product.xhtml";
        }else if(pageId.equals("cart")) {
            return "/cart.xhtml";
        }else if(pageId.equals("user")) {
            return "/user.xhtml";
        }else {
            return "/main.xhtml";
        }
    }
}
