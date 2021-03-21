package ru.geekbrains.jaxws;


import ru.geekbrains.service.product.ProductRepr;

import javax.jws.WebMethod;
import javax.jws.WebService;
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
