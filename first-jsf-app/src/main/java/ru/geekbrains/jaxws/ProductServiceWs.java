package ru.geekbrains.jaxws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import ru.geekbrains.service.product.ProductRepr;

import java.util.List;

@WebService
public interface ProductServiceWs {

    @WebMethod
    List<ProductRepr> findAll();

    @WebMethod
    ProductRepr findById(long id);

    @WebMethod
    void insert(ProductRepr toDoRepr);
}
