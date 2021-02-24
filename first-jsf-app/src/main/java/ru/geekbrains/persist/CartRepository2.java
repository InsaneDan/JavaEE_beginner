package ru.geekbrains.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@SessionScoped
public class CartRepository2 implements Serializable {

    private Map<Product, Long> cartMap = new ConcurrentHashMap<Product, Long>();

    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {
//        this.saveOrUpdate(new Product(1L, "Product  1",
//                "Description of product 1", new BigDecimal(100)), 10L);
//        this.saveOrUpdate(new Product(2L, "Product  2",
//                "Description of product 2", new BigDecimal(100)), 20L);
    }

    public List<Map.Entry<Product, Long>> findAll() {
        Set<Map.Entry<Product, Long>> cartProductsSet =
                cartMap.entrySet();
        return new ArrayList<Map.Entry<Product, Long>>(cartProductsSet);
    }

    public void saveOrUpdate(Product product, Long quantity) {
        if (cartMap.containsKey(product)) {
            Long newQuantity = cartMap.get(product) + quantity;
            if (newQuantity <= 0) {
                cartMap.remove(product);
            } else {
                cartMap.replace(product, newQuantity);
            }
        } else {
            cartMap.put(product, quantity);
        }
    }

    public void removeFromCart(Product product) {
        cartMap.remove(product);
    }
}
