package ru.geekbrains;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientConfig;
import ru.geekbrains.service.product.ProductRepr;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class RestClient {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient(new ClientConfig().register(JacksonJsonProvider.class));

        WebTarget webTarget = client.target("http://localhost:8080/jsf-web-app/api/v1/product");

        Invocation.Builder request = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = request.get();

        List<ProductRepr> products = response.readEntity(new GenericType<List<ProductRepr>>() {});
        products.forEach(product -> System.out.println(product.getDescription()));
    }
}
