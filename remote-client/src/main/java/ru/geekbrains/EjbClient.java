package ru.geekbrains;



import ru.geekbrains.service.product.ProductRepr;
import ru.geekbrains.service.product.ProductServiceRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;

public class EjbClient {

    public static void main(String[] args) throws IOException, NamingException {
        Context context = createInitialContext();

        String jndiServiceName = "ejb:/first-jsf-app/ProductServiceImpl!ru.geekbrains.service.product.ProductServiceRemote";
        ProductServiceRemote productService = (ProductServiceRemote) context.lookup(jndiServiceName);

        productService.findAll()
                .forEach(prod -> System.out.println(prod.getId() + "\t" + prod.getName() + "\t" + prod.getPrice()));

        System.out.println("\nFind by ID == 7");
        ProductRepr prod = productService.findById(7L);
        System.out.println(prod.getId() + "\t" + prod.getName() + "\t" + prod.getPrice() +
                "\tCategoryId:" + prod.getCategoryId() + "\t" + prod.getCategoryName());
    }

    public static Context createInitialContext() throws IOException, NamingException {
        final Properties env = new Properties();
        env.load(EjbClient.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
        return new InitialContext(env);
    }
}
