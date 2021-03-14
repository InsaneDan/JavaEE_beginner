package ru.geekbrains.service.cart;

import ru.geekbrains.service.product.ProductRepr;

import javax.ejb.Stateful;
import java.util.*;

@Stateful
public class CartServiceImpl implements CartService {

    private final Map<ProductRepr, Long> productMap = new HashMap<>();

    @Override
    public void addToCart(ProductRepr product, Long quantity) {

        // проверка id: если такой товар есть в таблице - увеличить его количество, если такого id нет - добавить

        Long keyId = product.getId();
        for (Map.Entry<ProductRepr, Long> prodEntry : productMap.entrySet()) {
            if (keyId == prodEntry.getKey().getId()) {
                Long newQuantity = prodEntry.getValue() + quantity;
                if (newQuantity <= 0) {
                    productMap.remove(prodEntry.getKey());
                } else {
                    productMap.replace(prodEntry.getKey(), newQuantity);
                }
                return;
            }
        }
        productMap.put(product, quantity);
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
