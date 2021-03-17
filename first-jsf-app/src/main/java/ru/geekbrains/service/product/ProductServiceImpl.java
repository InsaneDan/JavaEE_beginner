package ru.geekbrains.service.product;

import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.rest.ProductServiceRest;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(ProductServiceRemote.class)
public class ProductServiceImpl implements ProductService, ProductServiceRemote, ProductServiceRest {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductRepr> findAll() {
        return productRepository.findAll().stream()
                .map(this::buildProductRepr)
                .collect(Collectors.toList());
    }

    private ProductRepr buildProductRepr(Product product) {
        ProductRepr repr = new ProductRepr();

        repr.setId(product.getId());
        repr.setName(product.getName());
        repr.setDescription(product.getDescription());
        repr.setPrice(product.getPrice());
        Category category = product.getCategory();
        repr.setCategoryId(category != null ? category.getId() : null);
        repr.setCategoryName(category != null ? category.getName() : null);

        return repr;
    }

    @Override
    public ProductRepr findById(Long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            return buildProductRepr(product);
        }
        return null;
    }

    @Override
    public Long countAll() {
        return productRepository.countAll();
    }

    @Override
    public void insert(ProductRepr product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(product);
    }

    @Override
    public void update(ProductRepr product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(product);
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(ProductRepr product) {
        Category category = null;
        if (product.getCategoryId() != null) {
            category = categoryRepository.getReference(product.getCategoryId());
        }
        productRepository.saveOrUpdate(new Product(product, category));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductRepr> findByName(String name) {
        return productRepository.findByName(name).stream()
                .map(this::buildProductRepr)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductRepr> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream()
                .map(this::buildProductRepr)
                .collect(Collectors.toList());
    }
}
