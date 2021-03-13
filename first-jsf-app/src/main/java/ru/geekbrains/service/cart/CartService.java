package ru.geekbrains.service.cart;

import ru.geekbrains.service.product.ProductRepr;
import ru.geekbrains.service.product.ProductService;

import javax.ejb.Local;
import java.util.ArrayList;
import java.util.List;

@Local
public interface CartService {

    // TODO
    void addToCart(ProductRepr product, Long quantity);

    List<ProductRepr> getAll();

    void removeFromCart(ProductRepr product);

    Long getQuantity(ProductRepr product);

    public Double getTotalSum();
}
