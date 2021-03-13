package ru.geekbrains.service.cart;

import ru.geekbrains.service.product.ProductRepr;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CartService {

    void addToCart(ProductRepr product, Long quantity);

    List<ProductRepr> getAll();

    void removeFromCart(ProductRepr product);

    Long getQuantity(ProductRepr product);

    public Double getTotalSum();
}
