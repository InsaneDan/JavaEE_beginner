package ru.geekbrains.rest;

import ru.geekbrains.service.product.ProductRepr;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/product")
public interface ProductServiceRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepr> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductRepr findById(@PathParam("id") Long id);

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepr> findByName(@PathParam("name") String name);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductRepr product);

    @GET
    @Path("/category/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepr> findByCategoryId(@PathParam("categoryId") Long categoryId);




    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(ProductRepr product);

    @DELETE
    @Path("/delete/{id}")
    void deleteById(Long id);
}
