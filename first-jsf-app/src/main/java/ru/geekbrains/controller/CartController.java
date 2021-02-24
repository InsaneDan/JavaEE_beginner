package ru.geekbrains.controller;

import ru.geekbrains.persist.CartOrder;
import ru.geekbrains.persist.CartRepository;
import ru.geekbrains.persist.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named
@SessionScoped
public class CartController implements Serializable {

    @Inject
    private CartRepository cartRepository;

    private CartOrder cartOrder;

    public CartOrder getCartOrder() {
        return cartOrder;
    }

    public void setCartOrder(CartOrder cartOrder) {
        this.cartOrder = cartOrder;
    }

    public List<CartOrder> getAllCartOrders() {
        return cartRepository.findAll();
    }

    public void addToCart(Product product) {
        this.cartOrder = cartOrder;
        cartOrder.setQuantitiy(cartOrder.getQuantitiy() + 1);
//        return "/product_form.xhtml?faces-redirect=true";
    }

    public void decrementCartOrder(CartOrder cartOrder) {
        this.cartOrder = cartOrder;
        cartOrder.setQuantitiy(cartOrder.getQuantitiy() - 1);
//        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteCartOrder(CartOrder cartOrder) {
        cartRepository.deleteById(cartOrder.getId());
    }

}
