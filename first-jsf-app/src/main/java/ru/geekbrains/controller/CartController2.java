package ru.geekbrains.controller;

import ru.geekbrains.persist.CartRepository2;
import ru.geekbrains.persist.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Named
@SessionScoped
public class CartController2 implements Serializable {

    @Inject
    private CartRepository2 cartRepository2;

    public List<Map.Entry<Product, Long>> getAllCartProducts() {
        return cartRepository2.findAll();
    }

    public void addToCart(Product product, Long quantity) {
        cartRepository2.saveOrUpdate(product, quantity);
    }

    public void quantifyCartProduct(Product product, Long quantity) {
        cartRepository2.saveOrUpdate(product, quantity);
    }

    public void removeCartProduct(Product product) {
        cartRepository2.removeFromCart(product);
    }

    public Double getTotalSum() {
        Double sum = 0D;
        Long quantity;
        Double price;
        for (Map.Entry<Product, Long> order : cartRepository2.findAll()) {
            quantity = order.getValue();
            price = Double.valueOf(order.getKey().getPrice().toString());
            sum += quantity * price;
        }
        return sum;
    }
}
