package ru.geekbrains.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class CartRepository implements Serializable {

    private Map<Long, CartOrder> cartMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {

        this.saveOrUpdate(new CartOrder(1L, new Product(1L, "Product  1",
                "Description of product 1", new BigDecimal(100)), 10L));
        this.saveOrUpdate(new CartOrder(2L, new Product(2L, "Product  2",
                "Description of product 2", new BigDecimal(100)), 20L));
    }


    public List<CartOrder> findAll() {
        return new ArrayList<>(cartMap.values());
    }

    public CartOrder findById(Long id) {
        return cartMap.get(id);
    }

    public void saveOrUpdate(CartOrder cartOrder) {
        if (cartOrder.getId() == null) {
            Long id = identity.incrementAndGet();
            cartOrder.setId(id);
        }
        cartMap.put(cartOrder.getId(), cartOrder);
    }

    public void deleteById(Long id) {
        cartMap.remove(id);
    }
}
