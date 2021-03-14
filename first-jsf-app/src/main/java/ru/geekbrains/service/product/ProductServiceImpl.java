package ru.geekbrains.service.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(ProductServiceRemote.class)
public class ProductServiceImpl implements ProductService, ProductServiceRemote {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

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
        logger.info("buildProductRepr");
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
        logger.info("findById");
        Product product = productRepository.findById(id);
        if (product != null) {
            return buildProductRepr(product);
        }
        return null;
    }

    @Override
    public Long countAll() {
        logger.info("countAll");
        return productRepository.countAll();
    }

//    @Override
    public void insert(ProductRepr product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(product);
    }

//    @Override
    public void update(ProductRepr product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(product);
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(ProductRepr product) {
        logger.info("saveOrUpdate");
        Category category = null;
        if (product.getCategoryId() != null) {
            logger.info("Saving product with id {}" , product.getId());
            category = categoryRepository.getReference(product.getCategoryId());
        }
        logger.info("Saving new product");
        productRepository.saveOrUpdate(new Product(product, category));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        logger.info("deleteById");
        productRepository.deleteById(id);
    }
}
