package ru.geekbrains;

import ru.geekbrains.service.product.ProductRepr;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

public class JmsClient {

    public static void main(String[] args) throws IOException, NamingException {
        Context context = createInitialContext();

        ConnectionFactory factory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
        JMSContext jmsContext = factory.createContext("jmsUser", "123");

        Destination dest = (Destination) context.lookup("jms/productQueue");

        JMSProducer producer = jmsContext.createProducer();

        ObjectMessage om = jmsContext.createObjectMessage(new ProductRepr(null, "Product from JMS", "Product from JMS",
                new BigDecimal(100), 1L, null));

        producer.send(dest, om);
    }

    public static Context createInitialContext() throws IOException, NamingException {
        final Properties env = new Properties();
        env.load(EjbClient.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
        return new InitialContext(env);
    }
}


/*
// Create a new intial context, which loads from jndi.properties file:
javax.naming.Context ctx = new javax.naming.InitialContext();
// Lookup the connection factory:
javax.jms.TopicConnectionFactory factory = (javax.jms.TopicConnectionFactory)ctx.lookup("ConnectionFactory");
// Create a new TopicConnection for pub/sub messaging:
javax.jms.TopicConnection conn = factory.getTopicConnection();
// Lookup an existing topic:
javax.jms.Topic mytopic = (javax.jms.Topic)ctx.lookup("MyTopic");
// Create a new TopicSession for the client:
javax.jms.TopicSession session = conn.createTopicSession(false,TopicSession.AUTO_ACKNOWLEDGE);
// Create a new subscriber to receive messages:
javax.jms.TopicSubscriber subscriber = session.createSubscriber(mytopic);
*/