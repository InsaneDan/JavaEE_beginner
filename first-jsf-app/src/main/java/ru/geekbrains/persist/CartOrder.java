package ru.geekbrains.persist;

public class CartOrder {

    private Long id;
    private Product product;
    private Long quantitiy;

    public CartOrder() {}

    public CartOrder(Long id, Product product, Long quantitiy) {
        this.id = id;
        this.product = product;
        this.quantitiy = quantitiy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantitiy() {
        return quantitiy;
    }

    public void setQuantitiy(Long quantitiy) {
        this.quantitiy = quantitiy;
    }
}
