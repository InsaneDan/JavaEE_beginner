package ru.geekbrains.controller;

import ru.geekbrains.persist.CartRepository;
import ru.geekbrains.persist.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class CartController implements Serializable {

    @Inject
    private CartRepository cartRepository;

    public List<Map.Entry<Product, Long>> getAllCartProducts() {
        return cartRepository.findAll();
    }

    public void addToCart(Product product, Long quantity) {
        cartRepository.saveOrUpdate(product, quantity);
    }

    public void quantifyCartProduct(Product product, Long quantity) {
        cartRepository.saveOrUpdate(product, quantity);
    }

    public void removeCartProduct(Product product) {
        cartRepository.removeFromCart(product);
    }

    public Double getTotalSum() {
        Double sum = 0D;
        Long quantity;
        Double price;
        for (Map.Entry<Product, Long> order : cartRepository.findAll()) {
            quantity = order.getValue();
            price = Double.valueOf(order.getKey().getPrice().toString());
            sum += quantity * price;
        }
        return sum;
    }
}
