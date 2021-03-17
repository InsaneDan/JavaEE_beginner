package ru.geekbrains.rest;

import ru.geekbrains.service.category.CategoryRepr;
import ru.geekbrains.service.product.ProductRepr;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/category")
public interface CategoryServiceRest {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<CategoryRepr> findAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(CategoryRepr category);

    @DELETE
    @Path("/delete/{id}")
    void deleteById(Long id);
}
