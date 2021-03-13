package ru.geekbrains.controller;


import ru.geekbrains.service.cart.CartService;
import ru.geekbrains.service.product.ProductRepr;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class CartController implements Serializable {

    @EJB
    private CartService cartService;

    // TODO
    public void addToCart(ProductRepr product, Long quantity) {
        cartService.addToCart(product, quantity);
    }

    public void removeFromCart(ProductRepr product) {
        cartService.removeFromCart(product);
    }

    public List<ProductRepr> getAll() {
        return new ArrayList<>(cartService.getAll());
    }

    public Long getQuantity(ProductRepr product) {
        return cartService.getQuantity(product);
    }

    public Double getTotalSum() {
        return cartService.getTotalSum();
    }

}
