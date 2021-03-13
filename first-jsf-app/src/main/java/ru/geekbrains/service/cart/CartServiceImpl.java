package ru.geekbrains.service.cart;


import ru.geekbrains.service.product.ProductRepr;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateful
public class CartServiceImpl implements CartService {

    private final Map<ProductRepr, Long> productMap = new HashMap<>();

    @Override
    public void addToCart(ProductRepr product, Long quantity) {
        if (productMap.containsKey(product)) {
            Long newQuantity = productMap.get(product) + quantity;
            if (newQuantity <= 0) {
                productMap.remove(product);
            } else {
                productMap.replace(product, newQuantity);
            }
        } else {
            productMap.put(product, quantity);
        }
    }

    @Override
    public void removeFromCart(ProductRepr product) {
        productMap.remove(product);
    }

    @Override
    public List<ProductRepr> getAll() {
        return new ArrayList<>(productMap.keySet());
    }

    @Override
    public Long getQuantity(ProductRepr product) {
        if (product != null) {
            return productMap.get(product);
        }
        return null;
    }

    public Double getTotalSum() {
        Double sum = 0D;
        Long quantity;
        Double price;
        for (Map.Entry<ProductRepr, Long> mapEntry : productMap.entrySet()) {
            quantity = mapEntry.getValue();
            price = Double.valueOf(mapEntry.getKey().getPrice().toString());
            sum += quantity * price;
        }
        return sum;
    }
}
