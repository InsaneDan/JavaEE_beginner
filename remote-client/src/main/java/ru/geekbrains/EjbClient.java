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
        System.out.println(prod.getId() + "\t" + prod.getName() + "\t" + prod.getDescription()
                + "\t" + prod.getCategoryId() + "\t" + prod.getCategoryName());
    }

    public static Context createInitialContext() throws IOException, NamingException {
        final Properties env = new Properties();
        env.load(EjbClient.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
        return new InitialContext(env);
    }
}


/* РЕЗУЛЬТАТ (консоль):
"C:\Program Files\Java\jdk-15\bin\java.exe" ...............
мар. 16, 2021 1:25:34 AM org.wildfly.naming.client.Version <clinit>
INFO: WildFly Naming version 1.0.13.Final
мар. 16, 2021 1:25:34 AM org.wildfly.security.Version <clinit>
INFO: ELY00001: WildFly Elytron version 1.13.1.Final
мар. 16, 2021 1:25:35 AM org.jboss.ejb.client.naming.ejb.ejbURLContextFactory <clinit>
INFO: EJBCLIENT000064: org.jboss.ejb.client.naming.ejb.ejbURLContextFactory is deprecated; new applications should use org.wildfly.naming.client.WildFlyInitialContextFactory instead
мар. 16, 2021 1:25:35 AM org.xnio.Xnio <clinit>
INFO: XNIO version 3.8.2.Final
мар. 16, 2021 1:25:35 AM org.xnio.nio.NioXnio <clinit>
INFO: XNIO NIO Implementation Version 3.8.2.Final
мар. 16, 2021 1:25:35 AM org.jboss.threads.Version <clinit>
INFO: JBoss Threads version 2.4.0.Final
мар. 16, 2021 1:25:35 AM org.jboss.remoting3.EndpointImpl <clinit>
INFO: JBoss Remoting version 5.0.19.Final
мар. 16, 2021 1:25:35 AM org.jboss.ejb.client.EJBClient <clinit>
INFO: JBoss EJB Client version 4.0.33.Final
1	Product 1	100
2	Product 2	200
3	Product 3	300
4	Product 4	400
5	Product 5	500
6	Product 6	600
7	Product 7	777
8	Product 8	800
9	Product 9	999
10	Product 10	1000

Find by ID == 7
7	Product 7	Description 7	3	Category  3

Process finished with exit code 0

*/